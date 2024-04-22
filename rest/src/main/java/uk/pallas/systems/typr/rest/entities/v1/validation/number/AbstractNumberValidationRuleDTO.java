package uk.pallas.systems.typr.rest.entities.v1.validation.number;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;
import uk.pallas.systems.typr.entities.v1.validation.number.NumberValidationRule;
import uk.pallas.systems.typr.rest.entities.v1.validation.AbstractValidationRuleDTO;

public abstract class AbstractNumberValidationRuleDTO<N extends Number>
  extends AbstractValidationRuleDTO implements NumberValidationRule<N> {

  /**
   * Is there an upper range for valid value stored within the field?
   */
  @NotNull
  @Schema(description = "Is there an upper range for valid value stored within the field?",
    example = "999")
  private N maximumValue;

  /**
   * Is there a lower range for valid value stored within the field?
   */
  @NotNull
  @Schema(description = "Is there a lower range for valid value stored within the field?",
    example = "0")
  private N minimumValue;

  /** Used to define the units within the field, the same field might be in degrees, radians, etc.. */
  @Schema(description = "Used to define the units within the field, the same field might be in degrees, radians, etc..",
    example = "METRE_SECOND")
  private String unit;

  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  protected AbstractNumberValidationRuleDTO() {
    this(null);
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor and sets Minimum/Maximum values.
   *
   * @param data the object to create a duplicate off.
   */
  protected AbstractNumberValidationRuleDTO(final NumberValidationRule<N> data) {
    this(null == data ? null : data.getMaximumValue(), null == data ? null : data.getMinimumValue(),
      null == data ? null : data.getDescription(), null == data ? null : data.getUnit());
  }

  /**
   * Constructor, allows us to set the internal abstract fields.
   *
   * @param max The upper bound allowed for the field
   * @param min the lower bound allowed for the field
   * @param detailedDescription Detailed description of the field definition.
   * @param unitName Used to define the units within the field, the same field might be in degrees, radians, etc..
   */
  protected AbstractNumberValidationRuleDTO(final N max, final N min, final String detailedDescription,
                                            final String unitName) {
    super(detailedDescription);

    this.maximumValue = max;
    this.minimumValue = min;
    this.unit = null == unitName || unitName.isBlank() ? ValidationRuleConstants.NO_UNITS : unitName;
  }

  /**
   * Compares the supplied object to this one, it checks the supplied object is a FieldDefinition and then checks
   * the definition applies validation, its name and description to see if they are matching objects.
   *
   * @param toCompare the object to compare (can be null or a child class, etc..)
   * @return false if the name/validation and description fields in a field definition are
   *               different (or it isn't a field definition).
   */
  @Override
  public boolean equals(final Object toCompare) {

    final boolean result;
    if (this == toCompare) {
      result = true;
    } else if (toCompare instanceof NumberValidationRule) {
      final NumberValidationRule<?> that = (NumberValidationRule<?>) toCompare;
      result = super.equals(toCompare)
        && Objects.equals(this.getMaximumValue(), that.getMaximumValue())
        && Objects.equals(this.getMinimumValue(), that.getMinimumValue())
        && Objects.equals(this.getUnit(), that.getUnit());
    } else {
      result = false;
    }

    return result;
  }

  /**
   * Generates a Unique hashcode for the field definition class.
   *
   * @return a valid integer representation of this object,
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.getMaximumValue(), this.getMinimumValue(), this.getUnit());
  }

  /**
   * Retrieves the maximum alue that the field definition allows.
   *
   * @return null is allowed (or a valid N value)
   */
  public N getMaximumValue() {
    return this.maximumValue;
  }

  /**
   * Sets the maximum value (can be null) for the field definition to apply to the data field during validation.
   *
   * @param maximum the maximum possible value
   */
  public void setMaximumValue(final N maximum) {
    this.maximumValue = maximum;
  }

  /**
   * Retrieves the minimum value that the field definition allows.
   *
   * @return null is allowed (or a valid N value)
   */
  public N getMinimumValue() {
    return minimumValue;
  }

  /**
   * Sets the mainimum value (can be null) for the field definition to apply to the data field during validation.
   *
   * @param minimum the minimum possible valid value
   */
  public void setMinimumValue(final N minimum) {
    this.minimumValue = minimum;
  }

  /**
   * This will compare the incoming object and see if it is a valid whole number and if the value is a valid
   * number do we have a maximum or a minimum data range? If there is a upper or lower value (they can be null) then
   * it tests the supplied object is less than/equal or greater than/equal as appropriate.
   * <p>
   * If validationOptional is set to true this will return true by default (no further checks).
   *
   * @param toTest to test is valid
   * @return false if the object isn't a number or if it falls out the expected data range.
   */
  public boolean isValid(final Object toTest) {
    final Number value = this.getNumber(toTest);
    return this.isValid(value);
  }

  /**
   * This will compare the incoming object and see if it is a valid whole number and if the value is a valid
   * number do we have a maximum or a minimum data range? If there is a upper or lower value (they can be null) then
   * it tests the supplied object is less than/equal or greater than/equal as appropriate.
   * <p>
   * If validationOptional is set to true this will return true by default (no further checks).
   *
   * @param toTest to test is valid
   * @return false if the object isn't a number or if it falls out the expected data range.
   */
  public boolean isValid(final Number toTest) {
    final boolean result;

    if (null == toTest) {
      // The field should be numerical, it isn't ergo ... not valid
      result = false;
    } else {

      final boolean maxValComparison;
      if (null == this.getMaximumValue()) {
        maxValComparison = true;
      } else {
        maxValComparison = toTest.doubleValue() <= this.getMaximumValue().doubleValue();
      }

      final boolean minValComparison;
      if (null == this.getMinimumValue()) {
        minValComparison = true;
      } else {
        minValComparison = toTest.doubleValue() >= this.getMinimumValue().doubleValue();
      }

      result = maxValComparison && minValComparison;
    }

    return result;
  }


  /**
   * Attempts to confirm the supplied value is a whole number and will return it is true.
   *
   * @param toConvert the object to test (if Number or a String holding a valid whole number.
   * @return null .. or a valid Number.
   */
  protected abstract N getNumber(Object toConvert);

  public String getUnit() {
    return this.unit;
  }

  public void setUnit(final String unitName) {
    this.unit = null == unitName || unitName.isBlank() ? ValidationRuleConstants.NO_UNITS : unitName;
  }
}
