package uk.pallas.systems.typr.rest.entities.v1.validation.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
class LongValidationRuleDTOTest extends AbstractNumberValidationRuleDTOTest<LongValidationRuleDTO, Long> {

  @Test
  void testConstructor() {

    final long max = System.currentTimeMillis();
    final long min = 100;
    final String description = "Test description";
    final String unitName = "Knot";

    final LongValidationRule basic = new LongValidationRuleDTO(max,min,description, unitName);
    Assertions.assertEquals(max, basic.getMaximumValue());
    Assertions.assertEquals(min, basic.getMinimumValue());
    Assertions.assertEquals(description, basic.getDescription());
    Assertions.assertEquals(unitName, basic.getUnit());
  }

  @Test
  void testCopyConstructor() {

    final long max = 1234;
    final long min = 987;
    final String description = "Testing";
    final String unitName = "Knot";

    final LongValidationRule basic = new LongValidationRuleDTO(max,min,description, unitName);
    final LongValidationRule copy = new LongValidationRuleDTO(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Override
  LongValidationRuleDTO generateTestInstance() {
    final long max = 1234;
    final long min = 987;
    final String description = "LongValidationRuleDTOTest-generateTestInstance";
    final String unitName = "Knot";

    return new LongValidationRuleDTO(max,min,description, unitName);
  }

  @Override
  Long getValidNumber() {
    return 1034L;
  }

  @Test
  void testEquals() {

    final long max = 98765;
    final long min = 12345;
    final String description = "123";
    final String unitName = "Knot";

    final LongValidationRule basic = new LongValidationRuleDTO(max,min,description, unitName);

    Assertions.assertEquals(basic, basic);
  }
}
