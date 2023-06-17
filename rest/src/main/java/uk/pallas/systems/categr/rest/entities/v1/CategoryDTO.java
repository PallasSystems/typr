package uk.pallas.systems.categr.rest.entities.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import java.util.Objects;
import uk.pallas.systems.categr.CategoryConstants;
import uk.pallas.systems.categr.entities.v1.Category;

/**
 *
 */
@Schema(description = "Categories are ways of loosely grouping information fields")
public class CategoryDTO implements Category {

  /**
   * Detailed description of the field definition.
   */
  @Schema(description = "Detailed description of the field definition.", example = "Field represents a good Graph Edge")
  @Size(max = CategoryConstants.MAX_DESCRIPTION_LENGTH)
  private String description;

  /**
   * Name of the field definition e.g. post code, uk mobile.
   */
  @Schema(description = "Name of the category, this is", example = "Edge")
  @Size(max = CategoryConstants.MAX_NAME_LENGTH)
  private String name;

  /**
   * Default Class Constructor, sets values to null.
   */
  public CategoryDTO() {
    this(null);
  }

  /**
   * Class Copy Constructor.
   * @param data the object to be copied into this newly created Category object.
   */
  public CategoryDTO(final Category data) {
    this(null == data ? null : data.getName(), null == data ? null : data.getDescription());
  }

  /**
   * Class Constructor which lets us set all fields within the object.
   * @param identifier Name of the field definition e.g. post code, uk mobile.
   * @param desc Detailed description of the field definition.
   */
  public CategoryDTO(final String identifier, final String desc) {
    this.name = identifier;
    this.description = desc;
  }

  /**
   * Compares the supplied object to this one, it checks the supplied object is a Category.
   *
   * @param toCompare the object to compare (can be null or a child class, etc..)
   * @return false if the name and description fields in a category
   */
  @Override
  public boolean equals(final Object toCompare) {

    final boolean result;
    if (this == toCompare) {
      result = true;
    } else if (toCompare instanceof Category that) {
      result = Objects.equals(this.getName(), that.getName())
        && Objects.equals(this.getDescription(), that.getDescription());
    } else {
      result = false;
    }

    return result;
  }

  /**
   * Generates a Unique hashcode for the Category class.
   *
   * @return a valid integer representation of this object,
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getDescription());
  }

  /**
   * {@inheritDoc}
   *
   * @return non null value (if field definition is valid).
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * {@inheritDoc}
   *
   * @param identifier the new name for the field definition value
   */
  @Override
  public void setName(final String identifier) {
    this.name = identifier;
  }

  /**
   * {@inheritDoc}
   *
   * @return a hopefull long valid string (null is possible).
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /**
   * {@inheritDoc}
   *
   * @param detailedDescription the description to attach (null is ok)
   */
  @Override
  public void setDescription(final String detailedDescription) {
    this.description = detailedDescription;
  }
}
