package uk.pallas.systems.typr.rest.entities.v1.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;

/**
 * Defines regular expression rules for a field to confirm if the field is valid or not.
 */
@Schema(description = "Defines regular expression rules for a field to confirm if the field is valid or not.")
public class StringValidationRuleDTO extends AbstractValidationRuleDTO implements StringValidationRule {

  /**
   * As this is a String type, assumption is we apply Regular Express to validate the string.
   */
  @NotBlank
  @Size(max = ValidationRuleConstants.MAX_STRING_LENGTH)
  @Schema(description = "As this is a String type, assumption is we apply Regular Express to validate the string.",
    example = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$")
  private String detectRegex;

  /**
   * As this is a String type, assumption is we apply Regular Express to extract entities from a string block.
   */
  @Schema(description = "As this is a String type, assumption is we apply Regular Express to extract entities "
    + "from a string block.",
    example = "[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}")
  @Size(max = ValidationRuleConstants.MAX_STRING_LENGTH)
  private String extractRegex;

  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  public StringValidationRuleDTO() {
    this(null, null, null);
  }

  /**
   * Constructor, allows us to set the internal abstract fields.
   *
   * @param detailedDescription the description of the rule
   * @param detect              the Regular expression to validate a string
   * @param extract             regular expression to extract a valid value from the string
   */
  public StringValidationRuleDTO(final String detailedDescription, final String detect, final String extract) {
    super(detailedDescription);

    this.detectRegex = detect;
    this.extractRegex = extract;
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor.
   *
   * @param data the object to create a duplicate off.
   */
  public StringValidationRuleDTO(final StringValidationRule data) {
    this(null == data ? null : data.getDescription(), null == data ? null : data.getDetectRegex(),
      null == data ? null : data.getExtractRegex());
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
    } else if (toCompare instanceof StringValidationRule that) {
      result = super.equals(toCompare)
        && Objects.equals(this.getDetectRegex(), that.getDetectRegex())
        && Objects.equals(this.getExtractRegex(), that.getExtractRegex());
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
    return Objects.hash(super.hashCode(), this.getDetectRegex(), this.getExtractRegex());
  }

  /**
   * The regular Expression to apply to a string defined by this field definition, to confirm a given string is valid.
   *
   * @return non null regular expression that can be used by Java Regex system.
   */
  public String getDetectRegex() {
    return this.detectRegex;
  }

  /**
   * The regular Expression to apply to a string defined by this field definition, to confirm a given string is valid.
   *
   * @param validation a valid regular expression string.
   */
  public void setDetectRegex(final String validation) {
    this.detectRegex = validation;
  }

  /**
   * The regular Expression to extract a matching regex string from a text block.
   *
   * @return non null regular expression that can be used by Java Regex system.
   */
  public String getExtractRegex() {
    return this.extractRegex;
  }

  /**
   * Allows us to set the regular expression to extract the term from a text block.
   *
   * @param validation a valid regular expression string.
   */
  public void setExtractRegex(final String validation) {
    this.extractRegex = validation;
  }

  /**
   * Is the supplied test object something that matches against our field definition regular expression?
   * <p>
   * If validation optional is set to true this will return true, if the supplied object is null, this will always
   * return false. Otherwise this will call toString and then match the field definition regex tp confirm the object
   * matches our desired value.
   *
   * @param toTest to test is valid
   * @return false if the object fails the validation match.
   */
  @Override
  public boolean isValid(final Object toTest) {
    final boolean result;

    if (toTest instanceof String) {
      // Use to string as it leaves things the most flexible in our regex comparison.
      result = this.isValid((String) toTest);
    } else {
      result = false;
    }

    return result;
  }

  /**
   * Is the supplied test object something that matches against our field definition regular expression?
   * <p>
   * If validation optional is set to true this will return true, if the supplied object is null, this will always
   * return false. Otherwise it will call matches on the supplied string.
   *
   * @param toTest to test is valid
   * @return false if the object fails the validation match.
   */
  @Override
  public boolean isValid(final String toTest) {
    final boolean result;

    if (null == toTest) {
      result = false;
    } else {
      result = toTest.matches(this.getDetectRegex());
    }

    return result;
  }
}
