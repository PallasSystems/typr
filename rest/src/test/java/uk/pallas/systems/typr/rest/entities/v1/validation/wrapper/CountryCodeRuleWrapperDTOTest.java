package uk.pallas.systems.typr.rest.entities.v1.validation.wrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;
import uk.pallas.systems.typr.rest.entities.v1.CategoryDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;

class CountryCodeRuleWrapperDTOTest {
  @Test
  void testConstructor() {

    // Create Validation rule to be attached
    final long max = 99999;
    final long min = 12222;
    final String description = "CountryCodeRuleWrapperDTOTest-testConstructor";
    final String unitName = "Knot";
    final LongValidationRule rule = new LongValidationRuleDTO(max,min,description, unitName);
    // A valid Country code
    final String countryCode = "GBR";
    // Use the constructor to confirm it works as expected.
    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDTO(countryCode, rule);

    Assertions.assertEquals(countryCode, basic.getCountryCode());
    Assertions.assertEquals(rule, basic.getRule());
  }

  @Test
  void testCopyConstructor() {

    // Create Validation rule to be attached
    final double max = 5555.5;
    final double min = 222.3;
    final String description = "CountryCodeRuleWrapperDTOTest-testCopyConstructor";
    final String unitName = "Knot";
    final DoubleValidationRule rule = new DoubleValidationRuleDTO(max,min,description, unitName);
    // A valid Country code
    final String countryCode = "ABW";

    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDTO(countryCode, rule);
    final CountryCodeWrapper copy = new CountryCodeRuleWrapperDTO(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testEquals() {
    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDTO();

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {
    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDTO();


    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals("Test", basic);
    Assertions.assertNotEquals(basic, Double.parseDouble("543.3"));
    Assertions.assertNotEquals(basic, new CategoryDTO());

    final DoubleValidationRule doubleRule = new DoubleValidationRuleDTO();
    Assertions.assertNotEquals(basic, doubleRule);
  }

}
