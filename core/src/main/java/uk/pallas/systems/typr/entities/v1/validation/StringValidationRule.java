package uk.pallas.systems.typr.entities.v1.validation;

/**
 * Defines regular expression rules for a field to confirm if the field is valid or not.
 */
public interface StringValidationRule extends ValidationRule {

  /**
   * The regular Expression to apply to a string defined by this field definition, to confirm a given string is valid.
   *
   * @return non-null regular expression that can be used by Java Regex system.
   */
  String getDetectRegex();

  /**
   * The regular Expression to apply to a string defined by this field definition, to confirm a given string is valid.
   *
   * @param validation a valid regular expression string.
   */
  void setDetectRegex(String validation);

  /**
   * The regular Expression to extract a matching regex string from a text block.
   *
   * @return non null regular expression that can be used by Java Regex system.
   */
  String getExtractRegex();

  /**
   * Allows us to set the regular expression to extract the term from a text block.
   *
   * @param validation a valid regular expression string.
   */
  void setExtractRegex(String validation);

  /**
   * Is the supplied test object something that matches against our field definition regular expression?
   * <p>
   * If validation optional is set to true this will return true, if the supplied object is null, this will always
   * return false. Otherwise it will call matches on the supplied string.
   *
   * @param toTest to test is valid
   * @return false if the object fails the validation match.
   */
  boolean isValid(String toTest);
}
