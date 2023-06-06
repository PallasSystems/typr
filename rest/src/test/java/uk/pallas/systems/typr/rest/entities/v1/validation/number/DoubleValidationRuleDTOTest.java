package uk.pallas.systems.typr.rest.entities.v1.validation.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.rest.entities.v1.validation.StringValidationRuleDTO;

class DoubleValidationRuleDTOTest {

  @Test
  void testConstructor() {

    final double max = 9999.9;
    final double min = 10.0;
    final String description = "testConstructor";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDTO(max,min,description, unitName);
    Assertions.assertEquals(max, basic.getMaximumValue());
    Assertions.assertEquals(min, basic.getMinimumValue());
    Assertions.assertEquals(description, basic.getDescription());
    Assertions.assertEquals(unitName, basic.getUnit());
  }

  @Test
  void testCopyConstructor() {

    final double max = 12.5;
    final double min = 9.6;
    final String description = "testCopyConstructor";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDTO(max,min,description, unitName);
    final DoubleValidationRule copy = new DoubleValidationRuleDTO(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testEquals() {

    final double max = 98.4;
    final double min = 12.6;
    final String description = "testEquals";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDTO(max,min,description, unitName);

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {

    final double max = 88888.4;
    final double min = 13333.6;
    final String description = "testEqualsWithInvalid";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDTO(max,min,description, unitName);


    Assertions.assertFalse(basic.equals(null));
    Assertions.assertFalse(basic.equals("Test"));
    Assertions.assertFalse(basic.equals(Double.parseDouble("999.78")));
    Assertions.assertFalse(basic.equals(new DoubleValidationRuleDTO()));

    final LongValidationRule longRule = new LongValidationRuleDTO((long)max,(long)min,description, unitName);
    Assertions.assertFalse(basic.equals(longRule));

    final StringValidationRule stringRule = new StringValidationRuleDTO(description, "test", unitName);
    Assertions.assertFalse(basic.equals(stringRule));
  }

  @Test
  void testIsValid() {

    final double max = 55555.5;
    final double min = 33333.6;
    final String description = "testIsValid";
    final String unitName = "Knot";

    final DoubleValidationRule basic = new DoubleValidationRuleDTO(max,min,description, unitName);

    Assertions.assertTrue(basic.isValid("44444.6"));
    Assertions.assertTrue(basic.isValid(44444.6));
  }

  @Test
  void testIsValidWithInvalid() {

    final DoubleValidationRule basic = new DoubleValidationRuleDTO();

    Assertions.assertFalse(basic.isValid(null));
    Assertions.assertFalse(basic.isValid("XYZ"));
  }
}
