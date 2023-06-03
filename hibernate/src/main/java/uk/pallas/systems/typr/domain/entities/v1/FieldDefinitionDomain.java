package uk.pallas.systems.typr.domain.entities.v1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.pallas.systems.typr.domain.entities.v1.validation.wrapper.CountryCodeRuleWrapperDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.EnumValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;

@Entity
@Table(name = "field_def")
public class FieldDefinitionDomain implements FieldDefinition {
  /**
   * Static Logger for the class.
   */
  private static final Log LOGGER = LogFactory.getLog(FieldDefinitionDomain.class);
  /**
   * List of categories assocaited with our type.
   */
  @Column(nullable = true)
  @ManyToMany
  private final Collection<CategoryDomain> categories;
  /**
   * The shortened name (e.g. Acronym) of the field definition e.g. post code, uk mobile, IPv4, etc..
   */
  @Column(length = 4096, nullable = true)
  private String acronym;
  /**
   * Detailed description of the field definition.
   */
  @Column(length = 4096, nullable = true)
  private String description;

  /**
   * Name of the field definition e.g. post code, uk mobile.
   */
  @Id
  @Column(length = 100, nullable = false)
  private String name;

  /**
   * Validation for the field definition.
   */
  @OneToMany
  private Collection<EnumValidationRuleDomain> enumRules;

  /**
   * Validation for the field definition.
   */
  @OneToMany
  private Collection<StringValidationRuleDomain> stringRules;

  /**
   * Validation for the field definition.
   */
  @OneToMany
  private Collection<DoubleValidationRuleDomain> doubleRules;

  /**
   * Validation for the field definition.
   */
  @OneToMany
  private Collection<LongValidationRuleDomain> longRules;

  /**
   * List of Validation rules for the field definition.
   */
  @OneToMany
  private Collection<CountryCodeRuleWrapperDomain> countryCodeRules;

  /**
   * Default Class Constructor.
   */
  public FieldDefinitionDomain() {
    this(null);
  }

  /**
   * Copy Constructor.
   *
   * @param data - the object to duplicate
   */
  public FieldDefinitionDomain(final FieldDefinition data) {
    this(null != data ? data.getAcronym() : null, null != data ? data.getCategories() : null,
      null != data ? data.getDescription() : null, null != data ? data.getName() : null,
      null != data ? data.getRules() : null);
  }

  /**
   * Class Constructor.
   *
   * @param acryo     The shortened name
   * @param cats      The categories associated with our object
   * @param desc      the description of the rule
   * @param fieldName the name for the rule
   * @param rules     the validation rules to add to the object.
   */
  public FieldDefinitionDomain(final String acryo, final Collection<Category> cats,
                               final String desc, final String fieldName, final Collection<ValidationRule> rules) {
    this.acronym = acryo;
    this.description = desc;
    this.name = fieldName;

    this.categories = new HashSet<>();
    if (null != cats) {
      this.categories.addAll(cats.stream()
        .map(value -> new CategoryDomain(value))
        .collect(Collectors.toSet()));
    }

    this.countryCodeRules = new HashSet<>();
    this.doubleRules = new HashSet<>();
    this.enumRules = new HashSet<>();
    this.longRules = new HashSet<>();
    this.stringRules = new HashSet<>();

    this.setRules(rules);
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
    } else if (toCompare instanceof FieldDefinition that) {
      result = Objects.equals(this.getName(), that.getName())
        && Objects.equals(this.getAcronym(), that.getAcronym())
        && Objects.equals(this.getDescription(), that.getDescription())
        && this.getCategories().containsAll(that.getCategories())
        && that.getCategories().containsAll(this.getCategories())
        && this.getRules().containsAll(that.getRules())
        && that.getRules().containsAll(this.getRules());
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
    return Objects.hash(this.getAcronym(), this.getName(), this.getDescription(), this.getRules());
  }

