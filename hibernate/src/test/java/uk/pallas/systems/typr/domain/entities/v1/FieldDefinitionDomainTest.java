package uk.pallas.systems.typr.domain.entities.v1;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

class FieldDefinitionDomainTest {


  @Test
  void testConstructor() {
    final String fieldName = "FieldDefinitionDomainTest - testConstructor";
    final String acronym = "FDDT-tc";
    final String description = "FieldDefinitionDomainTest-testConstructor";

    final long max = 1234;
    final long min = 987;
    final String ruleDescription = "LongValidationRuleDomain-testConstructor";
    final String unitName = "Knot";
    final ValidationRule rule = new LongValidationRuleDomain(max,min,description, unitName);
    final Set<ValidationRule> rules = new HashSet<>();
    rules.add(rule);

    final FieldDefinitionDomain basic = new FieldDefinitionDomain(acronym, new HashSet<>(), description,
      fieldName, rules);

    Assertions.assertEquals(fieldName, basic.getName());
    Assertions.assertEquals(acronym, basic.getAcronym());
    Assertions.assertEquals(description, basic.getDescription());

    Assertions.assertNotNull(basic.getRules());
    Assertions.assertEquals(rules, basic.getRules());
  }

  @Test
  void testCopyConstructor() {
    final String fieldName = "FieldDefinitionDomainTest - testCopyConstructor";
    final String acronym = "FDDT-tc";
    final String description = "FieldDefinitionDomainTest-testCopyConstructor";

    final long max = 9876;
    final long min = 432;
    final String ruleDescription = "LongValidationRuleDomain-testCopyConstructor";
    final String unitName = "Degree Angle";
    final ValidationRule rule = new LongValidationRuleDomain(max,min,description, unitName);
    final Set<ValidationRule> rules = new HashSet<>();
    rules.add(rule);

    final FieldDefinitionDomain basic = new FieldDefinitionDomain(acronym, new HashSet<>(), description,
      fieldName, rules);

    final FieldDefinitionDomain copy = new FieldDefinitionDomain(basic);

    Assertions.assertEquals(basic, copy);
  }

}
