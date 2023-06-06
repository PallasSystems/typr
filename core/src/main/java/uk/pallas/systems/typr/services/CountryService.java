package uk.pallas.systems.typr.services;

import uk.pallas.systems.typr.entities.v1.FieldDefinition;

/**
 * Service used to define various valid country codes for validation of Country Wrapped rules.
 */
public interface CountryService {

  /**
   * Used to confirm all the Country aware rules have a valid ISO 3166 Alpha 3 Country code associated with it.
   *
   * @param definition the definition to test if it is correct.
   * @return false if the definition is invalid or if a country code wrapper exists with an invalid code.
   */
  boolean isValidISO31661Alpha3(final FieldDefinition definition);

  /**
   * Used to confirm if the supplied value is a valid ISO 3166 Country code.
   *
   * @param value the value to test if it is correct.
   * @return false if the value is invalid or contains an invalid code.
   */
  boolean isValidISO31661Alpha3(final String value);
}
