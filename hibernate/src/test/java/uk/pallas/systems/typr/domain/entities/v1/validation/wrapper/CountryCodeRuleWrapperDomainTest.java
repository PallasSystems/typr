package uk.pallas.systems.typr.domain.entities.v1.validation.wrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.CategoryDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
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
  void testDescription() {
    // Create Validation rule to be attached
    final String description = "CountryCodeRuleWrapperDomainTest-testDescription";
    final String detect = "detect-testDescription";
    final String extract = "extract-testDescription";
    final StringValidationRule rule = new StringValidationRuleDomain(description, detect, extract);
    Assertions.assertEquals(description, rule.getDescription());
    // Now create the wrapper.
    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDomain();
    Assertions.assertNull(basic.getDescription());
    // sets the rule
    basic.setRule(rule);
    Assertions.assertEquals(description, basic.getDescription());
    // Now change the description and confirm it has changed in the rule and wrapper.
    final String alternate = "blah blah";
    basic.setDescription(alternate);
    Assertions.assertEquals(alternate, basic.getDescription());
    Assertions.assertEquals(alternate, basic.getRule().getDescription());
  }

  @Test
  void testEquals() {

    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDomain();
    Assertions.assertEquals(basic, basic);

    final String description = "CountryCodeRuleWrapperDomainTest-testEquals";
    final String detect = "detect-testEquals";
    final String extract = "extract-testEquals";
    final StringValidationRule rule = new StringValidationRuleDomain(description, detect, extract);
    //
    final CountryCodeWrapper formed = new CountryCodeRuleWrapperDomain("GBR", rule);
    Assertions.assertEquals(formed, formed);
    Assertions.assertNotEquals(basic, formed);
  }

  @Test
  void testHashCode() {

    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDomain();
    Assertions.assertEquals(basic.hashCode(), basic.hashCode());

    final String description = "CountryCodeRuleWrapperDomainTest-testEquals";
    final String detect = "detect-testEquals";
    final String extract = "extract-testEquals";
    final StringValidationRule rule = new StringValidationRuleDomain(description, detect, extract);
    //
    final CountryCodeWrapper formed = new CountryCodeRuleWrapperDomain("GBR", rule);
    Assertions.assertEquals(formed.hashCode(), formed.hashCode());
    Assertions.assertNotEquals(basic.hashCode(), formed.hashCode());
  }

  @Test
  void testEqualsWithInvalid() {
    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDomain();

    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals(basic, new CountryCodeRuleWrapperDomain());
  }

}
