package uk.pallas.systems.typr.rest.entities.v1.validation.wrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;
import uk.pallas.systems.typr.rest.entities.v1.CategoryDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.StringValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;

class CountryCodeRuleWrapperDTOTest {
  @Test
  void testConstructor() {
    // Create Validation rule to be attached
    final String description = "CountryCodeRuleWrapperDomainTest-testDescription";
    final String detect = "detect-testDescription";
    final String extract = "extract-testDescription";
    final StringValidationRule rule = new StringValidationRuleDTO(description, detect, extract);
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
  void testDescription() {
    // Create Validation rule to be attached
    final double max = 5555.5;
    final double min = 222.3;
    final String description = "CountryCodeRuleWrapperDTOTest-testDescription";
    final String unitName = "Knot";
    final DoubleValidationRule rule = new DoubleValidationRuleDTO(max,min,description, unitName);
    Assertions.assertEquals(description, rule.getDescription());
    // Now create the wrapper.
    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDTO();
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

    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDTO();
    Assertions.assertEquals(basic, basic);

    final String description = "CountryCodeRuleWrapperDTOTest-testEquals";
    final String detect = "detect-testEquals";
    final String extract = "extract-testEquals";
    final StringValidationRule rule = new StringValidationRuleDTO(description, detect, extract);
    //
    final CountryCodeWrapper formed = new CountryCodeRuleWrapperDTO("GBR", rule);
    Assertions.assertEquals(formed, formed);
    Assertions.assertNotEquals(basic, formed);
  }

  @Test
  void testHashCode() {

    final CountryCodeWrapper basic = new CountryCodeRuleWrapperDTO();
    Assertions.assertEquals(basic.hashCode(), basic.hashCode());

    final String description = "CountryCodeRuleWrapperDTOTest-testEquals";
    final String detect = "detect-testEquals";
    final String extract = "extract-testEquals";
    final StringValidationRule rule = new StringValidationRuleDTO(description, detect, extract);
    //
    final CountryCodeWrapper formed = new CountryCodeRuleWrapperDTO("GBR", rule);
    Assertions.assertEquals(formed.hashCode(), formed.hashCode());
    Assertions.assertNotEquals(basic.hashCode(), formed.hashCode());
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
