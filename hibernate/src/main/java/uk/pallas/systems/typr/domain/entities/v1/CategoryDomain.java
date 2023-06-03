package uk.pallas.systems.typr.domain.entities.v1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import uk.pallas.systems.typr.entities.v1.Category;

/**
 * This class defines Categories which can be associated with a definition.
 */
@Entity
@Table(name = "Categories")
public class CategoryDomain implements Category {

  /**
   * Detailed description of the field definition.
   */
  @Column(length = 4096, nullable = true)
  private String description;

  /**
   * Name of the field definition e.g. post code, uk mobile.
   */
  @Id
  @Column(length = 4096, nullable = false)
  private String name;

  /**
   * Default Class Constructor, sets values to null.
   */
  public CategoryDomain() {
    this(null);
  }

  /**
   * Class Copy Constructor.
   */
  public CategoryDomain(final Category data) {
    this(null == data ? null : data.getName(), null == data ? null : data.getDescription());
  }

  /**
   * Class Constructor which lets us set all fields within the object.
   */
  public CategoryDomain(final String identifier, final String desc) {
    this.name = identifier;
    this.description = desc;
  }

  /**
   * {@inheritDoc}
   *
   * @return non null value (if field definition is valid).
   */
  public String getName() {
    return this.name;
  }

  /**
   * {@inheritDoc}
   *
   * @param identifier the new name for the field definition value
   */
  public void setName(final String identifier) {
    this.name = identifier;
  }

  /**
   * {@inheritDoc}
   *
   * @return a hopefull long valid string (null is possible).
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * {@inheritDoc}
   *
   * @param detailedDescription the description to attach (null is ok)
   */
  public void setDescription(final String detailedDescription) {
    this.description = detailedDescription;
  }
}
