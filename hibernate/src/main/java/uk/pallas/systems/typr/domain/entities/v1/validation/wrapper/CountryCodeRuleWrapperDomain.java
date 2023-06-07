package uk.pallas.systems.typr.domain.entities.v1.validation.wrapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.pallas.systems.typr.domain.entities.v1.validation.EnumValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;

@Entity
@Table(name = "wrap_country_code")
public class CountryCodeRuleWrapperDomain implements CountryCodeWrapper {
  /**
   * Static Logger for the class.
   */
  private static final Log LOGGER = LogFactory.getLog(CountryCodeRuleWrapperDomain.class);

  /**
   * Primary key for storing validation rules.
   */
  @Id
  @GeneratedValue
  private Long identifier;

  /**
   * Some rules (e.g. post code, zip code, etc.. are unique to a specific country, allows us to be country specific.
   */
  @Column(nullable = false)
  private String countryCode;

  /**
   * Validation for the field definition.
   */
  @OneToOne
  private EnumValidationRuleDomain enumRule;

  /**
   * Validation for the field definition.
   */
  @OneToOne
  private StringValidationRuleDomain stringRule;

  /**
   * Validation for the field definition.
   */
  @OneToOne
  private DoubleValidationRuleDomain doubleRule;

  /**
   * Validation for the field definition.
   */
  @OneToOne
  private LongValidationRuleDomain longRule;

  /**
   * Default constructor, sets the country code to undefined.
   */
  public CountryCodeRuleWrapperDomain() {
    this(ValidationRuleConstants.DEFAULT_COUNTRY_CODE, null);
  }

  /**
   * Copy constructor, creates a duplicate of the object supplied.
   *
   * @param wrapper the object to copy
   */
  public CountryCodeRuleWrapperDomain(final CountryCodeWrapper wrapper) {
    this(null == wrapper ? ValidationRuleConstants.DEFAULT_COUNTRY_CODE : wrapper.getCountryCode(),
      null == wrapper ? null : wrapper.getRule());
  }

  /**
   * Creates a new instance of the wrapper and populates it with the required settings.
   *
   * @param code a country specific identifier for the rule
   * @param validRule the rule we need to wrap with a different identifier.
   */
  public CountryCodeRuleWrapperDomain(final String code, final ValidationRule validRule) {

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
    final ValidationRule rule = this.getRule();
    if (null == rule) {
      result = null;
    } else {
      result = rule.getDescription();
    }

    return result;
  }

  /**
   * Sets the description to attach to this field definition.
   *
   * @param detailedDescription the description to attache (null is ok)
   */
  @Override
  public void setDescription(final String detailedDescription) {
    final ValidationRule rule = this.getRule();
    if (null != rule) {
      rule.setDescription(detailedDescription);
    }
  }

  public Long getIdentifier() {
    return identifier;
  }

  public void setIdentifier(final Long id) {
    this.identifier = id;
  }

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
  @Override
  public boolean isValid(final Object toTest) {
    final boolean result;

    if (null == this.getRule()) {
      result = false;
      LOGGER.warn(String.format("isValid - No Rule to test object: %s", toTest));
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
  public DoubleValidationRule getDoubleRule() {
    return null == this.doubleRule ? null : new DoubleValidationRuleDomain(this.doubleRule);
  }

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param value the rules to add to our field definition
   */
  public void setDoubleRule(final DoubleValidationRule value) {
    this.doubleRule = new DoubleValidationRuleDomain(value);
  }

  /**
   * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
   *
   * @return non null value (if field definition is valid).
   */
  public EnumValidationRule getEnumRule() {
    return null == this.enumRule ? null : new EnumValidationRuleDomain(this.enumRule);
  }

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param value the rules to add to our field definition
   */
  public void setEnumRule(final EnumValidationRule value) {
    this.enumRule = new EnumValidationRuleDomain(value);
  }

  /**
   * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
   *
   * @return non null value (if field definition is valid).
   */
  public LongValidationRule getLongRule() {
    return null == this.longRule ? null : new LongValidationRuleDomain(this.longRule);
  }

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param value the rules to add to our field definition
   */
  public void setLongRule(final LongValidationRule value) {
    this.longRule = new LongValidationRuleDomain(value);
  }

  /**
   * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
   *
   * @return non null value (if field definition is valid).
   */
  public StringValidationRule getStringRule() {
    return null == this.stringRule ? null : new StringValidationRuleDomain(this.stringRule);
  }

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param value the rules to add to our field definition
   */
  public void setStringRule(final StringValidationRule value) {
    this.stringRule = new StringValidationRuleDomain(value);
  }

  /**
   * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
   *
   * @return non null value (if field definition is valid).
   */
  public ValidationRule getRule() {
    final ValidationRule result;

    if (null == this.getDoubleRule()) {
      if (null == this.getEnumRule()) {
        if (null == this.getLongRule()) {
          if (null == this.getStringRule()) {
            result = null;
          } else {
            result = this.getStringRule();
          }
        } else {
          result = this.getLongRule();
        }
      } else {
        result = this.getEnumRule();
      }
    } else {
      result = this.getDoubleRule();
    }

    return result;
  }

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param value the rules to add to our field definition
   */
  public void setRule(final ValidationRule value) {

    this.doubleRule = null;
    this.enumRule = null;
    this.longRule = null;
    this.stringRule = null;

    if (value instanceof DoubleValidationRule) {
      this.doubleRule = new DoubleValidationRuleDomain((DoubleValidationRule) value);
    } else if (value instanceof EnumValidationRule) {
      this.enumRule = new EnumValidationRuleDomain((EnumValidationRule) value);
    } else if (value instanceof LongValidationRule) {
      this.longRule = new LongValidationRuleDomain((LongValidationRule) value);
    } else if (value instanceof StringValidationRule) {
      this.stringRule = new StringValidationRuleDomain((StringValidationRule) value);
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
