package uk.pallas.systems.typr.services;

import uk.pallas.systems.typr.entities.v1.FieldDefinition;

public interface CountryService {

  boolean isValidISO31661Alpha3(final FieldDefinition definition);

  boolean isValidISO31661Alpha3(final String value);
}
