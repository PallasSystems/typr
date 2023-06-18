package uk.pallas.systems.typr.services;

import java.util.Collection;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

/**
 * Wrapper for a Data Access Object, written so we can switch out the backend
 * of the system if it is needed.
 */
public interface FieldDefinitionService {

  /**
   * Retrieves a complete list of all field definitions from the database.
   * @return an empty list if no definitions exist within the database.
   */
  Collection<FieldDefinition> getFieldDefinitions();

  /**
   * Retrieves all Field definitions associated with a specific category.
   * @param category the name of the category we wish to retrieve field definitions for.
   * @return an empty set if none are found.
   */
  Collection<FieldDefinition> getFieldDefinitionsByCategory(String category);

  /**
   * Retrieves a specific field definition whose name matches the supplied one.
   * @param name the name of the field definition to retrieve
   * @return null if there is no field definition of that name.
   */
  FieldDefinition getFieldDefinitionByName(String name);

  /**
   * This will take the supplied field definition, copy it into a domain object and attempt to write the object
   * to the database.
   *
   * @param definition the object we want to save to the database.
   * @return null if we are unable to create a new object.
   */
  FieldDefinition saveFieldDefintion(FieldDefinition definition);
}
