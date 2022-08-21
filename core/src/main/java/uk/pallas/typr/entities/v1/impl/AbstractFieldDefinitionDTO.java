package uk.pallas.typr.entities.v1.impl;

import uk.pallas.typr.entities.v1.Category;
import uk.pallas.typr.entities.v1.FieldDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

abstract class AbstractFieldDefinitionDTO implements FieldDefinition {

    /** What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc.. */
    private String name;

    /** Can you describe what the field concerns? */
    private String description;

    /** Can you describe what the field concerns? */
    private Collection<Category> categories;

    /**
     * Default constructor, sets everything to null and makes validation optional.
     */
    protected AbstractFieldDefinitionDTO() {
        this(null, null, null);
    }

    protected AbstractFieldDefinitionDTO(final FieldDefinition data) {
        this(null == data ? null : data.getName(), null == data ? null : data.getDescription(), null == data ? null : data.getCategories());
    }

    /**
     * Constructor, allows us to set the internal abstract fields
     * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc..
     * @param desc Can you describe what the field concerns?
     */
    protected AbstractFieldDefinitionDTO(final String fieldName, final String desc, final Collection<Category> fieldCats) {
        super();

        this.name = fieldName;
        this.description = desc;
        this.categories = null == fieldCats ? new ArrayList<>() : new ArrayList<>(fieldCats);
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
            result = Objects.equals(this.getName(), that.getName())
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
        return Objects.hash(this.getName(), this.getDescription());
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
     * List of categories assocaited with our type. These are additional ways to define a type for routing/managing a schema.
     * @return an empty list if nothing is supplied.
     */
    public Collection<Category> getCategories() {
        return this.categories;
    }

    /**
     * Sets the list of categories assocaited with our type. These are additional ways to define a type for routing/managing a schema.
     * @param values all categories associated with the type.
     */
    public void setCategories(final Collection<Category> values) {
        this.categories = null == values ? new ArrayList<>() : values;
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
}
