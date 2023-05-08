package uk.pallas.systems.typr.entities.v1.validation;

import java.util.Collection;

public interface EnumValidationRule extends StringValidationRule {

  /**
   * This retrieves all valid enumerate types held within the definition.
   * @return an empty list or a list of valid string entries.
   */
  Collection<String> getEnumerates();

  /**
   * This sets all valid enumerate types held within the definition.
   * @param enumerates an empty list or a list of valid string entries.
   */
  void setEnumerates(final Collection<String> enumerates);
}
