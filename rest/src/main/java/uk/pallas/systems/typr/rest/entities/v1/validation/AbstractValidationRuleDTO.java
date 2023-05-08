package uk.pallas.systems.typr.rest.entities.v1.validation;

import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

import java.util.Objects;

public abstract class AbstractValidationRuleDTO implements ValidationRule {
    /** Detailed description of the field definition. */
    private String description;

    /** Default class constructor. */
    public AbstractValidationRuleDTO() {
        super();
    }

    /** Class Constructor. */
    public AbstractValidationRuleDTO(final String detailedDescription) {
        super();

        this.description = detailedDescription;
    }

    /** Copy Constructor. */
    public AbstractValidationRuleDTO(final ValidationRule data) {
        this(null == data ? null : data.getDescription());
    }

    /**
     * Retrieves a hopefully detailed description of the field definition so we can understand what it is for and why it exists.
     * @return a hopefull long valid string (null is possible).
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description to attach to this field definition.
     * @param detailedDescription the description to attache (null is ok)
     */
    @Override
    public void setDescription(final String detailedDescription) {
        this.description = detailedDescription;
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
        } else if (toCompare instanceof ValidationRule) {
            final ValidationRule that = (ValidationRule) toCompare;
            result = Objects.equals(this.getDescription(), that.getDescription());
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
        return Objects.hash(this.getDescription());
    }
}
