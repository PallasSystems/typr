package uk.pallas.systems.typr.services;

import uk.pallas.systems.typr.entities.v1.FieldDefinition;

public interface UnitsService {

  boolean isValid(final String unitName);

  boolean isValid(final FieldDefinition unitName);
}
