package uk.pallas.systems.typr.entities.v1;

public interface NumberFieldDefinition<N extends Number> extends FieldDefinition {

    /**
     * Retrieves the maximum alue that the field definition allows.
     * @return null is allowed (or a valid Long value)
     */
    N getMaximumValue();

    /**
     * Sets the maximum value (can be null) for the field definition to apply to the data field during validation.
     * @param maximum the maximum possible value
     */
    void setMaximumValue(final N maximum);

    /**
     * Retrieves the minimum value that the field definition allows.
     * @return null is allowed (or a valid Long value)
     */
    N getMinimumValue();

    /**
     * Sets the mainimum value (can be null) for the field definition to apply to the data field during validation.
     * @param minimum the minimum possible valid value
     */
    void setMinimumValue(final N minimum);

    /**
     * This will compare the incoming object and see if it is a valid whole number and if the value is a valid
     * number do we have a maximum or a minimum data range? If there is a upper or lower value (they can be null) then
     * it tests the supplied object is less than/equal or greater than/equal as appropriate.
     *
     * If validationOptional is set to true this will return true by default (no further checks).
     *
     * @param toTest to test is valid
     * @return false if the object isn't a number or if it falls out the expected data range.
     */
    boolean isValid(final Number toTest);
}
