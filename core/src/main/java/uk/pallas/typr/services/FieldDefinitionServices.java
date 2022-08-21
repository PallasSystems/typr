package uk.pallas.typr.services;

import uk.pallas.typr.entities.v1.*;

import java.util.Collection;

public interface FieldDefinitionServices {
  Collection<FieldDefinition> getFieldDefinitions();

  Collection<FieldDefinition> getFieldDefinitionsByType(final String type);

  FieldDefinition getFieldDefinition(final String name);

  FieldDefinition saveFieldDefintion(final FieldDefinition definition);
}
