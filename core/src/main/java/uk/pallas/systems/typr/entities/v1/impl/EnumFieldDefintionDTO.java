package uk.pallas.systems.typr.entities.v1.impl;

import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.EnumFieldDefinition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

public class EnumFieldDefintionDTO extends AbstractFieldDefinitionDTO implements EnumFieldDefinition {

  private final Collection<String> values;


  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  public EnumFieldDefintionDTO() {
    this(null);
  }

  /**
   * Constructor, allows us to set the internal abstract fields
   * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc...
   * @param shortName a shorted version of the Field Name (e.g. if the Field Name is Mobile Country Code the short name might be MCC).
   * @param desc Can you describe what the field concerns?
   * @param fieldCats what categories should be associated with the field definition.
   */
  public EnumFieldDefintionDTO(final String fieldName, final String shortName, final String desc,
                               final Collection<String> enumerates, final Collection<Category> fieldCats) {
    super(fieldName, shortName, desc, fieldCats);

    this.values = new HashSet<>(10);
    if (null != enumerates) {
      this.values.addAll(enumerates);
    }
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor.
   * @param data the object to create a duplicate off.
   */
  public EnumFieldDefintionDTO(final EnumFieldDefinition data) {
    this(null == data ? null : data.getName(), null == data ? null : data.getAcronym(),
         null == data ? null : data.getDescription(), null == data ? null : data.getValues(), null == data ? null : data.getCategories());
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
    } else if (toCompare instanceof EnumFieldDefinition) {
      final var that = (EnumFieldDefinition) toCompare;
      result = super.equals(toCompare) && (this.getValues().containsAll(that.getValues()) && that.getValues().containsAll(this.getValues()));
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
    return Objects.hash(super.hashCode(), this.getValues().hashCode());
  }

  @Override
  public Collection<String> getValues() {
    return new HashSet<>(this.values);
  }

  @Override
  public void setValues(final Collection<String> enumerates) {
    if (null == enumerates || enumerates.isEmpty()) {
      this.values.clear();
    } else {
      this.values.addAll(enumerates);
    }
  }

  /**
   * Compares the supplied object to confirm it is in the list of approved
   * @param toTest to test is valid
   * @return
   */
  @Override
  public boolean isValid(final Object toTest) {
    return toTest instanceof String ? this.isValid((String) toTest) : false;
  }

  @Override
  public String getDetectRegex() {
    return null;
  }

  @Override
  public void setDetectRegex(String validation) {

  }

  @Override
  public String getExtractRegex() {
    return null;
  }

  @Override
  public void setExtractRegex(String validation) {

  }

  @Override
  public boolean isValid(final String toTest) {
    final boolean result;

    if (null == toTest || toTest.isBlank()) {
      result = false;
    } else {
      final String trimmed = toTest.trim();
      final Optional<String> match = this.getValues().parallelStream().filter(value -> trimmed.equalsIgnoreCase(value)).findFirst();
      result = !match.isEmpty();
    }

    return result;
  }
}
