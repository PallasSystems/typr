package uk.pallas.systems.typr.entities.v1;

import java.util.Collection;
import uk.pallas.systems.typr.entities.v1.validation.multi.RuleWrapper;

/**
 * The idea of a rule wrapper is to allow us to hold different rules for a field definition, for example postal code
 * is different within each country (e.g. UK Post Code, USA Zip Code, etc..). Similary some things are a construction
 * of other fields (e.g. Hostname will often have a Top Level Domain name included).
 * <p>
 * The wrapper is a base interface for these different combinations to extend.
 */
public interface MultiValidationRuleFieldDefinition extends FieldDefinition {

  /**
   * Retrieves a list of all rules which have confirmed the incoming object is valid.
   *
   * @param toTest the object to test against the rules stored within this object
   * @return an empty list or a list of identifiers for rules.
   */
  Collection<String> getRulesPassed(Object toTest);

  /**
   * A list of rules this field definition can validate.
   *
   * @return non null value (if field definition is valid).
   */
  Collection<RuleWrapper> getRules();

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param rule the rules to add to our field definition
   */
  void setRules(Collection<RuleWrapper> rule);

}
