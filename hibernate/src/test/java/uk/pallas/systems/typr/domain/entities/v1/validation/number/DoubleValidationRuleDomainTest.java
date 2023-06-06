package uk.pallas.systems.typr.domain.entities.v1.validation.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;

class DoubleValidationRuleDomainTest {

  @Test
  void testConstructor() {

    final double max = 1000.0;
    final double min = 100.0;
    final String description = "Test description";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDomain(max,min,description, unitName);
    Assertions.assertEquals(max, basic.getMaximumValue());
    Assertions.assertEquals(min, basic.getMinimumValue());
    Assertions.assertEquals(description, basic.getDescription());
    Assertions.assertEquals(unitName, basic.getUnit());
  }

  @Test
  void testCopyConstructor() {

    final double max = 1234.5;
    final double min = 987.6;
    final String description = "Testing";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDomain(max,min,description, unitName);
    final DoubleValidationRule copy = new DoubleValidationRuleDomain(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testEquals() {

    final double max = 98765.4;
    final double min = 12345.6;
    final String description = "123";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDomain(max,min,description, unitName);

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {

    final double max = 99999.4;
    final double min = 12222.6;
    final String description = "dskhdfkjdsdhf";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDomain(max,min,description, unitName);


    Assertions.assertFalse(basic.equals(null));
    Assertions.assertFalse(basic.equals("Test"));
    Assertions.assertFalse(basic.equals(Double.parseDouble("543.3")));
    Assertions.assertFalse(basic.equals(new DoubleValidationRuleDomain()));

    final LongValidationRule longRule = new LongValidationRuleDomain((long)max,(long)min,description, unitName);
    Assertions.assertFalse(basic.equals(longRule));

    final StringValidationRule stringRule = new StringValidationRuleDomain(description, "test", unitName);
    Assertions.assertFalse(basic.equals(stringRule));
  }

  @Test
  void testIsValid() {

    final double max = 99999.4;
    final double min = 12222.6;
    final String description = "dskhdfkjdsdhf";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDomain(max,min,description, unitName);

    Assertions.assertTrue(basic.isValid("22222.6"));
    Assertions.assertTrue(basic.isValid(22222.6));
  }

  @Test
  void testIsValidWithInvalid() {

    final DoubleValidationRule basic = new DoubleValidationRuleDomain();

    Assertions.assertFalse(basic.isValid(null));
    Assertions.assertFalse(basic.isValid("ABC"));
  }
}
