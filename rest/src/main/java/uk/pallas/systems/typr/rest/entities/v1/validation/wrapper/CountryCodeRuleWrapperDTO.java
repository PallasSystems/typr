package uk.pallas.systems.typr.rest.entities.v1.validation.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;
import uk.pallas.systems.typr.rest.entities.v1.validation.EnumValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.StringValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;

public class CountryCodeRuleWrapperDTO implements CountryCodeWrapper {
  /**
   * Static Logger for the class.
   */
  private static final Log LOGGER = LogFactory.getLog(CountryCodeRuleWrapperDTO.class);

  /**
   * Some rules (e.g. post code, zip code, etc.. are unique to a specific country, allows us to be country specific.
   */
  @Schema(example = "UK",
    description = "Some rules (e.g. post code, zip code, etc.. are unique to a specific country, allows us to be "
      + "country specific.")
  private String countryCode;

  /**
   * Validation for the field definition.
   */
  @Schema(description = "Validation for the field definition.",
    anyOf = {DoubleValidationRuleDTO.class, EnumValidationRuleDTO.class, LongValidationRuleDTO.class,
      StringValidationRuleDTO.class})
  private ValidationRule rule;

  /**
   * Default constructor, sets the country code to undefined.
   */
  public CountryCodeRuleWrapperDTO() {
    this(ValidationRuleConstants.DEFAULT_COUNTRY_CODE, null);
  }

  /**
   * Copy constructor, creates a duplicate of the object supplied.
   *
   * @param wrapper the object to copy
   */
  public CountryCodeRuleWrapperDTO(final CountryCodeWrapper wrapper) {
    this(null == wrapper ? ValidationRuleConstants.DEFAULT_COUNTRY_CODE : wrapper.getCountryCode(),
      null == wrapper ? null : wrapper.getRule());
  }

  /**
   * Creates a new instance of the wrapper and populates it with the required settings.
   *
   * @param code a country specific identifier for the rule
   * @param validRule the rule we need to wrap with a different identifier.
   */
  public CountryCodeRuleWrapperDTO(final String code, final ValidationRule validRule) {
    this.countryCode = Objects.requireNonNullElse(code, ValidationRuleConstants.DEFAULT_COUNTRY_CODE);

    this.setRule(validRule);
  }

  /**
   * Compares the supplied object to this one, it checks the supplied object is a FieldDefinition and then checks
   * the definition applies validation, its name and description to see if they are matching objects.
   *
   * @param toCompare the object to compare (can be null or a child class, etc..)
   * @return false if the name/validation and description fields in a field definition are
   *               different (or it isn't a field definition).
   */
  @Override
  public boolean equals(final Object toCompare) {

    final boolean result;
    if (this == toCompare) {
      result = true;
    } else if (toCompare instanceof CountryCodeWrapper that) {
      result = Objects.equals(this.getCountryCode(), that.getCountryCode())
        && Objects.equals(this.getRule(), that.getRule());
    } else {
      result = false;
    }

    return result;
  }

  /**
   * Generates a Unique hashcode for the field definition class.
   *
   * @return a valid integer representation of this object,
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getCountryCode(), this.getRule());
  }

  @Override
  public String getCountryCode() {
    return this.countryCode;
  }

  @Override
  public void setCountryCode(final String code) {
    this.countryCode = Objects.requireNonNullElse(code, ValidationRuleConstants.DEFAULT_COUNTRY_CODE);
  }

  /**
   * Retrieves a hopefully detailed description of the field definition so we can understand what it is for and
   * why it exists.
   *
   * @return a hopefull long valid string (null is possible).
   */
  @Override
  public String getDescription() {

    final String result;
    final ValidationRule validationRule = this.getRule();
    if (null == validationRule) {
      result = null;
    } else {
      result = validationRule.getDescription();
    }

    return result;
  }

  /**
   * Sets the description to attach to this field definition.
   *
   * @param detailedDescription the description to attach (null is ok)
   */
  @Override
  public void setDescription(final String detailedDescription) {
    final ValidationRule validationRule = this.getRule();
    if (null != validationRule) {
      validationRule.setDescription(detailedDescription);
    }
  }

  /**
   * This method will supply the parameter into the Validation rule the wrapper holds, if no validation rule
   * exists this will return false. Otherwise it will return with the rules response.
   *
   * @param toTest to test is valid
   * @return false if the object is invalid, there is no rule or
   */
  @Override
  @JsonIgnore
  public boolean isValid(final Object toTest) {
    final boolean result;

    if (null == this.getRule()) {
      result = false;
    } else {
      result = this.getRule().isValid(toTest);
    }

    return result;
  }

  /**
   * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
   *
   * @return non null value (if field definition is valid).
   */
  public ValidationRule getRule() {
    final ValidationRule result;

    if (this.rule instanceof DoubleValidationRule) {
      result = new DoubleValidationRuleDTO((DoubleValidationRule) this.rule);
    } else if (this.rule instanceof EnumValidationRule) {
      result = new EnumValidationRuleDTO((EnumValidationRule) this.rule);
    } else if (this.rule instanceof LongValidationRule) {
      result = new LongValidationRuleDTO((LongValidationRule) this.rule);
    } else if (this.rule instanceof StringValidationRule) {
      result = new StringValidationRuleDTO((StringValidationRule) this.rule);
    } else {
      LOGGER.warn(String.format("getRule - Unsupported Rule Type supplied: %s", this.rule));
      result = null;
    }

    return result;
  }

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param value the rules to add to our field definition
   */
  public void setRule(final ValidationRule value) {
    if (value instanceof DoubleValidationRule) {
      this.rule = new DoubleValidationRuleDTO((DoubleValidationRule) value);
    } else if (value instanceof EnumValidationRule) {
      this.rule = new EnumValidationRuleDTO((EnumValidationRule) value);
    } else if (value instanceof LongValidationRule) {
      this.rule = new LongValidationRuleDTO((LongValidationRule) value);
    } else if (value instanceof StringValidationRule) {
      this.rule = new StringValidationRuleDTO((StringValidationRule) value);
    } else {
      LOGGER.warn(String.format("setRule - Unsupported Rule Type supplied: %s", value));
    }
  }

  /**
   * Defines the Country code and rule name under the to string and supplies Rule details.
   *
   * @return a valid String with field set assuming they are not null.
   */
  @Override
  public String toString() {
    return String.format("%s with Rule: %s", this.getCountryCode(), this.getRule());
  }
}
