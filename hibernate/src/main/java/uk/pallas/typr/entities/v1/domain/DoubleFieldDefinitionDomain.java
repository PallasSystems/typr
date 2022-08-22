package uk.pallas.typr.entities.v1.domain;

import uk.pallas.typr.entities.v1.Category;
import uk.pallas.typr.entities.v1.DoubleFieldDefinition;
import uk.pallas.typr.entities.v1.NumberFieldDefinition;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "double_type_definitions")
public class DoubleFieldDefinitionDomain extends AbstractNumberFieldDefinitionDomain<Double> implements DoubleFieldDefinition {

    /**
     * Default constructor, sets everything to null and makes validation optional.
     */
    public DoubleFieldDefinitionDomain() {
        this(null);
    }

    /**
     * Constructor, allows us to set the internal abstract fields
     *
     * @param max                The upper bound allowed for the field
     * @param min                the lower bound allowed for the field
     * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc...
     * @param shortName a shorted version of the Field Name (e.g. if the Field Name is Mobile Country Code the short name might be MCC).
     * @param desc Can you describe what the field concerns?
     * @param values what categories should be associated with the field definition.
     */
    public DoubleFieldDefinitionDomain(final Double max, final Double min, final String fieldName,
                                       final String shortName, final String desc, final Collection<Category> values) {
        super(max, min, fieldName, shortName, desc, values);
    }

    /**
     * Copy Constructor, passes up to parent Copy Constructor and sets Minimum/Maximum values.
     * @param data the object to create a duplicate off.
     */
    public DoubleFieldDefinitionDomain(final NumberFieldDefinition<Double> data) {
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
