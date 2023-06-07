package uk.pallas.systems.typr.rest.entities.v1.validation.number;

import io.swagger.v3.oas.annotations.media.Schema;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;

/**
 * This is used for whole integer numbers when used in fields and allows us to validate that they are within an
 * acceptable range. E.g. we would expect an Age (years) to have a minimum of 0 and a maximum of 150.
 */
@Schema(description = "This is used for whole integer numbers when used in fields and allows us to validate that they"
  + " are within an acceptable range. E.g. we would expect an Age (years) to have a minimum of 0 "
  + "and a maximum of 150.")
public class LongValidationRuleDTO extends AbstractNumberValidationRuleDTO<Long> implements LongValidationRule {


  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  public LongValidationRuleDTO() {
    this(null);
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor and sets Minimum/Maximum values.
   *
   * @param data the object to create a duplicate off.
   */
  public LongValidationRuleDTO(final LongValidationRule data) {
    this(null == data ? null : data.getMaximumValue(), null == data ? null : data.getMinimumValue(),
      null == data ? null : data.getDescription(), null == data ? null : data.getUnit());
  }

  /**
   * Constructor, allows us to set the internal abstract fields.
   *
   * @param max The upper bound allowed for the field
   * @param min the lower bound allowed for the field
   */
  public LongValidationRuleDTO(final Long max, final Long min, final String detailedDescription,
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
