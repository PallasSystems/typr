package uk.pallas.systems.typr.entities.v1;

import java.util.Collection;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

/**
 * This class defines Complex Structured Data field definitions we can assign to various structured data schema's.
 */
public interface FieldDefinition {

  /**
   * Retrieves the shortened name (e.g. Acronym) of the field definition e.g. post code, uk mobile, IPv4, etc..
   *
   * @return non null value (if field definition is valid).
   */
  String getAcronym();

  /**
   * Sets the shortened name (e.g. Acronym) for the field definition e.g. Ipv6, MCC, etc...
   *
   * @param identifier the new name for the field definition value
   */
  void setAcronym(String identifier);

  /**
   * List of categories associated with our type. These are additional ways to define a
   * type for routing/managing a schema.
   *
   * @return an empty list if nothing is supplied.
   */
  Collection<String> getCategories();

  /**
   * Sets the list of categories associated with our type. These are additional ways to
   * define a type for routing/managing a schema.
   *
   * @param values all categories associated with the type.
   */
  void setCategories(Collection<String> values);

  /**
   * Retrieves a hopefully detailed description of the field definition so
   * we can understand what it is for and why it exists.
   *
   * @return a hopefull long valid string (null is possible).
   */
  String getDescription();

  /**
   * Sets the description to attach to this field definition.
   *
   * @param detailedDescription the description to attach (null is ok)
   */
  void setDescription(String detailedDescription);

  /**
   * Retrieves the name of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
   *
   * @return non null value (if field definition is valid).
   */
  String getName();

  /**
   * Sets the name for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param identifier the new name for the field definition value
   */
  void setName(String identifier);

  /**
   * Is the supplied test object something that matches against our field definition regular expression?
   * <p>
   * If validation optional is set to true this will return true, if the supplied object is null, this will always
   * return false. Other-wise this will call toString and then match the field definition regex tp confirm the object
   * matches our desired value.
   *
   * @param toTest to test is valid
   * @return false if the object fails the validation match.
   */
  boolean isValid(Object toTest);

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
  Collection<ValidationRule> getRules();

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param rule the rules to add to our field definition
   */
  void setRules(Collection<ValidationRule> rule);
}
