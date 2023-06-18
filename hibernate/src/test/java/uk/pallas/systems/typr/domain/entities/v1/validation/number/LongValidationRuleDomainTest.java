package uk.pallas.systems.typr.domain.entities.v1.validation.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;

class LongValidationRuleDomainTest extends AbstractNumberValidationRuleDomainTest<LongValidationRuleDomain, Long> {

  @Test
  void testConstructor() {

    final long max = System.currentTimeMillis();
    final long min = 100;
    final String description = "testConstructor";
    final String unitName = "Degree Angle";

    final LongValidationRule basic = new LongValidationRuleDomain(max,min,description, unitName);
    Assertions.assertEquals(max, basic.getMaximumValue());
    Assertions.assertEquals(min, basic.getMinimumValue());
    Assertions.assertEquals(description, basic.getDescription());
    Assertions.assertEquals(unitName, basic.getUnit());
  }

  @Test
  void testCopyConstructor() {

    final long max = 1234;
    final long min = 987;
    final String description = "testCopyConstructor";
    final String unitName = "Knot";

    final LongValidationRule basic = new LongValidationRuleDomain(max,min,description, unitName);
    final LongValidationRule copy = new LongValidationRuleDomain(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Override
  LongValidationRuleDomain generateTestInstance() {
    final long max = 1234;
    final long min = 987;
    final String description = "LongValidationRuleDomainTest-generateTestInstance";
    final String unitName = "Knot";

    return new LongValidationRuleDomain(max,min,description, unitName);
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

    final LongValidationRule basic = new LongValidationRuleDomain(max,min,description, unitName);

    Assertions.assertEquals(basic, basic);
  }
}
