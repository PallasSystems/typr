package uk.pallas.systems.typr.entities.v1.validation;

public final class ValidationRuleConstants {

  /** Expected maximum string length. */
  public static final int MAX_STRING_LENGTH = 4096;

  /** Default Country code (means no country). */
  public static final String DEFAULT_COUNTRY_CODE = "Undefined";

  /** Default unit value, means no unit is associated with the rule. */
  public static final String NO_UNITS = "N/A";

  /** Default multiplier value (we expect the whole unit). */
  public static final Double DEFAULT_MULTIPLIER = 1.0;

  /** Private Utility Class Constructor. */
  private ValidationRuleConstants() {
    super();
  }
}
