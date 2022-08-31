package uk.pallas.systems.typr.services;

import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

import java.util.Collection;

public interface FieldDefinitionServices {

  Collection<Category> getCategories();

  Category getCategoryByName(final String name);

  Collection<FieldDefinition> getFieldDefinitions();

  Collection<FieldDefinition> getFieldDefinitionsByCategory(final String category);

  Collection<FieldDefinition> getFieldDefinitionsByType(final String type);

  FieldDefinition getFieldDefinitionByName(final String name);

  FieldDefinition saveFieldDefintion(final FieldDefinition definition);
}
