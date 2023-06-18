package uk.pallas.systems.typr.rest.entities.v1.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.TimeValidationRule;

class TimeValidationRuleDTOTest extends AbstractValidationRuleDTOTest<TimeValidationRuleDTO> {

  /** A valid non ISO time pattern to test this works as expected. */
  private static final String RANDOM_TIME_PATTERN = "dd/MM/yyyy";

  @Override
  TimeValidationRuleDTO generateTestInstance() {
    final String description = "TimeValidationRuleDTOTest";
    return new TimeValidationRuleDTO(description, RANDOM_TIME_PATTERN);
  }

  @Test
  void testConstructor() {
    // Create Validation rule to be attached
    final String description = "TimeValidationRuleDTOTest-testConstructor";
    final TimeValidationRule basic = new TimeValidationRuleDTO(description, RANDOM_TIME_PATTERN);

    Assertions.assertEquals(description, basic.getDescription());
    Assertions.assertEquals(RANDOM_TIME_PATTERN, basic.getTimePattern());
  }

  @Test
  void testCopyConstructor() {
    // Create Validation rule to be attached
    final String description = "TimeValidationRuleDTOTest-testCopyConstructor";

    final TimeValidationRule
      basic = new TimeValidationRuleDTO(description, RANDOM_TIME_PATTERN);
    final TimeValidationRule copy = new TimeValidationRuleDTO(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testIsValid() {
    // Create Validation rule to be attached
    final String description = "TimeValidationRuleDTOTest-testIsValid";
    final TimeValidationRule basic = new TimeValidationRuleDTO(description, RANDOM_TIME_PATTERN);

    // This is a valid UK Post Code
    Assertions.assertTrue(basic.isValid("23/04/1999"));
  }
}
