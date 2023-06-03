package uk.pallas.systems.typr.domain.entities.v1.validation.number;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;

@Entity
@Table(name = "val_double_rules")
public class DoubleValidationRuleDomain extends AbstractNumberValidationRuleDomain<Double>
  implements DoubleValidationRule {

  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  public DoubleValidationRuleDomain() {
    this(null);
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor and sets Minimum/Maximum values.
   *
   * @param data the object to create a duplicate off.
   */
  public DoubleValidationRuleDomain(final DoubleValidationRule data) {
    this(null == data ? null : data.getMaximumValue(), null == data ? null : data.getMinimumValue(),
      null == data ? null : data.getDescription(), null == data ? null : data.getUnit());
  }

  /**
   * Constructor, allows us to set the internal abstract fields.
   *
   * @param max The upper bound allowed for the field
   * @param min the lower bound allowed for the field
   */
  protected DoubleValidationRuleDomain(final Double max, final Double min, final String detailedDescription,
                                       final String unitName) {
    super(max, min, detailedDescription, unitName);
  }

  /**
   * Attempts to confirm the supplied value is a whole number and will return it is true.
   *
   * @param toConvert the object to test (if Number or a String holding a valid whole number.
   * @return null .. or a valid Number.
   */
  @Override
  protected Double getNumber(final Object toConvert) {

    Double result = null;

    if (toConvert instanceof Number) {
      result = ((Number) toConvert).doubleValue();
    } else if (null != toConvert) {
      try {
        result = Double.parseDouble(toConvert.toString());
      } catch (final NumberFormatException exception) {
        // TODO lets catch this fail and log it.
      }
    }

    return result;
  }
}
