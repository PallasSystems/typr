package uk.pallas.systems.typr.rest.entities.v1.validation.multi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neovisionaries.i18n.CountryCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.multi.CountryCodeRuleWrapper;
import uk.pallas.systems.typr.rest.entities.v1.utils.DTOFactory;
import uk.pallas.systems.typr.rest.entities.v1.validation.EnumValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.StringValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;


public class CountryCodeRuleWrapperDTO implements CountryCodeRuleWrapper {

  /**
   * Some rules (e.g. post code, zip code, etc.. are unique to a specific country, allows us to be country specific.
   */
  @Schema(example = "UK", nullable = false,
    description = "Some rules (e.g. post code, zip code, etc.. are unique to a specific country, allows us to be "
      + "country specific.")
  private CountryCode countryCode;

  /**
   * Countries can have .
   */
  @Schema(description = "Name of the field definition e.g. post code.",
    example = "Post Code",
    nullable = false)
  @Size(min = 1, max = 100)
  private String name;

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
    this(CountryCode.UNDEFINED, null);
  }

  /**
   * Copy constructor, creates a duplicate of the object supplied.
   *
   * @param wrapper the object to copy
   */
  public CountryCodeRuleWrapperDTO(final CountryCodeRuleWrapper wrapper) {
    this(null == wrapper ? CountryCode.UNDEFINED : wrapper.getCountryCode(),
      null == wrapper ? null : wrapper.getRule());
  }

  /**
   * Creates a new instance of the wrapper and populates it with the required settings.
   *
   * @param code a country specific identifier for the rule
   * @param validRule the rule we need to wrap with a different identifier.
   */
  public CountryCodeRuleWrapperDTO(final CountryCode code, final ValidationRule validRule) {
    if (null == code) {
      this.countryCode = CountryCode.UNDEFINED;
    } else {
      this.countryCode = code;
    }

    this.rule = validRule;
  }

  /**
   * Compares the supplied object to this one, it checks the supplied object is a FieldDefinition and then checks
   * the definition applies validation, its name and description to see if they are matching objects.
   *
   * @param toCompare the object to compare (can be null or a child class, etc..)
   * @return false if the name/validation and description fields in a field definition are
   * different (or it isn't a field definition).
   */
  @Override
  public boolean equals(final Object toCompare) {

    final boolean result;
    if (this == toCompare) {
      result = true;
    } else if (toCompare instanceof CountryCodeRuleWrapper that) {
      result = super.equals(toCompare) && Objects.equals(this.getCountryCode(), that.getCountryCode())
        && Objects.equals(this.getName(), that.getName())
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
    return super.hashCode() + Objects.hash(this.getCountryCode(), this.getName(), this.getRule());
  }

  @Override
  public CountryCode getCountryCode() {
    return this.countryCode;
  }

  @Override
  public void setCountryCode(final CountryCode code) {
    if (null == code) {
      this.countryCode = CountryCode.UNDEFINED;
    } else {
      this.countryCode = code;
    }
  }

  /**
   * Retrieves the name of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
   *
   * @return non null value (if field definition is valid).
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param identifier the new name for the field definition value
   */
  @Override
  public void setName(final String identifier) {
    this.name = identifier;
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
    return this.rule;
  }

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param value the rules to add to our field definition
   */
  public void setRule(final ValidationRule value) {
    this.rule = DTOFactory.getValidationRuleDTO(value);
  }

  /**
   * Defines the Country code and rule name under the to string and supplies Rule details.
   *
   * @return a valid String with field set assuming they are not null.
   */
  @Override
  public String toString() {
    return String.format("%s - %s with Rule: %s", this.getCountryCode(), this.getName(), this.getRule());
  }
}
