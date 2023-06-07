package uk.pallas.systems.typr.domain.entities.v1.validation.number;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;

@Entity
@Table(name = "val_long_rules")
public class LongValidationRuleDomain extends AbstractNumberValidationRuleDomain<Long> implements LongValidationRule {


  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  public LongValidationRuleDomain() {
    this(null);
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor and sets Minimum/Maximum values.
   *
   * @param data the object to create a duplicate off.
   */
  public LongValidationRuleDomain(final LongValidationRule data) {
    this(null == data ? null : data.getMaximumValue(), null == data ? null : data.getMinimumValue(),
      null == data ? null : data.getDescription(), null == data ? null : data.getUnit());
  }

  /**
   * Constructor, allows us to set the internal abstract fields.
   *
   * @param max The upper bound allowed for the field
   * @param min the lower bound allowed for the field
   */
  public LongValidationRuleDomain(final Long max, final Long min, final String detailedDescription,
                                     final String unit) {
    super(max, min, detailedDescription, unit);
  }

  /**
   * Attempts to confirm the supplied value is a whole number and will return it is true.
   *
   * @param toConvert the object to test (if Number or a String holding a valid whole number.
   * @return null .. or a valid Number.
   */
  @Override
  protected Long getNumber(final Object toConvert) {

    Long result = null;

    if (toConvert instanceof Number) {
      result = ((Number) toConvert).longValue();
    } else if (null != toConvert) {
      try {
        final Double value = Double.valueOf(toConvert.toString());
        result = value.longValue();
      } catch (final NumberFormatException exception) {
        // TODO lets catch this fail and log it.
      }
    }

    return result;
  }
}
