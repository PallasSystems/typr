package uk.pallas.systems.typr.domain.entities.v1.validation.number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
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
    Assertions.assertNotEquals(basic, "Test");
    Assertions.assertNotEquals(basic, Double.parseDouble("543.3"));
    Assertions.assertNotEquals(basic, new DoubleValidationRuleDomain());

    // Check an alternate instance fails
    final T alternate = this.generateTestInstance();
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

    Assertions.assertFalse(basic.isValid(this.getValidNumber()));
  }
}
