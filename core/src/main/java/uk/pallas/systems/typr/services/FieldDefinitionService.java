package uk.pallas.systems.typr.services;

import java.util.Collection;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

public interface FieldDefinitionService {

  Collection<Category> getCategories();

  Category getCategoryByName(String name);

  Collection<FieldDefinition> getFieldDefinitions();

  Collection<FieldDefinition> getFieldDefinitionsByCategory(String category);

  FieldDefinition getFieldDefinitionByName(String name);

  FieldDefinition saveFieldDefintion(FieldDefinition definition);
}
