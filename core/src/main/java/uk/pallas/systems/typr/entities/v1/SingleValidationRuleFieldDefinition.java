package uk.pallas.systems.typr.entities.v1;

import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

/**
 * Used to define a single Field type and a rule to be associated with it. For example a Mobile Country Code
 * is an international type and so this could represent a MCC value.
 */
public interface SingleValidationRuleFieldDefinition extends FieldDefinition {

  /**
   * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
   *
   * @return non null value (if field definition is valid).
   */
  ValidationRule getRule();

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param rule the rules to add to our field definition
   */
  void setRule(final ValidationRule rule);

}
