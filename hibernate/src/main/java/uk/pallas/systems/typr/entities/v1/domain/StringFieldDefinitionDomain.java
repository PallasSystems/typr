package uk.pallas.systems.typr.entities.v1.domain;

import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.StringFieldDefinition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "string_type_definitions")
public class StringFieldDefinitionDomain extends AbstractFieldDefinitionDomain implements StringFieldDefinition {

  /** As this is a String type, assumption is we apply Regular Express to validate the string. */
  @Column(nullable = true)
  private String detectRegex;

  /** As this is a String type, assumption is we apply Regular Express to extract entities from a string block. */
  @Column(nullable = true)
  private String extractRegex;

  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  public StringFieldDefinitionDomain() {
    this(null, null, null);
  }

  /**
   * Constructor, allows us to set the internal abstract fields
   * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc..
   */
  public StringFieldDefinitionDomain(final String fieldName) {
    this(null, fieldName, null);
  }

  /**
   * Constructor, allows us to set the internal abstract fields
   * @param regularExp the Regular expression to apply to this field.
   * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc...
   * @param desc Can you describe what the field concerns?
   */
  public StringFieldDefinitionDomain(final String regularExp, final String fieldName, final String desc) {
    this(regularExp, null, fieldName, desc, null);
  }

  /**
   * Constructor, allows us to set the internal abstract fields
   * @param detect the Regular expression to apply to this field.
   * @param extract the Regular expression to apply to this field.
   * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc...
   * @param desc Can you describe what the field concerns?
   * @param values what categories should be associated with the field definition.
   */
  public StringFieldDefinitionDomain(final String detect, final String extract, final String fieldName,
                                     final String desc, final Collection<Category> values) {
    super(fieldName, desc);

    this.detectRegex = detect;
    this.extractRegex = extract;
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor.
   * @param data the object to create a duplicate off.
   */
  public StringFieldDefinitionDomain(final StringFieldDefinition data) {
    super(data);

    if (null == data) {
      this.detectRegex = null;
      this.extractRegex = null;
    } else {
      this.detectRegex = data.getDetectRegex();
      this.extractRegex = data.getExtractRegex();
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
      final StringFieldDefinition that = (StringFieldDefinition) toCompare;
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
   * @return a valid integer representation of this object,
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.getDetectRegex(), this.getExtractRegex());
  }

  /**
   * The regular Expression to apply to a string defined by this field definition, to confirm a given string is valid.
   * @return non null regular expression that can be used by Java Regex system.
   */
  public String getDetectRegex() {
    return this.detectRegex;
  }

  /**
   * The regular Expression to apply to a string defined by this field definition, to confirm a given string is valid.
   * @param validation a valid regular expression string.
   */
  public void setDetectRegex(final String validation) {
    this.detectRegex = validation;
  }

  /**
   * The regular Expression to extract a matching regex string from a text block.
   * @return non null regular expression that can be used by Java Regex system.
   */
  public String getExtractRegex() {
    return this.extractRegex;
  }

  /**
   * Allows us to set the regular expression to extract the term from a text block.
   * @param validation a valid regular expression string.
   */
  public void setExtractRegex(final String validation) {
    this.extractRegex = validation;
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

    if (toTest instanceof String) {
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

    if (null == toTest) {
      result = false;
    } else {
      result = toTest.matches(this.getDetectRegex());
    }

    return result;
  }
}
