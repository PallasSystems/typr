package uk.pallas.systems.typr.rest.entities.v1.validation.multi;

import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.multi.RuleWrapper;
import uk.pallas.systems.typr.rest.entities.v1.utils.DTOFactory;
import uk.pallas.systems.typr.rest.entities.v1.validation.EnumValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.StringValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;

import java.util.Objects;

public abstract class AbstractRuleWrapperDTO implements RuleWrapper {
    /** Static Logger for the class. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRuleWrapperDTO.class);

    /** Validation for the field definition. */
    @Schema(description = "Validation for the field definition.",
            anyOf={ DoubleValidationRuleDTO.class, EnumValidationRuleDTO.class, LongValidationRuleDTO.class,
                    StringValidationRuleDTO.class })
    private ValidationRule rule;


    /**
     * Default constructor, sets the country code to undefined.
     */
    public AbstractRuleWrapperDTO() {
        this((ValidationRule) null);
    }

    /**
     * Copy constructor, creates a duplicate of the object supplied.
     * @param wrapper the object to copy
     */
    public AbstractRuleWrapperDTO(final AbstractRuleWrapperDTO wrapper) {
        this(null == wrapper ? null : wrapper.getRule());
    }

    /**
     * Creates a new instance of the wrapper and populates it with the required settings
     * @param value the rule we need to wrap with a different identifier.
     */
    public AbstractRuleWrapperDTO(final ValidationRule value) {
        super();

        this.rule = value;
    }

    /**
     * Compares the supplied object to this one, it checks the supplied object is a FieldDefinition and then checks
     * the definition applies validation, its name and description to see if they are matching objects.
     *
     * @param toCompare the object to compare (can be null or a child class, etc..)
     * @return false if the name/validation and description fields in a field definition are
     *        different (or it isn't a field definition).
     */
    @Override
    public boolean equals(final Object toCompare) {

        final boolean result;
        if (this == toCompare) {
            result = true;
        } else if (toCompare instanceof RuleWrapper) {
            final RuleWrapper that = (RuleWrapper) toCompare;
            result = super.equals(toCompare) && Objects.equals(this.getRule(), that.getRule());
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Generates a Unique hashcode for the field definition class.
     * @return a valid integer representation of this object,
     */
    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(this.getRule());
    }

    /**
     * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
     * @return non null value (if field definition is valid).
     */
    public ValidationRule getRule() {
        return this.rule;
    }

    /**
     * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
     * @param value the rules to add to our field definition
     */
    public void setRule(final ValidationRule value) {
        this.rule = DTOFactory.getValidationRuleDTO(value);
    }
}
