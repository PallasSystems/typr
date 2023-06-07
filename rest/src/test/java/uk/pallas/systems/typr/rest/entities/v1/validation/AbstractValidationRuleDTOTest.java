package uk.pallas.systems.typr.rest.entities.v1.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;

public abstract class AbstractValidationRuleDTOTest<T extends AbstractValidationRuleDTO> {

  /**
   * Used to generate a new instance of the class where we can test the common methods/processing between classes.
   * @return a valid fully populated object
   */
   abstract T generateTestInstance();

  @Test
  void testEquals() {
    final T basic = this.generateTestInstance();

    Assertions.assertEquals(basic, basic);
  }


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
    Assertions.assertNotEquals(basic, new DoubleValidationRuleDTO());

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
  }
}
