package uk.pallas.systems.typr.entities.v1.validation;

import com.neovisionaries.i18n.CountryCode;

public interface ValidationRule {

    /**
     * Retrieves a hopefully detailed description of the field definition so we can understand what it is for and why it exists.
     * @return a hopefull long valid string (null is possible).
     */
    String getDescription();

    /**
     * Sets the description to attach to this field definition.
     * @param detailedDescription the description to attached (null is ok)
     */
    void setDescription(final String detailedDescription);

    /**
     * Is the supplied test object something that matches against our field definition regular expression?
     *
     * If validation optional is set to true this will return true, if the supplied object is null, this will always
     * return false. Other-wise this will call toString and then match the field definition regex tp confirm the object
     * matches our desired value.
     *
     * @param toTest to test is valid
     * @return false if the object fails the validation match.
     */
    boolean isValid(final Object toTest);
}
