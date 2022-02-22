package uk.pallas.typing.domain.entities;

import uk.pallas.freeman.common.structured.definitions.FieldDefinition;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractFieldDefinitionDomain  implements FieldDefinition {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  /** What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc.. */
  @Column(nullable = false)
  private String name;

  /** Can you describe what the field concerns? */
  @Column(length=4096)
  private String description;

  /** Is the validation optional? Does failing the validation mean we should not ingest the data? */
  @Column(nullable = false)
  private boolean validationOptional;

  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  protected AbstractFieldDefinitionDomain() {
    this(null, null, true);
  }

  /**
   * Copy Constructor.
   * @param data object to copy from.
   */
  protected AbstractFieldDefinitionDomain(final FieldDefinition data) {
    super();

    if (null == data) {
      this.validationOptional = true;
    } else {
      this.description = data.getDescription();
      this.validationOptional = data.isValidationOptional();
      this.name = data.getName();
    }
  }


  /**
   * Constructor, allows us to set the internal abstract fields
   * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc..
   * @param desc Can you describe what the field concerns?
   * @param optionalValidation Is the validation optional?
   */
  protected AbstractFieldDefinitionDomain(final String fieldName, final String desc, final boolean optionalValidation) {
    super();

    this.name = fieldName;
    this.description = desc;
    this.validationOptional = optionalValidation;
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
    } else if (toCompare instanceof FieldDefinition) {
      final var that = (FieldDefinition) toCompare;
      result = this.isValidationOptional() == that.isValidationOptional()
                   && Objects.equals(this.getName(), that.getName())
                   && Objects.equals(this.getDescription(), that.getDescription());
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
    return Objects.hash(this.getName(), this.getDescription(), this.isValidationOptional());
  }

  public int getId() {
    return id;
  }

  public void setId(final int identifier) {
    this.id = identifier;
  }

  /**
   * Retrieves the name of the field definition e.g. post code, uk mobile, IPv4, etc..
   * @return non null value (if field definition is valid).
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name for the field definition e.g. Post Code, Ipv6, MCC, etc...
   * @param identifier the new name for the field definition value
   */
  public void setName(final String identifier) {
    this.name = identifier;
  }

  /**
   * Retrieves a hopefully detailed description of the field definition so we can understand what it is for and why it exists.
   * @return a hopefull long valid string (null is possible).
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description to attach to this field definition.
   * @param detailedDescription the description to attache (null is ok)
   */
  public void setDescription(final String detailedDescription) {
    this.description = detailedDescription;
  }

  /**
   * Flag to allow us to configure if validation is required for this field definition (perhaps a lot of the data
   * is non conforming). If this is set to true then we do not need to validate the field, if false .. we do.
   *
   * @return true/false on if the regular expression associated is actually used to test the object.
   */
  public boolean isValidationOptional() {
    return this.validationOptional;
  }

  /**
   * This allows us to set the flag to configure if the DTO will test the incoming data object.
   * @param validationOptional is the validation of the field .. optional?
   */
  public void setValidationOptional(final boolean validationOptional) {
    this.validationOptional = validationOptional;
  }

}
