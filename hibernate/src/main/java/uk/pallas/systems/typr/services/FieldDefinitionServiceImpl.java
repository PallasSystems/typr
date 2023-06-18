package uk.pallas.systems.typr.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.systems.typr.domain.FieldDefinitionRespository;
import uk.pallas.systems.typr.domain.entities.v1.CategoryDomain;
import uk.pallas.systems.typr.domain.entities.v1.FieldDefinitionDomain;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * Used to provide a wrapper around Spring Data JPA's Field Definition Repository so we can
 * switch out the backend as needed.
 */
@Service
public class FieldDefinitionServiceImpl implements FieldDefinitionService {
  /** Static Logger for the class. */
  private static final Log LOGGER = LogFactory.getLog(FieldDefinitionServiceImpl.class);
  @Autowired
  private FieldDefinitionRespository fieldDefDAO;

  public FieldDefinitionRespository getFieldDefDAO() {
    return this.fieldDefDAO;
  }

  public void setFieldDefDAO(final FieldDefinitionRespository service) {
    this.fieldDefDAO = service;
  }

  /**
   * Retrieves a complete list of all field definitions from the database.
   * @return an empty list if no definitions exist within the database.
   */
  public Collection<FieldDefinition> getFieldDefinitions() {
    return new HashSet<>(this.getFieldDefDAO().findAll());
  }

  /**
   * Retrieves all Field definitions associated with a specific category.
   * @param category the name of the category we wish to retrieve field definitions for.
   * @return an empty set if none are found.
   */
  @Override
  public Collection<FieldDefinition> getFieldDefinitionsByCategory(final String category) {
    // Generate a Category Object so we can query the database for the associated one.
    final Category query = new CategoryDomain(category, "");
    // perform the query on the stem.
    return new HashSet<>(this.getFieldDefDAO().findByCategories(query));
  }

  /**
   * Retrieves a specific field definition whose name matches the supplied one.
   * @param name the name of the field definition to retrieve
   * @return null if there is no field definition of that name.
   */
  public FieldDefinition getFieldDefinitionByName(final String name) {
    final Optional<FieldDefinitionDomain> defResults = this.getFieldDefDAO().findById(name);
    return defResults.orElse(null);
  }

  /**
   * This will take the supplied field definition, copy it into a domain object and attempt to write the object
   * to the database.
   *
   * @param fieldDef the object we want to save to the database.
   * @return null if we are unable to create a new object.
   */
  public FieldDefinition saveFieldDefintion(final FieldDefinition fieldDef) {

    final FieldDefinition result;
    if (null == fieldDef) {
      LOGGER.error("saveFieldDefintion - Null object was supplied.");
      result = null;
    } else {
      result = this.getFieldDefDAO().save(new FieldDefinitionDomain(fieldDef));
    }

    return result;
  }
}
