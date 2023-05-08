package uk.pallas.systems.typr.domain.entities.v1.validation;

import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class EnumValidationRuleDomain extends AbstractValidationRuleDomain implements EnumValidationRule {

    @ElementCollection
    private Collection< String > enumerates = new ArrayList<>();

    /**
     * Default constructor, sets everything to null and makes validation optional.
     */
    public EnumValidationRuleDomain() {
        this(null, null);
    }

    /**
     * Constructor, allows us to set the internal abstract fields
     * @param detailedDescription the description of the rule
     * @param data Enumerate values we need to support.
     */
    public EnumValidationRuleDomain(final String detailedDescription, final Collection<String> data) {
        super(detailedDescription);

        if (null != data) {
            this.enumerates.addAll(data);
        }
    }

    /**
     * Copy Constructor, passes up to parent Copy Constructor.
     * @param data the object to create a duplicate off.
     */
    public EnumValidationRuleDomain(final EnumValidationRule data) {
        this(null == data ? null : data.getDescription(), null == data ? null : data.getEnumerates());
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
        } else if (toCompare instanceof EnumValidationRule) {
            final EnumValidationRule that = (EnumValidationRule) toCompare;
            result = super.equals(toCompare)
                    && this.getEnumerates().containsAll(that.getEnumerates())
                    && that.getEnumerates().containsAll(this.getEnumerates());
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
        return Objects.hash(super.hashCode(), this.getEnumerates());
    }


    @Override
    public Collection<String> getEnumerates() {
        return new HashSet<>(this.enumerates);
    }

    @Override
    public void setEnumerates(Collection<String> enumerates) {
        if (null == enumerates) {
            this.enumerates.clear();
        } else {
            this.enumerates.clear();
            this.enumerates.addAll(enumerates);
        }
    }

    @Override
    public String getDetectRegex() {
        return null;
    }

    @Override
    public void setDetectRegex(String validation) {

    }

    @Override
    public String getExtractRegex() {
        return null;
    }

    @Override
    public void setExtractRegex(String validation) {

    }

    /**
     * Is the supplied test object something that matches against our field definition regular expression?
     *
     * If validation optional is set to true this will return true, if the supplied object is null, this will always
     * return false. Otherwise this will call toString and then match the field definition regex tp confirm the object
     * matches our desired value.
     *
     * @param toTest to test is valid
     * @return false if the object fails the validation match.
     */
    @Override
    public boolean isValid(final Object toTest) {
        final boolean result;

        if (toTest instanceof String) {
            // Use to string as it leaves things the most flexible in our regex comparison.
            result = this.isValid((String)toTest);
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Is the supplied test object something that matches against our field definition regular expression?
     *
     * If validation optional is set to true this will return true, if the supplied object is null, this will always
     * return false. Otherwise it will call matches on the supplied string.
     *
     * @param toTest to test is valid
     * @return false if the object fails the validation match.
     */
    @Override
    public boolean isValid(final String toTest) {
        final boolean result;

        if (null == toTest) {
            result = false;
        } else {
            result = this.getEnumerates().contains(toTest);
        }

        return result;
    }
}
