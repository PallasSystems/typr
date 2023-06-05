package uk.pallas.systems.typr.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.measure.Unit;
import javax.measure.quantity.Frequency;
import org.springframework.stereotype.Service;
import si.uom.NonSI;
import si.uom.SI;
import tech.units.indriya.unit.Units;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.NumberValidationRule;
import uk.pallas.systems.typr.services.UnitsService;

@Service
public class UnitsServiceImpl implements UnitsService {
  /** Cache of units against their names. **/
  private final Map<String, Unit<?>> units;

  public UnitsServiceImpl() {
    this.units = new ConcurrentHashMap<>();

    final Unit rateOfTurn = NonSI.DEGREE_ANGLE.divide(Units.MINUTE).asType(Frequency.class);
    this.units.put("Degree Angle per Minute", rateOfTurn);

    // Pull in the other units into a unified list
    final SI siUnits = SI.getInstance();
    if (null != siUnits) {
      for (final Unit<?> value : siUnits.getUnits()) {
        if (null != value) {
          if (null == value.getName() || value.getName().isBlank()) {
            this.units.put(value.toString(), value);
          } else {
            this.units.put(value.getName(), value);
          }
        }
      }
    }

    final NonSI nonSIUnits = NonSI.getInstance();
    if (null != nonSIUnits) {
      for (final Unit<?> value : nonSIUnits.getUnits()) {
        if (null != value) {
          if (null == value.getName() || value.getName().isBlank()) {
            this.units.put(value.toString(), value);
          } else {
            this.units.put(value.getName(), value);
          }
        }
      }
    }
  }

  public Collection<Unit<?>> getUnits() {
    return this.units.values();
  }

  public Unit<?> getUnit(final String unitName) {
    Unit<?> unit = null;
    if (null == unitName || unitName.isBlank()) {
      //unit = null;
    } else {
      unit = this.units.get(unitName);
    }

    return unit;
  }

  public boolean isValid(final String unitName) {
    return ValidationRuleConstants.NO_UNITS.equals(unitName) || null != this.getUnit(unitName);
  }

  @Override
  public boolean isValid(final FieldDefinition definition) {
    final boolean result;

    if (null == definition || null == definition.getRules() || definition.getRules().isEmpty()) {
      result = false;
    } else {
      boolean valid = true;
      for (final ValidationRule rule : definition.getRules()) {
        if (rule instanceof LongValidationRule) {
          valid = this.isValid(((LongValidationRule) rule).getUnit());
        } else if (rule instanceof DoubleValidationRule) {
          valid = this.isValid(((DoubleValidationRule) rule).getUnit());
        }

        // if we find an invalid rule then stop and assume the whole definition isn't valid.
        if (!valid) {
          break;
        }
      }

      result = valid;
    }

    return result;
  }
}
