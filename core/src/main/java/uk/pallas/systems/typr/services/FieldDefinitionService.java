package uk.pallas.systems.typr.services;

import java.util.Collection;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

/**
 * Service wrapping the Data Access Object (DAO) to allow us to extract information from the database.
 */
public interface FieldDefinitionService {

  /**
   * Retrieve all field definitions from the database.
   * @return an empty collection or valid definitions
   */
  Collection<FieldDefinition> getFieldDefinitions();

  /**
   * Retrieve all field definitions which are a member of the supplied category from the database.
   * @return an empty collection or valid definitions
   */
  Collection<FieldDefinition> getFieldDefinitionsByCategory(String string);

  /**
   * Retrieve a speciic field definition through its name
   * @param name the name (primary key) of the field definition
   * @return null if the field definition can not be found.
   */
  FieldDefinition getFieldDefinitionByName(String name);

  /**
   * Creates a field definition within the database
   * @param definition the definition to be created within the database
   * @return null if there was a problem saving the definition
   */
  FieldDefinition saveFieldDefintion(FieldDefinition definition);
}
