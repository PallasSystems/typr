package uk.pallas.systems.typr.domain.entities.v1.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.validation.AbstractValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;

public abstract class AbstractValidationRuleDomainTest<T extends AbstractValidationRuleDomain> {

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
  void testEqualsWithInvalid() {
    final T basic = this.generateTestInstance();

    Assertions.assertFalse(basic.equals(null));
    Assertions.assertFalse(basic.equals("Test"));
    Assertions.assertFalse(basic.equals(Double.parseDouble("543.3")));
    Assertions.assertFalse(basic.equals(new DoubleValidationRuleDomain()));
  }

  @Test
  void testIsValidWithInvalid() {
    final T basic = this.generateTestInstance();

    Assertions.assertFalse(basic.isValid(null));
    Assertions.assertFalse(basic.isValid("ABC"));
  }
}
