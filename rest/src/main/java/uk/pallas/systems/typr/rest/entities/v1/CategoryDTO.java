package uk.pallas.systems.typr.rest.entities.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import uk.pallas.systems.typr.entities.v1.Category;

/**
 *
 */
@Schema(description = "Categories are ways of loosely grouping information fields")
public class CategoryDTO implements Category {

  /**
   * Detailed description of the field definition.
   */
  @Schema(description = "Detailed description of the field definition.", example = "Field represents a good Graph Edge")
  @Size(min = 0, max = 4096)
  private String description;

  /**
   * Name of the field definition e.g. post code, uk mobile.
   */
  @Schema(description = "Name of the category, this is", nullable = false, example = "Edge")
  @Size(min = 0, max = 4096)
  private String name;

  /**
   * Default Class Constructor, sets values to null.
   */
  public CategoryDTO() {
    this(null);
  }

  /**
   * Class Copy Constructor.
   */
  public CategoryDTO(final Category data) {
    this(null == data ? null : data.getName(), null == data ? null : data.getDescription());
  }

  /**
   * Class Constructor which lets us set all fields within the object.
   */
  public CategoryDTO(final String identifier, final String desc) {
    this.name = identifier;
    this.description = desc;
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
