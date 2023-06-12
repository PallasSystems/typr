package uk.pallas.systems.typr.rest.entities.v1.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.pallas.systems.typr.entities.v1.validation.TimeValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;

public class TimeValidationRuleDTO extends AbstractValidationRuleDTO implements TimeValidationRule {

  /** Static Logger for the class. */
  private static final Log LOGGER = LogFactory.getLog(TimeValidationRuleDTO.class);

  /** A time pattern using Java annotation. */
  @NotBlank
  @Size(max = ValidationRuleConstants.MAX_STRING_LENGTH)
  @Schema(description = "Follows the Java Time Pattern format for SimpleDateFormat",
    example = "dd/MM/yy")
  private String timePattern;

  /**
   * Default constructor, description to null and .
   */
  public TimeValidationRuleDTO() {
    this(ValidationRuleConstants.ISO_8601_DATE, null);
  }

  /**
   * Constructor, allows us to set the internal abstract fields
   *
   * @param detailedDescription the description of the rule
   * @param pattern A pattern using Java annotation.
   */
  public TimeValidationRuleDTO(final String detailedDescription, final String pattern) {
    super(detailedDescription);

    this.setTimePattern(pattern);
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor.
   *
   * @param data the object to create a duplicate off.
   */
  public TimeValidationRuleDTO(final TimeValidationRule data) {
    this(null == data ? null : data.getDescription(), null == data ? null : data.getTimePattern());
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
    } else if (toCompare instanceof TimeValidationRule that) {
      result = super.equals(toCompare)
        && Objects.equals(this.getTimePattern(), that.getTimePattern());
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
    return Objects.hash(super.hashCode(), this.getTimePattern());
  }

  /**
   * {@inheritDoc}
   * @return representation of the time pattern held in the rule.
   */
  public String getTimePattern() {
    return this.timePattern;
  }

  /**
   * {@inheritDoc}
   * @param pattern A pattern using Java annotation.
   */
  public void setTimePattern(final String pattern) {
    if (null == pattern || pattern.isBlank()) {
      this.timePattern = ValidationRuleConstants.ISO_8601_DATE;
    } else {
      this.timePattern = pattern;
    }
  }

  /**
   * Checks the incoming object is based on a string and can be converted into a date using the time pattern
   * supplied with the object.
   *
   * @param toTest to test is valid
   * @return false if the supplied object can't be converted.
   */
  @Override
  public boolean isValid(final Object toTest) {

    final DateFormat formattter = new SimpleDateFormat(this.getTimePattern());
    formattter.setLenient(false);

    boolean result = false;
    try {
      if (toTest instanceof String dateString) {
        result = null != formattter.parse(dateString);
      }
    } catch (final ParseException exception) {
      LOGGER.error(
        String.format("Unable to parse: %s to test for date with pattern: %s", toTest, this.getTimePattern()));
    }

    return result;
  }
}
