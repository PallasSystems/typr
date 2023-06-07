package uk.pallas.systems.typr.rest.entities.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;

class CategoryDTOTest {
  @Test
  void testConstructor() {
    final String name = "name";
    final String description = "CategoryDTOTest-testConstructor";
    final Category basic = new CategoryDTO(name, description);
    Assertions.assertEquals(name, basic.getName());
    Assertions.assertEquals(description, basic.getDescription());
  }

  @Test
  void testCopyConstructor() {
    final String name = "name";
    final String description = "CategoryDTOTest-testCopyConstructor";
    final Category basic = new CategoryDTO(name, description);
    final Category copy = new CategoryDTO(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testEquals() {
    final String name = "name";
    final String description = "CategoryDTOTest-testEquals";
    final Category basic = new CategoryDTO(name, description);

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {
    final String name = "name";
    final String description = "CategoryDTOTest-testEqualsWithInvalid";
    final Category basic = new CategoryDTO(name, description);


    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals("Test", basic);
    Assertions.assertNotEquals(basic, Double.parseDouble("543.3"));
    Assertions.assertNotEquals(basic, new CategoryDTO());

    final DoubleValidationRule doubleRule = new DoubleValidationRuleDTO();
    Assertions.assertNotEquals(basic, doubleRule);
  }
}
