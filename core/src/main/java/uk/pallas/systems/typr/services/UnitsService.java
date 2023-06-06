package uk.pallas.systems.typr.services;

import java.util.Collection;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

public interface UnitsService {

  Collection<String> getUnits();

  boolean isValid(final String unitName);

  boolean isValid(final FieldDefinition unitName);
}
