package uk.pallas.systems.typr.domain.entities.v1.validation.wrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.CategoryDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;

class CountryCodeRuleWrapperDomainTest {
  @Test
  void testConstructor() {

    // Create Validation rule to be attached
    final long max = 99999;
    final long min = 12222;
    final String description = "CountryCodeRuleWrapperDomainTest-testConstructor";
    final String unitName = "Knot";
    final LongValidationRule rule = new LongValidationRuleDomain(max,min,description, unitName);
    // A valid Country code
    final String countryCode = "GBR";
    // Use the constructor to confirm it works as expected.
    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDomain(countryCode, rule);

    Assertions.assertEquals(countryCode, basic.getCountryCode());
    Assertions.assertEquals(rule, basic.getRule());
  }

  @Test
  void testCopyConstructor() {

    // Create Validation rule to be attached
    final double max = 5555.5;
    final double min = 222.3;
    final String description = "CountryCodeRuleWrapperDomainTest-testCopyConstructor";
    final String unitName = "Knot";
    final DoubleValidationRule rule = new DoubleValidationRuleDomain(max,min,description, unitName);
    // A valid Country code
    final String countryCode = "ABW";

    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDomain(countryCode, rule);
    final CountryCodeWrapper copy = new CountryCodeRuleWrapperDomain(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testEquals() {
    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDomain();

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {
    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDomain();


    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals("Test", basic);
    Assertions.assertNotEquals(basic, Double.parseDouble("543.3"), 0.0);
    Assertions.assertNotEquals(basic, new CategoryDomain());

    final DoubleValidationRule doubleRule = new DoubleValidationRuleDomain();
    Assertions.assertNotEquals(basic, doubleRule);
  }

}
