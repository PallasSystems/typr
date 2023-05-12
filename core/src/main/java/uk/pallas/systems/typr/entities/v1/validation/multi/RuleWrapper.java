package uk.pallas.systems.typr.entities.v1.validation.multi;

import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

/**
 * The idea of a rule wrapper is to allow us to hold different rules for a field definition, for example postal code
 * is different within each country (e.g. UK Post Code, USA Zip Code, etc..). Similary some things are a construction
 * of other fields (e.g. Hostname will often have a Top Level Domain name included).
 *
 * The wrapper is a base interface for these different combinations to extend.
 */
public interface RuleWrapper {

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
    boolean isValid(final Object toTest);

    /**
     * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
     * @return non null value (if field definition is valid).
     */
    ValidationRule getRule();

    /**
     * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
     * @param rule the rules to add to our field definition
     */
    void setRule(final ValidationRule rule);
}
