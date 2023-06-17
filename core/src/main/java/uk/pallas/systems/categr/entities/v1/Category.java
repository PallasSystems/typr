package uk.pallas.systems.categr.entities.v1;

/**
 * This class defines Categories which can be associated with a definition.
 */
public interface Category {
  /**
   * Retrieves the name of the field definition e.g. post code, uk mobile, IPv4, etc..
   *
   * @return non null value (if field definition is valid).
   */
  String getName();

  /**
   * Sets the name for the field definition e.g. Post Code, Ipv6, MCC, etc...
   *
   * @param identifier the new name for the field definition value
   */
  void setName(String identifier);

  /**
   * Retrieves a hopefully detailed description of the field definition so we can understand what it is
   * for and why it exists.
   *
   * @return a hopefull long valid string (null is possible).
   */
  String getDescription();

  /**
   * Sets the description to attach to this field definition.
   *
   * @param detailedDescription the description to attache (null is ok)
   */
  void setDescription(String detailedDescription);
}
