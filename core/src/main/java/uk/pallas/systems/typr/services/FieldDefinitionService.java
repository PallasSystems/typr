package uk.pallas.systems.typr.services;

import java.util.Collection;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

public interface FieldDefinitionService {

  Collection<FieldDefinition> getFieldDefinitions();

  Collection<FieldDefinition> getFieldDefinitionsByCategory(java.lang.String string);

  FieldDefinition getFieldDefinitionByName(java.lang.String name);

  FieldDefinition saveFieldDefintion(FieldDefinition definition);
}
