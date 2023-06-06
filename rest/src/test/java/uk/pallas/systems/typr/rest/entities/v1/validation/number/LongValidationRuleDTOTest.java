package uk.pallas.systems.typr.rest.entities.v1.validation.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.rest.entities.v1.validation.StringValidationRuleDTO;

class LongValidationRuleDTOTest {

  @Test
  void testConstructor() {

    final long max = 1000;
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

  @Test
  void testEquals() {

    final long max = 98765;
    final long min = 12345;
    final String description = "123";
    final String unitName = "Knot";

    final LongValidationRule basic = new LongValidationRuleDTO(max,min,description, unitName);

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {

    final long max = 99999;
    final long min = 12222;
    final String description = "dskhdfkjdsdhf";
    final String unitName = "Knot";

    final LongValidationRule basic = new LongValidationRuleDTO(max,min,description, unitName);


    Assertions.assertFalse(basic.equals(null));
    Assertions.assertFalse(basic.equals("Test"));
    Assertions.assertFalse(basic.equals(Double.parseDouble("543.3")));
    Assertions.assertFalse(basic.equals(new LongValidationRuleDTO()));

    final DoubleValidationRule longRule = new DoubleValidationRuleDTO((double)max,(double)min,description, unitName);
    Assertions.assertFalse(basic.equals(longRule));

    final StringValidationRule stringRule = new StringValidationRuleDTO(description, "test", unitName);
    Assertions.assertFalse(basic.equals(stringRule));
  }


  @Test
  void testIsValid() {

    final long max = 99999;
    final long min = 12222;
    final String description = "adsfsdf";
    final String unitName = "Knot";

    final LongValidationRule basic = new LongValidationRuleDTO(max,min,description, unitName);

    Assertions.assertTrue(basic.isValid("22222"));
    Assertions.assertTrue(basic.isValid(22222));
    Assertions.assertTrue(basic.isValid(22222.54654));
  }

  @Test
  void testIsValidWithInvalid() {

    final DoubleValidationRule basic = new DoubleValidationRuleDTO();

    Assertions.assertFalse(basic.isValid(null));
    Assertions.assertFalse(basic.isValid("ABC"));
  }
}
