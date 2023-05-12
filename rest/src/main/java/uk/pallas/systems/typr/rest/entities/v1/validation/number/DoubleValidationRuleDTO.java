package uk.pallas.systems.typr.rest.entities.v1.validation.number;

import io.swagger.v3.oas.annotations.media.Schema;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;

/**
 * This rule assumes the field is a valid double number and allows us to place ranges on what value the field can be
 * (e.g. Direction could have a Min 0.0 and Max 360.0. So then when a System supplied 342.5 we know its valid.
 */
@Schema(description = "This rule assumes the field is a valid double number and allows us to place ranges on what "
        + "value the field can be (e.g. Direction could have a Min 0.0 and Max 360.0. So then when a System supplied "
        + "342.5 we know its valid.")
public class DoubleValidationRuleDTO extends AbstractNumberValidationRuleDTO<Double> implements DoubleValidationRule {

    /**
     * Default constructor, sets everything to null and makes validation optional.
     */
    public DoubleValidationRuleDTO() {
        this(null);
    }

    /**
     * Copy Constructor, passes up to parent Copy Constructor and sets Minimum/Maximum values.
     * @param data the object to create a duplicate off.
     */
    public DoubleValidationRuleDTO(final DoubleValidationRule data) {
        this(null == data ? null : data.getMaximumValue(), null == data ? null : data.getMinimumValue(),
                null == data ? null : data.getDescription());
    }

    /**
     * Constructor, allows us to set the internal abstract fields
     *
     * @param max                The upper bound allowed for the field
     * @param min                the lower bound allowed for the field
     */
    public DoubleValidationRuleDTO(final Double max, final Double min, final String detailedDescription) {
        super(max, min, detailedDescription);
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
