package uk.pallas.systems.typr.entities.v1.domain;

import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "enum_type_definitions_values")
public class EnumerateValueDomain {

  /** What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc.. */
  @Id
  @Column(nullable = false)
  private String name;

  /** Can you describe what the field concerns? */
  @Column(length=4096, nullable = true)
  private String description;

  @Column(nullable = true)
  private Long ordinal;

  public EnumerateValueDomain() {
    this(null);
  }

  public EnumerateValueDomain(final String value) {
    this(value, null);
  }

  public EnumerateValueDomain(final String value, final String description) {
    this(value, description, null);
  }

  public EnumerateValueDomain(final String value, final String description, final Long ordinalValue){
    super();

    this.name = value;
    this.description = description;
    this.ordinal = ordinalValue;
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
    } else if (toCompare instanceof EnumerateValueDomain) {
      final var that = (EnumerateValueDomain) toCompare;
      result = Objects.equals(this.getName(), that.getName())
                   && Objects.equals(this.getOrdinal(), that.getOrdinal())
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
    return Objects.hash(this.getName(), this.getDescription(), this.getOrdinal());
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

  public Long getOrdinal() {
    return this.ordinal;
  }

  public void setOrdinal(final Long value) {
    this.ordinal = value;
  }
}
