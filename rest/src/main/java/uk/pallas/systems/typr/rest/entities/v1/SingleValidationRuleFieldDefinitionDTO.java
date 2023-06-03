package uk.pallas.systems.typr.rest.entities.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collection;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.SingleValidationRuleFieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.rest.entities.v1.utils.DTOFactory;
import uk.pallas.systems.typr.rest.entities.v1.validation.EnumValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.StringValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;

/**
 * Used to define a single Field type and a rule to be associated with it. For example a Mobile Country Code
 * is an international type and so this could represent a MCC value.
 */
@Schema(description = "Used to define a single Field type and a rule to be associated with it. For example a Mobile "
  + "Country Code is an international type and so this could represent a MCC value.")
public class SingleValidationRuleFieldDefinitionDTO extends AbstractFieldDefinitionDTO
  implements SingleValidationRuleFieldDefinition {
  /**
   * Static Logger for the class.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFieldDefinitionDTO.class);

  /**
   * Validation for the field definition.
   */
  @Schema(description = "Validation Rule for the field definition.",
    anyOf = {DoubleValidationRuleDTO.class, EnumValidationRuleDTO.class, LongValidationRuleDTO.class,
      StringValidationRuleDTO.class})
  private ValidationRule rule;


  /**
   * Default Class Constructor.
   */
  public SingleValidationRuleFieldDefinitionDTO() {
    this(null);
  }

  /**
   * Copy Constructor.
   */
  public SingleValidationRuleFieldDefinitionDTO(final FieldDefinition data) {
    this(null != data ? data.getAcronym() : null, null != data ? data.getCategories() : null,
      null != data ? data.getDescription() : null, null != data ? data.getName() : null,
      null);
  }

  /**
   * Copy Constructor.
   */
  public SingleValidationRuleFieldDefinitionDTO(final SingleValidationRuleFieldDefinition data) {
    this(null != data ? data.getAcronym() : null, null != data ? data.getCategories() : null,
      null != data ? data.getDescription() : null, null != data ? data.getName() : null,
      null != data ? data.getRule() : null);
  }

  /**
   * Class Constructor.
   *
   * @param acryo    The shortened name
   * @param cats     The categories associated with our object
   * @param desc     the description of the rule
   * @param name     the name for the rule
   * @param theRulez the Validation rule to apply
   */
  public SingleValidationRuleFieldDefinitionDTO(final String acryo, final Collection<Category> cats, final String desc,
                                                final String name,
                                                final ValidationRule theRulez) {
    super(acryo, cats, desc, name);

    this.rule = DTOFactory.getValidationRuleDTO(theRulez);
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
    } else if (toCompare instanceof SingleValidationRuleFieldDefinition that) {
      result = super.equals(toCompare) && Objects.equals(this.getRule(), that.getRule());
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
    return super.hashCode() + Objects.hash(this.getRule());
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
}
