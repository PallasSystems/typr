package uk.pallas.systems.typr.entities.v1;

import com.neovisionaries.i18n.CountryCode;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

import java.util.Collection;
import java.util.Map;

/**
 * This class defines Complex Structured Data field definitions we can assign to various structured data schema's.
 */
public interface FieldDefinition {

    /**
     * Retrieves the shortened name (e.g. Acronym) of the field definition e.g. post code, uk mobile, IPv4, etc..
     * @return non null value (if field definition is valid).
     */
    String getAcronym();

    /**
     * Sets the shortened name (e.g. Acronym) for the field definition e.g. Ipv6, MCC, etc...
     * @param identifier the new name for the field definition value
     */
    void setAcronym(final String identifier);

    /**
     * List of categories assocaited with our type. These are additional ways to define a type for routing/managing a schema.
     * @return an empty list if nothing is supplied.
     */
    Collection<Category> getCategories();

    /**
     * Sets the list of categories assocaited with our type. These are additional ways to define a type for routing/managing a schema.
     * @param values all categories associated with the type.
     */
    void setCategories(final Collection<Category> values);

    /**
     * Retrieves a hopefully detailed description of the field definition so we can understand what it is for and why it exists.
     * @return a hopefull long valid string (null is possible).
     */
    String getDescription();

    /**
     * Sets the description to attach to this field definition.
     * @param detailedDescription the description to attache (null is ok)
     */
    void setDescription(final String detailedDescription);

    /**
     * Retrieves the name of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
     * @return non null value (if field definition is valid).
     */
    String getName();

    /**
     * Sets the name for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
     * @param identifier the new name for the field definition value
     */
    void setName(final String identifier);

    /**
     * Is the supplied test object something that matches against our field definition regular expression?
     *
     * If validation optional is set to true this will return true, if the supplied object is null, this will always
     * return false. Otherwise this will call toString and then match the field definition regex tp confirm the object
     * matches our desired value.
     *
     * @param toTest to test is valid
     * @return false if the object fails the validation match.
     */
    boolean isValid(final Object toTest);
}
