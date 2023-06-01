package uk.pallas.systems.typr.services;

import java.util.Collection;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

public interface FieldDefinitionService {

  Collection<Category> getCategories();

  Category getCategoryByName(final String name);

  Collection<FieldDefinition> getFieldDefinitions();

  Collection<FieldDefinition> getFieldDefinitionsByCategory(final String category);

  FieldDefinition getFieldDefinitionByName(final String name);

  FieldDefinition saveFieldDefintion(final FieldDefinition definition);
}
