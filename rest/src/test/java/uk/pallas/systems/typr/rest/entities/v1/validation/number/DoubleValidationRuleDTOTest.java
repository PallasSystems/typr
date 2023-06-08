package uk.pallas.systems.typr.rest.entities.v1.validation.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;

class DoubleValidationRuleDTOTest
  extends AbstractNumberValidationRuleDTOTest<DoubleValidationRuleDTO, Double> {

  /** Used to ensure the valid number is within our defined range. by taking the current time and removing 1 minute). */
  private static final double MILLS_IN_MINUTE = 60000;

  @Override
  DoubleValidationRuleDTO generateTestInstance() {
    final double max = MILLS_IN_MINUTE * 2;
    final double min = 10.0;
    final String description = "Test description";
    final String unitName = "Knot";

    return new DoubleValidationRuleDTO(max,min,description, unitName);
  }

  /** The Max range in auto generated is the current time, so this removes 1 minute from the
   * current time to ensure the number is lower.
   * @return a valid double within the generateTestInstance range.
   */
  @Override
  Double getValidNumber() {
    return MILLS_IN_MINUTE;
  }

  @Test
  void testConstructor() {

    final double max = 1000.0;
    final double min = 100.0;
    final String description = "DoubleValidationRuleDTOTest-testConstructor";
    final String unitName = ValidationRuleConstants.NO_UNITS;

    final DoubleValidationRule basic = new DoubleValidationRuleDTO(max,min,description, unitName);
    Assertions.assertEquals(max, basic.getMaximumValue());
    Assertions.assertEquals(min, basic.getMinimumValue());
    Assertions.assertEquals(description, basic.getDescription());
    Assertions.assertEquals(unitName, basic.getUnit());
  }

  @Test
  void testCopyConstructor() {
    final DoubleValidationRule basic = this.generateTestInstance();
    final DoubleValidationRule copy = new DoubleValidationRuleDTO(basic);

    Assertions.assertEquals(basic, copy);
  }
}
