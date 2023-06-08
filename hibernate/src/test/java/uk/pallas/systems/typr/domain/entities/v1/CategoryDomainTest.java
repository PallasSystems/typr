package uk.pallas.systems.typr.domain.entities.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;

class CategoryDomainTest {
  @Test
  void testConstructor() {
    final String name = "name";
    final String description = "CategoryDomainTest-testConstructor";
    final Category basic = new CategoryDomain(name, description);
    Assertions.assertEquals(name, basic.getName());
    Assertions.assertEquals(description, basic.getDescription());
  }

  @Test
  void testCopyConstructor() {
    final String name = "name";
    final String description = "CategoryDomainTest-testCopyConstructor";
    final Category basic = new CategoryDomain(name, description);
    final Category copy = new CategoryDomain(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testEquals() {
    final String name = "name";
    final String description = "CategoryDomainTest-testEquals";
    final Category basic = new CategoryDomain(name, description);

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {
    final String name = "name";
    final String description = "CategoryDomainTest-testEqualsWithInvalid";
    final Category basic = new CategoryDomain(name, description);

    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals(basic, new CategoryDomain());
  }
}
