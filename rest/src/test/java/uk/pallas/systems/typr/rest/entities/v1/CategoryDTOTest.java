package uk.pallas.systems.typr.rest.entities.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.Category;

class CategoryDTOTest {
  @Test
  void testConstructor() {
    final String name = "testConstructor";
    final String description = "CategoryDTOTest-testConstructor";
    final Category basic = new CategoryDTO(name, description);
    Assertions.assertEquals(name, basic.getName());
    Assertions.assertEquals(description, basic.getDescription());
  }

  @Test
  void testCopyConstructor() {
    final String name = "testCopyConstructor";
    final String description = "CategoryDTOTest-testCopyConstructor";
    final Category basic = new CategoryDTO(name, description);
    final Category copy = new CategoryDTO(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testEquals() {
    final String name = "testEquals";
    final String description = "CategoryDTOTest-testEquals";
    final Category basic = new CategoryDTO(name, description);

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {
    final String name = "testEqualsWithInvalid";
    final String description = "CategoryDTOTest-testEqualsWithInvalid";
    final Category basic = new CategoryDTO(name, description);

    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals(basic, new CategoryDTO());
  }
}
