package uk.pallas.systems.typr.services.impl;

import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;
import uk.pallas.systems.typr.services.CountryService;

/**
 * Used to test the new Country service.
 */
class CountryServiceImplTest {

  /**
   * Checks various invalid Field Definitions which should fail.
   */
  @Test
  void testIsValidISO31661Alpha3StringWithInvalid() {
    final CountryService service = new CountryServiceImpl();

    Assertions.assertFalse(service.isValidISO31661Alpha3((String) null));
    Assertions.assertFalse(service.isValidISO31661Alpha3(""));
    Assertions.assertFalse(service.isValidISO31661Alpha3(" "));
    Assertions.assertFalse(service.isValidISO31661Alpha3("WXYZ"));
  }

  /**
   * Creates a mocked String Validation Rile wrapped in a valid country code, then confirms
   * the Countries service believes this is valid.
   */
  @Test
  void testIsValidISO31661Alpha3FieldDef() {
    final CountryService service = new CountryServiceImpl();

    // Generate a Mock for the Country Code Wrapper
    final StringValidationRule validDef = Mockito.mock(StringValidationRule.class);
    final CountryCodeWrapper wrapper = Mockito.mock(CountryCodeWrapper.class);
    Mockito.when(wrapper.getRule()).thenReturn(validDef);
    Mockito.when(wrapper.getCountryCode()).thenReturn("GBR");
    final Collection<ValidationRule> rules = new HashSet<>();
    rules.add(wrapper);

    // Now configure A Field definition to correctly return
    final FieldDefinition definition = Mockito.mock(FieldDefinition.class);
    Mockito.when(definition.getRules()).thenReturn(rules);

    Assertions.assertTrue(service.isValidISO31661Alpha3(definition));
  }

  /**
   * Creates a mocked String Validation Rile wrapped in an invalid country code. This should cause
   * the country service to return false.
   */
  @Test
  void testIsValidISO31661Alpha3FieldDefWithInvalidCountryCode() {
    final CountryService service = new CountryServiceImpl();

    // Generate a Mock for the Country Code Wrapper
    final StringValidationRule validDef = Mockito.mock(StringValidationRule.class);
    final CountryCodeWrapper wrapper = Mockito.mock(CountryCodeWrapper.class);
    Mockito.when(wrapper.getRule()).thenReturn(validDef);
    Mockito.when(wrapper.getCountryCode()).thenReturn("POP");
    final Collection<ValidationRule> rules = new HashSet<>();
    rules.add(wrapper);

    // Now configure A Field definition to correctly return
    final FieldDefinition definition = Mockito.mock(FieldDefinition.class);
    Mockito.when(definition.getRules()).thenReturn(rules);

    Assertions.assertFalse(service.isValidISO31661Alpha3(definition));
  }

  /**
   * Checks various invalid Field Definitions which should fail.
   */
  @Test
  void testIsValidISO31661Alpha3FieldDefWithNull() {
    final CountryService service = new CountryServiceImpl();

    Assertions.assertFalse(service.isValidISO31661Alpha3((FieldDefinition) null));
  }

  /**
   * Confirms if there is no country code the service returns true.
   */
  @Test
  void testIsValidISO31661Alpha3FieldDefWithNoWrapper() {
    final CountryService service = new CountryServiceImpl();

    // Generate a Mock for the Country Code Wrapper
    final StringValidationRule validDef = Mockito.mock(StringValidationRule.class);
    final Collection<ValidationRule> rules = new HashSet<>();
    rules.add(validDef);

    // Now configure A Field definition to correctly return
    final FieldDefinition definition = Mockito.mock(FieldDefinition.class);
    Mockito.when(definition.getRules()).thenReturn(rules);

    Assertions.assertTrue(service.isValidISO31661Alpha3(definition));
  }
}
