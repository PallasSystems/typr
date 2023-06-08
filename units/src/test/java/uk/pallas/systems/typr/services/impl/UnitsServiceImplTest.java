package uk.pallas.systems.typr.services.impl;

import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;
import uk.pallas.systems.typr.services.UnitsService;

class UnitsServiceImplTest {

  /**
   * Checks various invalid Field Definitions which should fail.
   */
  @Test
  void testIsValidStringWithInvalid() {
    final UnitsService service = new UnitsServiceImpl();

    Assertions.assertFalse(service.isValid((String) null));
    Assertions.assertFalse(service.isValid(""));
    Assertions.assertFalse(service.isValid(" "));
    Assertions.assertFalse(service.isValid("WXYZ"));
  }

  /**
   * Checks various invalid Field Definitions which should fail.
   */
  @Test
  void testIsValidFieldDefWithInvalid() {
    final UnitsService service = new UnitsServiceImpl();

    Assertions.assertFalse(service.isValid((FieldDefinition) null));
  }

  /**
   * Checks we get more than 1 unit type in response to our query.
   */
  @Test
  void testGetUnits() {
    final UnitsService service = new UnitsServiceImpl();

    final Collection<String> units = service.getUnits();
    Assertions.assertNotNull(units);
    Assertions.assertFalse(units.isEmpty());

    for (final String unit : units) {
      Assertions.assertTrue(service.isValid(unit));
    }
  }


  /**
   * Creates a mocked String Validation Rile wrapped in an invalid country code. This should cause
   * the country service to return false.
   */
  @Test
  void testIsValidFieldDefWrapped() {
    final UnitsService service = new UnitsServiceImpl();

    // Generate a Mock for the Country Code Wrapper
    final LongValidationRule validDef = Mockito.mock(LongValidationRule.class);
    Mockito.when(validDef.getUnit()).thenReturn("Knot");

    final CountryCodeWrapper wrapper = Mockito.mock(CountryCodeWrapper.class);
    Mockito.when(wrapper.getRule()).thenReturn(validDef);
    Mockito.when(wrapper.getCountryCode()).thenReturn("GBR");
    final Collection<ValidationRule> rules = new HashSet<>();
    rules.add(wrapper);

    // Now configure A Field definition to correctly return
    final FieldDefinition definition = Mockito.mock(FieldDefinition.class);
    Mockito.when(definition.getRules()).thenReturn(rules);

    Assertions.assertTrue(service.isValid(definition));
  }
}
