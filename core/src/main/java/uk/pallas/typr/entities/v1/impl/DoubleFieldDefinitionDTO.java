package uk.pallas.typr.entities.v1.impl;

import uk.pallas.typr.entities.v1.Category;
import uk.pallas.typr.entities.v1.DoubleFieldDefinition;
import uk.pallas.typr.entities.v1.NumberFieldDefinition;

import java.util.Collection;

public class DoubleFieldDefinitionDTO extends AbstractNumberFieldDefinitionDTO<Double> implements DoubleFieldDefinition {

    /**
     * Default constructor, sets everything to null and makes validation optional.
     */
    public DoubleFieldDefinitionDTO() {
        this(null, null, null, null, null);
    }

    /**
     * Constructor, allows us to set the internal abstract fields
     *
     * @param max                The upper bound allowed for the field
     * @param min                the lower bound allowed for the field
     * @param fieldName          What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc..
     * @param desc               Can you describe what the field concerns?
     */
    public DoubleFieldDefinitionDTO(final Double max, final Double min, final String fieldName, final String desc, final Collection<Category> values) {
        super(max, min, fieldName, desc, values);
    }

    /**
     * Copy Constructor, passes up to parent Copy Constructor and sets Minimum/Maximum values.
     * @param data the object to create a duplicate off.
     */
    public DoubleFieldDefinitionDTO(final NumberFieldDefinition<Double> data) {
        super(data);
    }

    /**
     * Attempts to confirm the supplied value is a whole number and will return it is true.
     *
     * @param toConvert the object to test (if Number or a String holding a valid whole number.
     * @return null .. or a valid Number.
     */
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
