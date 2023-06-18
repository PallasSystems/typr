package uk.pallas.systems.typr.rest.entities.v1;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;

class FieldDefinitionDTOTest {
  
  @Test
  void testConstructor() {
    final String fieldName = "FieldDefinitionDTOTest - testConstructor";
    final String acronym = "FDDT-tc";
    final String description = "FieldDefinitionDTOTest-testConstructor";

    final long max = 1234;
    final long min = 987;
    final String ruleDescription = "LongValidationRuleDTO-testConstructor";
    final String unitName = "Knot";
    final ValidationRule rule = new LongValidationRuleDTO(max,min,description, unitName);
    final Set<ValidationRule> rules = new HashSet<>();
    rules.add(rule);

    final FieldDefinitionDTO basic = new FieldDefinitionDTO(acronym, new HashSet<>(), description,
      fieldName, rules);

    Assertions.assertEquals(fieldName, basic.getName());
    Assertions.assertEquals(acronym, basic.getAcronym());
    Assertions.assertEquals(description, basic.getDescription());

    Assertions.assertNotNull(basic.getRules());
    Assertions.assertEquals(rules, basic.getRules());
  }

  @Test
  void testCopyConstructor() {
    final String fieldName = "FieldDefinitionDTOTest - testCopyConstructor";
    final String acronym = "FDDT-tc";
    final String description = "FieldDefinitionDTOTest-testCopyConstructor";

    final long max = 9876;
    final long min = 432;
    final String ruleDescription = "LongValidationRuleDTO-testCopyConstructor";
    final String unitName = "Degree Angle";
    final ValidationRule rule = new LongValidationRuleDTO(max,min,description, unitName);
    final Set<ValidationRule> rules = new HashSet<>();
    rules.add(rule);

    final FieldDefinitionDTO basic = new FieldDefinitionDTO(acronym, new HashSet<>(), description,
      fieldName, rules);

    final FieldDefinitionDTO copy = new FieldDefinitionDTO(basic);

    Assertions.assertEquals(basic, copy);
  }

}
