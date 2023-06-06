package uk.pallas.systems.typr.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
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
   * Checks various invalid Field Definitions which should fail.
   */
  @Test
  void testIsValidISO31661Alpha3FieldDef() {
    final CountryService service = new CountryServiceImpl();

    Assertions.assertFalse(service.isValidISO31661Alpha3((FieldDefinition) null));
  }
}
