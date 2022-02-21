package uk.pallas.typing.domain.entities;

import uk.pallas.freeman.common.structured.definitions.StringFieldDefinition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "stringDefs")
public class StringFieldDefinitionDomain extends AbstractFieldDefinitionDomain implements StringFieldDefinition {

  /** As this is a String type, assumption is we apply Regular Express to validate the string. */
  @Column
  private String regex;

  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  public StringFieldDefinitionDomain() {
    this(null, null, null, true);
  }

  /**
   * Constructor, allows us to set the internal abstract fields
   * @param regularExp the Regular expression to apply to this field.
   * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc..
   * @param desc Can you describe what the field concerns?
   * @param optionalValidation Is the validation optional?
   */
  public StringFieldDefinitionDomain(final String regularExp, final String fieldName, final String desc,
                                   final boolean optionalValidation) {
    super(fieldName, desc, optionalValidation);

    this.regex = regularExp;
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor.
   * @param data the object to create a duplicate off.
   */
  public StringFieldDefinitionDomain(final StringFieldDefinition data) {
    super(data);

    if (null == data) {
      this.regex = null;
    } else {
      this.regex = data.getRegex();
    }
  }

  /**
   * Compares the supplied object to this one, it checks the supplied object is a FieldDefinition and then checks
   * the definition applies validation, its name and description to see if they are matching objects.
   *
   * @param toCompare the object to compare (can be null or a child class, etc..)
   * @return false if the name/validation and description fields in a field definition are
   *        different (or it isn't a field definition).
   */
  @Override
  public boolean equals(final Object toCompare) {

    final boolean result;
    if (this == toCompare) {
      result = true;
    } else if (toCompare instanceof StringFieldDefinition) {
      final var that = (StringFieldDefinition) toCompare;
      result = super.equals(toCompare)
                   && Objects.equals(this.getRegex(), that.getRegex());
    } else {
      result = false;
    }

    return result;
  }

  /**
   * Generates a Unique hashcode for the field definition class.
   * @return a valid integer representation of this object,
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.getRegex());
  }

  /**
   *  The regular Expression to apply to a string defined by this field definition.
   * @return non null regular expression that can be used by Java Regex system.
   */
  public String getRegex() {
    return this.regex;
  }

  /**
   * Allows us to set the regular expression associated with the field definition.
   * @param validation a valid regular expression string.
   */
  public void setRegex(final String validation) {
    this.regex = validation;
  }

  /**
   * Is the supplied test object something that matches against our field definition regular expression?
   *
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

    if (this.isValidationOptional()) {
      result = toTest instanceof String;
    } else if (toTest instanceof String) {
      // Use to string as it leaves things the most flexible in our regex comparison.
      result = this.isValid((String)toTest);
    } else {
      result = false;
    }

    return result;
  }

  /**
   * Is the supplied test object something that matches against our field definition regular expression?
   *
   * If validation optional is set to true this will return true, if the supplied object is null, this will always
   * return false. Otherwise it will call matches on the supplied string.
   *
   * @param toTest to test is valid
   * @return false if the object fails the validation match.
   */
  @Override
  public boolean isValid(final String toTest) {
    final boolean result;

    if (this.isValidationOptional()) {
      result = null != toTest;
    } else if (null == toTest) {
      result = false;
    } else {
      result = toTest.matches(this.getRegex());
    }

    return result;
  }
}
