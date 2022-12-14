package uk.pallas.systems.typr.entities.v1.impl;

import uk.pallas.systems.typr.entities.v1.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CategoryDTO implements Category {

  /** What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc.. */
  @NotBlank
  private String name;

  /** Can you describe what the field concerns? */
  @Size(max=4096)
  private String description;

  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  protected CategoryDTO() {
    this(null, null);
  }

  /**
   * Copy Constructor.
   * @param data object to copy from.
   */
  protected CategoryDTO(final Category data) {
    this(null == data ? null : data.getName(), null == data ? null : data.getDescription());
  }


  /**
   * Constructor, allows us to set the internal abstract fields
   * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc..
   * @param desc Can you describe what the field concerns?
   */
  protected CategoryDTO(final String fieldName, final String desc) {
    super();

    this.name = fieldName;
    this.description = desc;
  }

  /**
   * Compares the supplied object to this one, it checks the supplied object is a FieldDefinition and then checks
   * the definition applies validation, its name and description to see if they are matching objects.
   *
   * @param toCompare the object to compare (can be null or a child class, etc..)
   * @return false if the name/validation and description fields in a category are
   *        different (or it isn't a category).
   */
  @Override
  public boolean equals(final Object toCompare) {

    final boolean result;
    if (this == toCompare) {
      result = true;
    } else if (toCompare instanceof Category) {
      final Category that = (Category) toCompare;
      result = Objects.equals(this.getName(), that.getName())
                   && Objects.equals(this.getDescription(), that.getDescription());
    } else {
      result = false;
    }

    return result;
  }

  /**
   * Generates a Unique hashcode for the category class.
   * @return a valid integer representation of this object,
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getDescription());
  }

  /**
   * Retrieves the name of the category e.g. post code, uk mobile, IPv4, etc..
   * @return non null value (if category is valid).
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name for the category e.g. Post Code, Ipv6, MCC, etc...
   * @param identifier the new name for the category value
   */
  public void setName(final String identifier) {
    this.name = identifier;
  }

  /**
   * Retrieves a hopefully detailed description of the category so we can understand what it is for and why it exists.
   * @return a hopefull long valid string (null is possible).
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description to attach to this Category.
   * @param detailedDescription the description to attache (null is ok)
   */
  public void setDescription(final String detailedDescription) {
    this.description = detailedDescription;
  }
}
