package uk.pallas.systems.typr.entities.v1.validation.wrapper;

import com.neovisionaries.i18n.CountryCode;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

public interface CountryCodeWrapper  extends ValidationRule {
  /**
   * The specific country this rule is associated with.
   *
   * @return UNDEFINED or a valid country code.
   */
  CountryCode getCountryCode();

  /**
   * Sets the specific country this rule is associated with.
   *
   * @param code - UNDEFINED or a valid country code.
   */
  void setCountryCode(CountryCode code);

  /**
   * A list of rules this field definition can validate.
   *
   * @return non null value (if field definition is valid).
   */
  ValidationRule getRule();

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param rule the rules to add to our field definition
   */
  void setRule(ValidationRule rule);
}
