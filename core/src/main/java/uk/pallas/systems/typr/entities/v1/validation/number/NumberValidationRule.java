package uk.pallas.systems.typr.entities.v1.validation.number;

import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

public interface NumberValidationRule<N extends Number> extends ValidationRule {
  /**
   * If the field is measured within a specific unit this will identify the unit allowing dynamic translation.
   * @return UNDEFINED is no unit is specified/required.
   */
  String getUnit();

  /**
   * Allows you to specify a specific unit for the numeric field value.
   * @param unit null/invalid will result in the field being set to UNDEFINED
   */
  void setUnit(String unit);

  /**
   * Retrieves the maximum alue that the field definition allows.
   *
   * @return null is allowed (or a valid Long value)
   */
  N getMaximumValue();

  /**
   * Sets the maximum value (can be null) for the field definition to apply to the data field during validation.
   *
   * @param maximum the maximum possible value
   */
  void setMaximumValue(N maximum);

  /**
   * Retrieves the minimum value that the field definition allows.
   *
   * @return null is allowed (or a valid Long value)
   */
  N getMinimumValue();

  /**
   * Sets the mainimum value (can be null) for the field definition to apply to the data field during validation.
   *
   * @param minimum the minimum possible valid value
   */
  void setMinimumValue(N minimum);

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
  boolean isValid(Number toTest);
}
