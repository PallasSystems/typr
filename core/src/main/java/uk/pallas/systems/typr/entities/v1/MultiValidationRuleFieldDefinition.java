package uk.pallas.systems.typr.entities.v1;

import uk.pallas.systems.typr.entities.v1.validation.multi.RuleWrapper;

import java.util.Collection;

public interface MultiValidationRuleFieldDefinition extends FieldDefinition {

    Collection<String> getRulesPassed(final Object toTest);

    /**
     * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
     * @return non null value (if field definition is valid).
     */
    Collection<RuleWrapper> getRules();

    /**
     * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
     * @param rule the rules to add to our field definition
     */
    void setRules(final Collection<RuleWrapper> rule);

}
