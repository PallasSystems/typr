package uk.pallas.systems.typr.entities.v1;

import java.util.Collection;

public interface EnumFieldDefinition extends StringFieldDefinition {

  /**
   * This retrieves all valid enumerate types held within the definition.
   * @return an empty list or a list of valid string entries.
   */
  Collection<String> getValues();

  /**
   * This sets all valid enumerate types held within the definition.
   * @param enumerates an empty list or a list of valid string entries.
   */
  void setValues(final Collection<String> enumerates);
}
