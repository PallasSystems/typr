package uk.pallas.systems.typr.domain.entities.v1.validation;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Objects;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;

@MappedSuperclass
public abstract class AbstractValidationRuleDomain implements ValidationRule {

  /**
   * Primary key for storing validation rules.
   */
  @Id
  @GeneratedValue
  private Long identifier;

  /**
   * Detailed description of the field definition.
   */
  @Column(length = ValidationRuleConstants.MAX_STRING_LENGTH)
  private String description;

  /**
   * Default class constructor.
   */
  public AbstractValidationRuleDomain() {
    this((String)null);
  }

  /**
   * Class Constructor.
   * @param detailedDescription Detailed description of the field definition.
   */
  public AbstractValidationRuleDomain(final String detailedDescription) {
    this.description = detailedDescription;
  }

  /**
   * Copy Constructor.
   * @param data a Validation rule we are looking to create a clone of.
   */
  public AbstractValidationRuleDomain(final ValidationRule data) {
    this(null == data ? null : data.getDescription());
  }

  public Long getIdentifier() {
    return identifier;
  }

  public void setIdentifier(final Long id) {
    this.identifier = id;
  }

  /**
   * Retrieves a hopefully detailed description of the field definition so we can understand what it is for and
   * why it exists.
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
   * @param detailedDescription the description to attache (null is ok)
   */
  @Override
  public void setDescription(final String detailedDescription) {
    this.description = detailedDescription;
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
    } else if (toCompare instanceof ValidationRule that) {
      result = Objects.equals(this.getDescription(), that.getDescription());
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
    return Objects.hash(this.getDescription());
  }
}
