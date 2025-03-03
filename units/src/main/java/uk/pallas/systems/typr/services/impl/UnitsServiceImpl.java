package uk.pallas.systems.typr.services.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.measure.Unit;
import javax.measure.quantity.Frequency;
import javax.measure.quantity.Length;
import javax.measure.quantity.Speed;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import si.uom.NonSI;
import si.uom.SI;
import tech.units.indriya.format.SimpleUnitFormat;
import tech.units.indriya.unit.Units;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;
import uk.pallas.systems.typr.services.UnitsService;

@Service
public class UnitsServiceImpl implements UnitsService {
  /** Static Logger for the class. */
  private static final Log LOGGER = LogFactory.getLog(UnitsServiceImpl.class);

  /** Cache of units against their names. **/
  private final Map<String, Unit<?>> units;

  public UnitsServiceImpl() {
    this.units = new ConcurrentHashMap<>();

    final Unit<Frequency> rateOfTurn = NonSI.DEGREE_ANGLE.divide(Units.MINUTE).asType(Frequency.class);
    this.units.put("Degree Angle per Minute", rateOfTurn);

    // Pull in the other units into a unified list
    final SI siUnits = SI.getInstance();
    if (null != siUnits) {
      this.processUnits(siUnits.getUnits());
    }

    final NonSI nonSIUnits = NonSI.getInstance();
    if (null != nonSIUnits) {
      this.processUnits(nonSIUnits.getUnits());
    }

    final Units unitsInstance = Units.getInstance();
    if (null != unitsInstance) {
      this.processUnits(unitsInstance.getUnits());
    }

    this.addNonSiCommonUnits();
  }

  /**
   * The measurement library has removed some units from its design. This adds some of those
   * units back in.
   */
  private void addNonSiCommonUnits() {
    /**
     * A unit of length equal to <code>1852.0 m</code> (standard name
     * <code>nmi</code>).
     */
    final Unit<?> nauticalMile = this.addUnit(SI.METRE.multiply(1852), "Nautical mile", "nmi", true);

    /**
     * A unit of velocity expressing the number of {@link #NAUTICAL_MILE nautical
     * miles} per {@link #HOUR hour} (abbreviation <code>kn</code>).
     */
    this.addUnit(nauticalMile.divide(SI.HOUR).asType(Speed.class), "Knot", "kn", true);
  }

  /**
   * Used to add a unit into the Unit Service for other components to make use of.
   * 
   * @param value the unit to be added to the service.
   * @param name the name of the unit
   * @param text the unit descriptor (e.g. kph)
   * @param isLabel is there a unit descriptor.
   * @return the reference to the unit that was supplied.
   */
  private Unit<?> addUnit(Unit<?> value, String name, String text, boolean isLabel) {

    if (null == value) {
      LOGGER.error("addUnit - Called Measure Unit library and did not get an associated units");
    } else {
      if (isLabel && StringUtils.hasText(text)) {
        SimpleUnitFormat.getInstance().label(value, text);
      } 
    
      if (StringUtils.hasText(name)) {
        this.units.put(name, value);
      } else {
        this.units.put(value.toString(), value);
      }
    }

    return value;
  }

  /**
   * Designed to iterate over a collection of units and add them to the services internal cache, so we can
   * process a single list of units rather than having to check multiple places.
   *
   * @param units the units to process.
   */
  private void processUnits(final Set<Unit<?>> values) {
    if (null == values) {
      LOGGER.error("processUnits - Called Measure Unit library and did not get an associated units");
    } else {
      for (final Unit<?> value : values) {
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

  /**
   * {@inheritDoc}
   * @return a collection of types collected from the measurement library.
   */
  @Override
  public Collection<String> getUnits() {
    return this.units.keySet();
  }

  public Unit<?> getUnit(final String unitName) {
    Unit<?> unit = null;
    if (null == unitName || unitName.isBlank()) {
      LOGGER.trace("getUnits - Invalid Unit Name was supplied");
    } else {
      unit = this.units.get(unitName);
    }

    return unit;
  }

  /**
   * {@inheritDoc}
   *
   * @param unitName the name to perform a lookup on
   * @return false if the name can't be found.
   */
  @Override
  public boolean isValid(final String unitName) {
    return ValidationRuleConstants.NO_UNITS.equals(unitName) || null != this.getUnit(unitName);
  }

  /**
   * {@inheritDoc}
   *
   * @param definition the object to check
   * @return false if the name can't be found.
   */
  @Override
  public boolean isValid(final FieldDefinition definition) {
    final boolean result;

    if (null == definition || null == definition.getRules() || definition.getRules().isEmpty()) {
      result = false;
    } else {
      boolean valid = true;
      for (final ValidationRule rule : definition.getRules()) {
        valid = this.isValid(rule);

        // if we find an invalid rule then stop and assume the whole definition isn't valid.
        if (!valid) {
          break;
        }
      }

      result = valid;
    }

    return result;
  }

  /**
   * Checks if a Numeric based rule is found and if the unit associated with it is valid.
   * If the rule is wrapped this will extract and test the internal rule.
   * @param rule the rule to extract the unit value from.
   * @return this will return true if no numeric rule, or true if a numeric rule has a valid unit
   */
  private boolean isValid(final ValidationRule rule) {
    final boolean valid;

    if (rule instanceof LongValidationRule) {
      valid = this.isValid(((LongValidationRule) rule).getUnit());
    } else if (rule instanceof DoubleValidationRule) {
      valid = this.isValid(((DoubleValidationRule) rule).getUnit());
    } else if (rule instanceof CountryCodeWrapper wrapper) {
      valid = this.isValid(wrapper.getRule());
    } else {
      valid = true;
    }

    return valid;
  }
}
