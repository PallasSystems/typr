package uk.pallas.systems.typr.entities.v1.validation;

public interface TimeValidationRule extends ValidationRule {

  /**
   * Uses Java Pattern Letters to configure a time based field.
   * @return a valid pattern (uses ISO 8601 as the default)
   */
  String getTimePattern();

  /**
   * Allows you to define a Time Date definition for a field.
   * @param pattern A pattern using Java annotation.
   */
  void setTimePattern(final String pattern);
}
