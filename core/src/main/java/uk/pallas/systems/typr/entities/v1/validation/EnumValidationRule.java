package uk.pallas.systems.typr.entities.v1.validation;

import java.util.Collection;

/**
 * Some field definitions have a limited number of allowed string values and so this definition allows us to maintain
 * a list rather than a regular expression.
 */
public interface EnumValidationRule extends StringValidationRule {

  /**
   * This retrieves all valid enumerate types held within the definition.
   *
   * @return an empty list or a list of valid string entries.
   */
  Collection<String> getEnumerates();

  /**
   * This sets all valid enumerate types held within the definition.
   *
   * @param enumerates an empty list or a list of valid string entries.
   */
  void setEnumerates(Collection<String> enumerates);
}
