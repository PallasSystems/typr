package uk.pallas.systems.typr.domain.entities.v1.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.TimeValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;

class TimeValidationRuleDomainTest extends AbstractValidationRuleDomainTest<TimeValidationRuleDomain>{

  /** A valid non ISO time pattern to test this works as expected. */
  private static final String RANDOM_TIME_PATTERN = "dd/MM/yy";

  @Override
  TimeValidationRuleDomain generateTestInstance() {
    final String description = "TimeValidationRuleDomainTest";
    return new TimeValidationRuleDomain(description, RANDOM_TIME_PATTERN);
  }

  @Test
  void testConstructor() {
    // Create Validation rule to be attached
    final String description = "StringValidationRuleDomainTest-testConstructor";
    final TimeValidationRule basic = new TimeValidationRuleDomain(description, RANDOM_TIME_PATTERN);

    Assertions.assertEquals(description, basic.getDescription());
    Assertions.assertEquals(RANDOM_TIME_PATTERN, basic.getTimePattern());
  }

  @Test
  void testCopyConstructor() {
    // Create Validation rule to be attached
    final String description = "TimeValidationRuleDomainTest-testCopyConstructor";

    final TimeValidationRule
      basic = new TimeValidationRuleDomain(description, RANDOM_TIME_PATTERN);
    final TimeValidationRule copy = new TimeValidationRuleDomain(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testIsValid() {
    // Create Validation rule to be attached
    final String description = "TimeValidationRuleDomainTest-testConstructor";
    final TimeValidationRule basic = new TimeValidationRuleDomain(description, RANDOM_TIME_PATTERN);

    // This is a valid UK Post Code
    Assertions.assertTrue(basic.isValid("23/04/99"));
  }
}
