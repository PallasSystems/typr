package uk.pallas.systems.typr.rest.entities.v1.validation.multi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neovisionaries.i18n.CountryCode;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.multi.CountryCodeRuleWrapper;

import java.util.Objects;

public class CountryCodeRuleWrapperDTO extends AbstractRuleWrapperDTO implements CountryCodeRuleWrapper {

    /** Some rules (e.g. post code, zip code, etc.. are unique to a specific country, allows us to be country specific. */
    @Schema(example="UNDEFINED", nullable = false,
            description="Some rules (e.g. post code, zip code, etc.. are unique to a specific country, allows us to be country specific.")
    private CountryCode countryCode;

    /**
     * Default constructor, sets the country code to undefined.
     */
    public CountryCodeRuleWrapperDTO() {
        this(CountryCode.UNDEFINED, null);
    }

    /**
     * Copy constructor, creates a duplicate of the object supplied.
     * @param wrapper the object to copy
     */
    public CountryCodeRuleWrapperDTO(final CountryCodeRuleWrapper wrapper) {
        this(null == wrapper ? CountryCode.UNDEFINED : wrapper.getCountryCode(),
                null == wrapper ? null : wrapper.getRule());
    }

    /**
     * Creates a new instance of the wrapper and populates it with the required settings
     * @param code a country specific identifier for the rule
     * @param rule the rule we need to wrap with a different identifier.
     */
    public CountryCodeRuleWrapperDTO(final CountryCode code, final ValidationRule rule) {
        super(rule);

        if (null == code) {
            this.countryCode = CountryCode.UNDEFINED;
        } else {
            this.countryCode = code;
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
        } else if (toCompare instanceof CountryCodeRuleWrapper) {
            final CountryCodeRuleWrapper that = (CountryCodeRuleWrapper) toCompare;
            result = super.equals(toCompare) && Objects.equals(this.getCountryCode(), that.getCountryCode());
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
        return super.hashCode() + Objects.hash(this.getCountryCode());
    }

    @Override
    public CountryCode getCountryCode() {
        return this.countryCode;
    }

    @Override
    public void setCountryCode(final CountryCode code) {
        this.countryCode = code;
    }

    @JsonIgnore
    @Override
    public String getId() {
        final StringBuilder result = new StringBuilder();

        if (null != this.getCountryCode()) {
            result.append(this.getCountryCode());
        }

        return result.toString();
    }
}
