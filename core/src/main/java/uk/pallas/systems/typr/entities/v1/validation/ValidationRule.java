package uk.pallas.systems.typr.entities.v1.validation;

/**
 * A field definition can have multiple validation rules applied to it, this provides an interface
 * to allow those to be managed via composition.
 */
public interface ValidationRule {

  /**
   * Retrieves a hopefully detailed description of the field definition so we can understand what it is for and
   * why it exists.
   *
   * @return a hopefull long valid string (null is possible).
   */
  String getDescription();

  /**
   * Sets the description to attach to this field definition.
   *
   * @param detailedDescription the description to attached (null is ok)
   */
  void setDescription(String detailedDescription);

  /**
   * Is the supplied test object something that matches against our field definition regular expression?
   * <p>
   * If validation optional is set to true this will return true, if the supplied object is null, this will always
   * return false. Other-wise this will call toString and then match the field definition regex tp confirm the object
   * matches our desired value.
   *
   * @param toTest to test is valid
   * @return false if the object fails the validation match.
   */
  boolean isValid(Object toTest);
}