  /**
   * Retrieves a list of all rules which have confirmed the incoming object is valid.
   *
   * @param toTest the object to test against the rules stored within this object
   * @return an empty list or a list of identifiers for rules.
   */
  public Collection<String> getRulesPassed(final Object toTest) {
    final Collection<String> passed = new HashSet<>();

    final Collection<ValidationRule> rules = new HashSet<>();
    rules.addAll(this.countryCodeRules);
    rules.addAll(this.doubleRules);
    rules.addAll(this.enumRules);
    rules.addAll(this.longRules);
    rules.addAll(this.stringRules);

    for (final ValidationRule rule : rules) {
      if (rule.isValid(toTest)) {
        passed.add(rule.toString());
      }
    }

    return passed;
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
    final Collection<String> results = this.getRulesPassed(toTest);
    return null != results && !results.isEmpty();
  }

  /**
   * Retrieves the shortened name (e.g. Acronym) of the field definition e.g. post code, uk mobile, IPv4, etc..
   *
   * @return non null value (if field definition is valid).
   */
  @Override
  public String getAcronym() {
    return this.acronym;
  }

  /**
   * Sets the shortened name (e.g. Acronym) for the field definition e.g. Ipv6, MCC, etc...
   *
   * @param identifier the new name for the field definition value
   */
  @Override
  public void setAcronym(final String identifier) {
    this.acronym = identifier;
  }

  /**
   * List of categories assocaited with our type. These are additional ways to define a type for routing/managing
   * a schema.
   *
   * @return an empty list if nothing is supplied.
   */
  @Override
  public Collection<Category> getCategories() {
    return new HashSet<>(this.categories);
  }

  /**
   * Sets the list of categories assocaited with our type. These are additional ways to define a type for
   * routing/managing a schema.
   *
   * @param values all categories associated with the type.
   */
  @Override
  public void setCategories(final Collection<Category> values) {
    if (null == values) {
      this.categories.clear();
    } else {
      this.categories.clear();
      this.categories.addAll(values.stream()
        .map(value -> new CategoryDomain(value))
        .collect(Collectors.toSet()));
    }
  }

  /**
   * Retrieves a hopefully detailed description of the field definition so we can understand what it is for
   * and why it exists.
   *
   * @return a hopefull long valid string (null is possible).
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * Sets the description to attach to this field definition.
   *
   * @param detailedDescription the description to attached (null is ok)
   */
  @Override
  public void setDescription(final String detailedDescription) {
    this.description = detailedDescription;
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
   * A list of rules this field definition can validate.
   *
   * @return non null value (if field definition is valid).
   */
  public Collection<ValidationRule> getRules() {
    final Collection<ValidationRule> results = new HashSet<>();

    results.addAll(this.countryCodeRules.stream()
      .map(value -> new CountryCodeRuleWrapperDomain(value)).collect(Collectors.toSet()));

    results.addAll(this.doubleRules.stream()
      .map(value -> new DoubleValidationRuleDomain(value)).collect(Collectors.toSet()));

    results.addAll(this.enumRules.stream()
      .map(value -> new EnumValidationRuleDomain(value)).collect(Collectors.toSet()));

    results.addAll(this.longRules.stream()
      .map(value -> new LongValidationRuleDomain(value)).collect(Collectors.toSet()));

    results.addAll(this.stringRules.stream()
      .map(value -> new StringValidationRuleDomain(value)).collect(Collectors.toSet()));

    return results;
  }

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param rules the rules to add to our field definition
   */
  public void setRules(final Collection<ValidationRule> rules) {

    this.countryCodeRules.clear();
    this.doubleRules.clear();
    this.enumRules.clear();
    this.longRules.clear();
    this.stringRules.clear();

    if (null != rules) {
      for (final ValidationRule rule : rules) {
        if (rule instanceof CountryCodeWrapper) {
          this.countryCodeRules.add(new CountryCodeRuleWrapperDomain((CountryCodeWrapper)rule));
        } else if (rule instanceof DoubleValidationRule) {
          this.doubleRules.add(new DoubleValidationRuleDomain((DoubleValidationRule)rule));
        } else if (rule instanceof EnumValidationRule) {
          this.enumRules.add(new EnumValidationRuleDomain((EnumValidationRule)rule));
        } else if (rule instanceof LongValidationRule) {
          this.longRules.add(new LongValidationRuleDomain((LongValidationRule)rule));
        } else if (rule instanceof StringValidationRule) {
          this.stringRules.add(new StringValidationRuleDomain((StringValidationRule)rule));
        }
      }
    }
  }
}
