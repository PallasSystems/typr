package uk.pallas.systems.typr.domain.entities.v1;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

@MappedSuperclass
public abstract class AbstractFieldDefinitionDomain implements FieldDefinition {
  /**
   * Static Logger for the class.
   */
  private static final Log LOGGER = LogFactory.getLog(SingleValidationRuleFieldDefinitionDomain.class);
  /**
   * List of categories assocaited with our type.
   */
  @Column(nullable = true)
  @ManyToMany
  private final Collection<CategoryDomain> categories;
  /**
   * The shortened name (e.g. Acronym) of the field definition e.g. post code, uk mobile, IPv4, etc..
   */
  @Column(length = 4096, nullable = true)
  private String acronym;
  /**
   * Detailed description of the field definition.
   */
  @Column(length = 4096, nullable = true)
  private String description;

  /**
   * Name of the field definition e.g. post code, uk mobile.
   */
  @Id
  @Column(length = 100, nullable = false)
  private String name;

  /**
   * Default Class Constructor.
   */
  public AbstractFieldDefinitionDomain() {
    this(null);
  }

  /**
   * Copy Constructor.
   * @param data - the object to duplicate
   */
  public AbstractFieldDefinitionDomain(final FieldDefinition data) {
    this(null != data ? data.getAcronym() : null, null != data ? data.getCategories() : null,
      null != data ? data.getDescription() : null, null != data ? data.getName() : null);
  }

  /**
   * Class Constructor.
   *
   * @param acryo The shortened name
   * @param cats  The categories associated with our object
   * @param desc  the description of the rule
   * @param fieldName  the name for the rule
   */
  public AbstractFieldDefinitionDomain(final String acryo, final Collection<Category> cats,
                                       final String desc, final String fieldName) {
    this.acronym = acryo;
    this.description = desc;
    this.name = fieldName;

    this.categories = new HashSet<>();
    if (null != cats) {
      this.categories.addAll(cats.stream()
        .map(value -> new CategoryDomain(value))
        .collect(Collectors.toSet()));
    }
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
    } else if (toCompare instanceof FieldDefinition that) {
      result = Objects.equals(this.getName(), that.getName())
        && Objects.equals(this.getAcronym(), that.getAcronym())
        && Objects.equals(this.getDescription(), that.getDescription())
        && this.getCategories().containsAll(that.getCategories())
        && that.getCategories().containsAll(this.getCategories());
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
    return Objects.hash(this.getAcronym(), this.getName(), this.getDescription());
  }

  /**
   * Retrieves the shortened name (e.g. Acronym) of the field definition e.g. post code, uk mobile, IPv4, etc..
   *
   * @return non null value (if field definition is valid).
   */
  @Override
  public String getAcronym() {
    return this.acronym;
  }

  /**
   * Sets the shortened name (e.g. Acronym) for the field definition e.g. Ipv6, MCC, etc...
   *
   * @param identifier the new name for the field definition value
   */
  @Override
  public void setAcronym(final String identifier) {
    this.acronym = identifier;
  }

  /**
   * List of categories assocaited with our type. These are additional ways to define a type for routing/managing
   * a schema.
   *
   * @return an empty list if nothing is supplied.
   */
  @Override
  public Collection<Category> getCategories() {
    return new HashSet<>(this.categories);
  }

  /**
   * Sets the list of categories assocaited with our type. These are additional ways to define a type for
   * routing/managing a schema.
   *
   * @param values all categories associated with the type.
   */
  @Override
  public void setCategories(final Collection<Category> values) {
    if (null == values) {
      this.categories.clear();
    } else {
      this.categories.clear();
      this.categories.addAll(values.stream()
        .map(value -> new CategoryDomain(value))
        .collect(Collectors.toSet()));
    }
  }

  /**
   * Retrieves a hopefully detailed description of the field definition so we can understand what it is for
   * and why it exists.
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
   * @param detailedDescription the description to attached (null is ok)
   */
  @Override
  public void setDescription(final String detailedDescription) {
    this.description = detailedDescription;
  }

  /**
   * Retrieves the name of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
   *
   * @return non null value (if field definition is valid).
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
   *
   * @param identifier the new name for the field definition value
   */
  @Override
  public void setName(final String identifier) {
    this.name = identifier;
  }
}
