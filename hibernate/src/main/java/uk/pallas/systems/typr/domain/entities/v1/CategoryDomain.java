package uk.pallas.systems.typr.domain.entities.v1;

import jakarta.persistence.*;
import uk.pallas.systems.typr.entities.v1.Category;

/**
 * This class defines Categories which can be associated with a definition.
 */
@Entity
@Table(name="Categories")
public class CategoryDomain implements Category {

    /** Detailed description of the field definition. */
    @Column(length=4096, nullable = true)
    private String description;

    /** Name of the field definition e.g. post code, uk mobile. */
    @Id
    @Column(length=4096, nullable = false)
    private String name;

    /** Default Class Constructor, sets values to null. */
    public CategoryDomain() {
        this(null);
    }

    /** Class Copy Constructor. */
    public CategoryDomain(final Category data) {
        this(null == data ? null : data.getName(), null == data ? null : data.getDescription());
    }

    /** Class Constructor which lets us set all fields within the object. */
    public CategoryDomain(final String identifier, final String desc) {
        super();

        this.name = identifier;
        this.description = desc;
    }

    /**
     * Retrieves the name of the field definition e.g. post code, uk mobile, IPv4, etc..
     * @return non null value (if field definition is valid).
     */
    public String getName() {
        return this.name;
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
        return this.description;
    }

    /**
     * Sets the description to attach to this field definition.
     * @param detailedDescription the description to attache (null is ok)
     */
    public void setDescription(final String detailedDescription) {
        this.description = detailedDescription;
    }
}
