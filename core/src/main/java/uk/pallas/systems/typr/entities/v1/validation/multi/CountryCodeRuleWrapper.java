package uk.pallas.systems.typr.entities.v1.validation.multi;


import com.neovisionaries.i18n.CountryCode;

/**
 *  Some field definitions are country specific for example a postal code within the UK is difined differently
 *  to a zip code in the United States but both are used within the same area of an address block
 */
public interface CountryCodeRuleWrapper extends RuleWrapper {

    /**
     * The specific country this rule is associated with.
     * @return UNDEFINED or a valid country code.
     */
    CountryCode getCountryCode();

    /**
     * Sets the specific country this rule is associated with.
     * @param code - UNDEFINED or a valid country code.
     */
    void setCountryCode(final CountryCode code);

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
}
