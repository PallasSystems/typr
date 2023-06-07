package uk.pallas.systems.typr.services.impl;

import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
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
}
