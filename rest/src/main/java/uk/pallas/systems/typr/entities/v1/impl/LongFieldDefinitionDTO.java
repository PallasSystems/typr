package uk.pallas.systems.typr.entities.v1.impl;

import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.NumberFieldDefinition;
import uk.pallas.systems.typr.entities.v1.LongFieldDefinition;

import java.util.Collection;

public class LongFieldDefinitionDTO extends AbstractNumberFieldDefinitionDTO<Long> implements LongFieldDefinition {

    /**
     * Default constructor, sets everything to null and makes validation optional.
     */
    public LongFieldDefinitionDTO() {
        this(null);
    }

    /**
     * Constructor, allows us to set the internal abstract fields
     *
     * @param max The upper bound allowed for the field
     * @param min The lower bound allowed for the field
     * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc...
     * @param shortName a shorted version of the Field Name (e.g. if the Field Name is Mobile Country Code the short name might be MCC).
     * @param desc Can you describe what the field concerns?
     * @param values what categories should be associated with the field definition.
     */
    public LongFieldDefinitionDTO(final Long max, final Long min, final String fieldName,
                                  final String shortName, final String desc, final Collection<Category> values) {
        super(max, min, fieldName, shortName, desc, values);
    }

    /**
     * Copy Constructor, passes up to parent Copy Constructor and sets Minimum/Maximum values.
     * @param data the object to create a duplicate off.
     */
    public LongFieldDefinitionDTO(final NumberFieldDefinition<Long> data) {
        super(data);
    }

    /**
     * Attempts to confirm the supplied value is a whole number and will return it is true.
     *
     * @param toConvert the object to test (if Number or a String holding a valid whole number.
     * @return null .. or a valid Number.
     */
    protected Long getNumber(final Object toConvert) {

        Long result = null;

        if (toConvert instanceof Number) {
            result = ((Number) toConvert).longValue();
        } else if (null != toConvert) {
            try {
                result = Long.parseLong(toConvert.toString());
            } catch (final NumberFormatException exception) {
                // TODO lets catch this fail and log it.
            }
        }

        return result;
    }
}