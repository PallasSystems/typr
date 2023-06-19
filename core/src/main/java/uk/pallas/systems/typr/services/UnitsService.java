package uk.pallas.systems.typr.services;

import java.util.Collection;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

/**
 * A wrapper for the SI Measurements library, so it can be called by various spring services.
 */
public interface UnitsService {

  /**
   * Retrieves all valid units supported by Typr.
   * @return a collection of types collected from the measurement library.
   */
  Collection<String> getUnits();

  /**
   * Looks to see if the supplied name can be found within the units library (not everything has a name).
   *
   * @param unitName the name to perform a lookup on
   * @return false if the name can't be found.
   */
  boolean isValid(String unitName);

  /**
   * Looks to see if the supplied field definition has Number validation rules with Units defined against them.
   * If there is a unit this will look to see if the supplied name can be found within the units library (not
   * everything has a name).
   *
   * @param definition the object to check
   * @return false if the name can't be found.
   */
  boolean isValid(FieldDefinition definition);
}
