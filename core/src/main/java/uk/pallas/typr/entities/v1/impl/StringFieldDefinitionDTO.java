package uk.pallas.typr.entities.v1.impl;

import uk.pallas.typr.entities.v1.Category;
import uk.pallas.typr.entities.v1.StringFieldDefinition;

import java.util.Collection;
import java.util.Objects;

/**
 * We expect to store a list of valid Field definitions within a database, these will be referenced within the main
 * data definitions. By classifying fields beyond string, int, etc.. we are able to identify identical fields between
 * different data definitions, this allows us to translate data objects (as well as apply more stringent rules on
 * validating the incoming data).
 *
 * This class is focussed on String fields, allowing us to craft regular expressions to test the incoming data is valid.
 */
public class StringFieldDefinitionDTO extends AbstractFieldDefinitionDTO implements StringFieldDefinition {

    /** As this is a String type, assumption is we apply Regular Express to validate the string. */
    private String detectRegex;

    /** As this is a String type, assumption is we apply Regular Express to extract entities from a string block. */
    private String extractRegex;

    /**
     * Default constructor, sets everything to null and makes validation optional.
     */
    public StringFieldDefinitionDTO() {
        this(null);
    }

    /**
     * Constructor, allows us to set the internal abstract fields
     * @param regularExp the Regular expression to apply to this field.
     * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc...
     * @param shortName a shorted version of the Field Name (e.g. if the Field Name is Mobile Country Code the short name might be MCC).
     * @param desc Can you describe what the field concerns?
     * @param values what categories should be associated with the field definition.
     */
    public StringFieldDefinitionDTO(final String regularExp, final String fieldName, final String shortName,
                                    final String desc, final Collection<Category> values) {
        this(regularExp, null, fieldName, shortName, desc, values);
    }

    /**
     * Constructor, allows us to set the internal abstract fields
     * @param detect the Regular expression to apply to this field.
     * @param extract the Regular expression to apply to this field.
     * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc...
     * @param shortName a shorted version of the Field Name (e.g. if the Field Name is Mobile Country Code the short name might be MCC).
     * @param desc Can you describe what the field concerns?
     * @param values what categories should be associated with the field definition.
     */
    public StringFieldDefinitionDTO(final String detect, final String extract, final String fieldName,
                                    final String shortName, final String desc, final Collection<Category> values) {
        super(fieldName, shortName, desc, values);

        this.detectRegex = detect;
        this.extractRegex = extract;
    }

    /**
     * Copy Constructor, passes up to parent Copy Constructor.
     * @param data the object to create a duplicate off.
     */
    public StringFieldDefinitionDTO(final StringFieldDefinition data) {
        super(data);

        if (null == data) {
            this.detectRegex = null;
            this.extractRegex = null;
        } else {
            this.detectRegex = data.getDetectRegex();
            this.extractRegex = data.getExtractRegex();
        }
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
        } else if (toCompare instanceof StringFieldDefinition) {
            final var that = (StringFieldDefinition) toCompare;
            result = super.equals(toCompare)
                         && Objects.equals(this.getDetectRegex(), that.getDetectRegex())
                         && Objects.equals(this.getExtractRegex(), that.getExtractRegex());
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
        return Objects.hash(super.hashCode(), this.getDetectRegex(), this.getExtractRegex());
    }


    /**
     * The regular Expression to apply to a string defined by this field definition, to confirm a given string is valid.
     * @return non null regular expression that can be used by Java Regex system.
     */
    public String getDetectRegex() {
        return this.detectRegex;
    }

    /**
     * The regular Expression to apply to a string defined by this field definition, to confirm a given string is valid.
     * @param validation a valid regular expression string.
     */
    public void setDetectRegex(final String validation) {
        this.detectRegex = validation;
    }

    /**
     * The regular Expression to extract a matching regex string from a text block.
     * @return non null regular expression that can be used by Java Regex system.
     */
    public String getExtractRegex() {
        return this.extractRegex;
    }

    /**
     * Allows us to set the regular expression to extract the term from a text block.
     * @param validation a valid regular expression string.
     */
    public void setExtractRegex(final String validation) {
        this.extractRegex = validation;
    }

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
    @Override
    public boolean isValid(final Object toTest) {
        final boolean result;

        if (toTest instanceof String) {
            // Use to string as it leaves things the most flexible in our regex comparison.
            result = this.isValid((String)toTest);
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Is the supplied test object something that matches against our field definition regular expression?
     *
     * If validation optional is set to true this will return true, if the supplied object is null, this will always
     * return false. Otherwise it will call matches on the supplied string.
     *
     * @param toTest to test is valid
     * @return false if the object fails the validation match.
     */
    @Override
    public boolean isValid(final String toTest) {
        final boolean result;

        if (null == toTest || toTest.isBlank()) {
            result = false;
        } else {
            result = toTest.trim().matches(this.getDetectRegex());
        }

        return result;
    }
}
