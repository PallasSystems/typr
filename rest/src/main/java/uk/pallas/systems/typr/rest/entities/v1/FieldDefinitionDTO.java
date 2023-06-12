package uk.pallas.systems.typr.rest.entities.v1;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.TimeValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;
import uk.pallas.systems.typr.rest.entities.v1.validation.EnumValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.StringValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.TimeValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.wrapper.CountryCodeRuleWrapperDTO;

public class FieldDefinitionDTO implements FieldDefinition {
  /**
   * Static Logger for the class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(FieldDefinitionDTO.class);
  /**
   * List of categories associated with our type.
   */
  @ArraySchema(schema = @Schema(description = "List of categories associated with our type.",
    implementation = CategoryDTO.class),
    uniqueItems = true,
    minItems = 0)
  private final Collection<Category> categories;
  /**
   * The shortened name (e.g. Acronym) of the field definition e.g. post code, uk mobile, IPv4, etc..
   */
  @Size(max = ValidationRuleConstants.MAX_STRING_LENGTH)
  @Schema(description = "The shortened name (e.g. Acronym) of the field definition e.g. post code, uk mobile, "
    + "IPv4, etc..",
    example = "MCC",
    nullable = true)
  private String acronym;
  /**
   * Detailed description of the field definition.
   */
  @Schema(description = "Detailed description of the field definition.",
    example = "The mobile country code consists of three decimal digits and the mobile network code consists of "
      + "two or three decimal digits ")
  @Size(max = ValidationRuleConstants.MAX_STRING_LENGTH)
  private String description;

  /**
   * Name of the field definition e.g. post code, uk mobile.
   */
  @Schema(description = "Name of the field definition e.g. post code, uk mobile.",
    example = "Mobile Country Code")
  @Size(min = 1, max = 100)
  private String name;

  /**
   * List of Validation rules for the field definition.
   */
  @ArraySchema(schema = @Schema(description = "List of Validation rules for the field definition.",
    anyOf = {DoubleValidationRuleDTO.class, EnumValidationRuleDTO.class, LongValidationRuleDTO.class,
      StringValidationRuleDTO.class, TimeValidationRuleDTO.class }),
    uniqueItems = true,
    minItems = 1)
  private final Collection<ValidationRule> rules;

  /**
   * Default Class Constructor.
   */
  public FieldDefinitionDTO() {
    this(null);
  }

  /**
   * Copy Constructor.
   *
   * @param data - the object to create a duplicate from.
   */
  public FieldDefinitionDTO(final FieldDefinition data) {
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
   * @param rules     The Rules to be converted.
   */
  public FieldDefinitionDTO(final String acryo, final Collection<Category> cats,
                            final String desc, final String fieldName, final Collection<ValidationRule> rules) {
    this.acronym = acryo;
    this.description = desc;
    this.name = fieldName;

    this.categories = new HashSet<>();
    if (null != cats) {
      this.categories.addAll(cats.stream().filter(Objects::nonNull)
        .map(CategoryDTO::new)
        .collect(Collectors.toSet()));
    }

    this.rules = new HashSet<>();
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
        && that.getCategories().containsAll(this.getCategories());
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
    return Objects.hash(this.getAcronym(), this.getName(), this.getDescription());
  }


  /**
   * Retrieves a list of all rules which have confirmed the incoming object is valid.
   *
   * @param toTest the object to test against the rules stored within this object
   * @return an empty list or a list of identifiers for rules.
   */
  public Collection<String> getRulesPassed(final Object toTest) {
    final Collection<String> passed = new HashSet<>();

    for (final ValidationRule rule : this.getRules()) {
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
    return acronym;
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
   * List of categories assocaited with our type. These are additional ways to define a type for routing/managing a
   * schema.
   *
   * @return an empty list if nothing is supplied.
   */
  @Override
  public Collection<Category> getCategories() {
    return new HashSet<>(categories);
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
      this.categories.addAll(values.stream().filter(Objects::nonNull)
        .map(CategoryDTO::new)
        .collect(Collectors.toSet()));
    }
  }

  /**
   * Retrieves a hopefully detailed description of the field definition so we can understand what it is for and
   * why it exists.
   *
   * @return a hopefull long valid string (null is possible).
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description to attach to this field definition.
   *
   * @param detailedDescription the description to attache (null is ok)
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

    for (final ValidationRule rule : this.rules) {
      if (rule instanceof CountryCodeWrapper countryWrapper) {
        results.add(new CountryCodeRuleWrapperDTO(countryWrapper));
      } else if (rule instanceof DoubleValidationRule doubleRule) {
        results.add(new DoubleValidationRuleDTO(doubleRule));
      } else if (rule instanceof EnumValidationRule enumRule) {
        results.add(new EnumValidationRuleDTO(enumRule));
      } else if (rule instanceof LongValidationRule longRule) {
        results.add(new LongValidationRuleDTO(longRule));
      } else if (rule instanceof StringValidationRule stringRule) {
        results.add(new StringValidationRuleDTO(stringRule));
      } else if (rule instanceof TimeValidationRule timeRule) {
        results.add(new TimeValidationRuleDTO(timeRule));
      }
    }

    return results;
  }

  /**
   * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param validationRules the rules to add to our field definition
   */
  public void setRules(final Collection<ValidationRule> validationRules) {

    this.rules.clear();

    if (null != validationRules) {
      for (final ValidationRule rule : validationRules) {
        if (rule instanceof CountryCodeWrapper countryWrapper) {
          this.rules.add(new CountryCodeRuleWrapperDTO(countryWrapper));
        } else if (rule instanceof DoubleValidationRule doubleRule) {
          this.rules.add(new DoubleValidationRuleDTO(doubleRule));
        } else if (rule instanceof EnumValidationRule enumRule) {
          this.rules.add(new EnumValidationRuleDTO(enumRule));
        } else if (rule instanceof LongValidationRule longRule) {
          this.rules.add(new LongValidationRuleDTO(longRule));
        } else if (rule instanceof StringValidationRule stringRule) {
          this.rules.add(new StringValidationRuleDTO(stringRule));
        } else if (rule instanceof TimeValidationRule timeRule) {
          this.rules.add(new TimeValidationRuleDTO(timeRule));
        }
      }
    }
  }
}
