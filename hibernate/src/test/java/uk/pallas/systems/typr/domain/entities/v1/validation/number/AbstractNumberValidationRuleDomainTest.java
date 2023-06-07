package uk.pallas.systems.typr.domain.entities.v1.validation.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRuleConstants;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;

public abstract class AbstractNumberValidationRuleDomainTest
  <T extends AbstractNumberValidationRuleDomain, N extends Number >{

  /**
   * Used to generate a new instance of the class where we can test the common methods/processing between classes.
   *
   * @return a valid fully populated object
   */
  abstract T generateTestInstance();

  abstract N getValidNumber();

  @Test
  void testEquals() {
    final T basic = this.generateTestInstance();

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {
    final T basic = this.generateTestInstance();

    Assertions.assertNotEquals(basic, null);
    Assertions.assertNotEquals(basic, new DoubleValidationRuleDomain());

    // Check an alternate instance fails
    final T alternate = this.generateTestInstance();
    alternate.setDescription("testEqualsWithInvalid");
    alternate.setMaximumValue(Integer.MAX_VALUE);
    alternate.setMinimumValue(Integer.MIN_VALUE);
    Assertions.assertNotEquals(basic, alternate);
  }

  @Test
  void testIsValidWithInvalid() {
    final T basic = this.generateTestInstance();

    Assertions.assertFalse(basic.isValid(null));
    Assertions.assertFalse(basic.isValid((String) null));
    Assertions.assertFalse(basic.isValid("ABC"));

    final double max = 99999;
    final double min = 12222;
    final String description = "dskhdfkjdsdhf";
    final String unitName = "Knot";
    final DoubleValidationRule
      longRule = new DoubleValidationRuleDomain(max,min,description, unitName);
    Assertions.assertNotEquals(basic, longRule);

    final StringValidationRule stringRule = new StringValidationRuleDomain(description, "test", unitName);
    Assertions.assertNotEquals(basic, stringRule);
  }

  @Test
  void testIsValid() {
    final T basic = this.generateTestInstance();
    Assertions.assertTrue(basic.isValid(this.getValidNumber()));
  }

  @Test
  void testIsValidWithNullMax() {
    final T basic = this.generateTestInstance();
    // configure the maximum number to null to confirm everything becomes true
    basic.setMaximumValue(null);
    Assertions.assertTrue(basic.isValid(this.getValidNumber()));
    Assertions.assertTrue(basic.isValid(Long.MAX_VALUE));

    // We expect this value to be lower than our test minimum and so should fail
    Assertions.assertFalse(basic.isValid(Long.MIN_VALUE));
  }

  @Test
  void testIsValidWithNullMin() {
    final T basic = this.generateTestInstance();
    // configure the maximum number to null to confirm everything becomes true
    basic.setMinimumValue(null);
    Assertions.assertTrue(basic.isValid(this.getValidNumber()));
    Assertions.assertFalse(basic.isValid(Long.MAX_VALUE));

    // We expect this value to be lower than our test maximum and so should pass
    Assertions.assertTrue(basic.isValid(Long.MIN_VALUE));
  }

  @Test
  void testSetUnit() {
    final T basic = this.generateTestInstance();

    final String alternate = "Example";
    basic.setUnit(alternate);
    Assertions.assertEquals(alternate, basic.getUnit());
  }

  @Test
  void testSetUnitWithInvalid() {
    final T basic = this.generateTestInstance();

    final String alternate = "Example";
    basic.setUnit(alternate);
    Assertions.assertEquals(alternate, basic.getUnit());

    basic.setUnit(null);
    Assertions.assertEquals(ValidationRuleConstants.NO_UNITS, basic.getUnit());

    basic.setUnit(alternate);
    Assertions.assertEquals(alternate, basic.getUnit());

    basic.setUnit(" ");
    Assertions.assertEquals(ValidationRuleConstants.NO_UNITS, basic.getUnit());

  }
}
