package uk.pallas.systems.typr.services;

import uk.pallas.systems.typr.entities.v1.FieldDefinition;

import java.util.Collection;

public interface FieldDefinitionServices {
  Collection<FieldDefinition> getFieldDefinitions();

  Collection<FieldDefinition> getFieldDefinitionsByType(final String type);

  FieldDefinition getFieldDefinition(final String name);

  FieldDefinition saveFieldDefintion(final FieldDefinition definition);
}
