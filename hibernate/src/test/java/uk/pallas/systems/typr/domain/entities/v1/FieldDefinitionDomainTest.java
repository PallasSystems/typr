package uk.pallas.systems.typr.domain.entities.v1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

class FieldDefinitionDomainTest {

  /**
   * This will generate a test object within a number validation rule, that has a range of 100-10000.
   * @return a valid and completly formed Field Definition.
   */
  private FieldDefinition generateTestObject() {
    final String fieldName = "FieldDefinitionDomainTest-generateTestObject";
    final String acronym = "FDDT-gto";
    final String description = "FieldDefinitionDomainTest-generateTestObject";

    final long max = 10000;
    final long min = 100;
    final String ruleDescription = "LongValidationRuleDomain-generateTestObject";
    final String unitName = "Degree Angle";
    final ValidationRule rule = new LongValidationRuleDomain(max,min,ruleDescription, unitName);
    final Set<ValidationRule> rules = new HashSet<>();
    rules.add(rule);

    return new FieldDefinitionDomain(acronym, new HashSet<>(), description, fieldName, rules);
  }

  @Test
  void testConstructor() {
    final String fieldName = "FieldDefinitionDomainTest - testConstructor";
    final String acronym = "FDDT-tc";
    final String description = "FieldDefinitionDomainTest-testConstructor";

    final long max = 1234;
    final long min = 987;
    final String ruleDescription = "LongValidationRuleDomain-testConstructor";
    final String unitName = "Knot";
    final ValidationRule rule = new LongValidationRuleDomain(max,min,ruleDescription, unitName);
    final Set<ValidationRule> rules = new HashSet<>();
    rules.add(rule);

    final FieldDefinition basic = new FieldDefinitionDomain(acronym, new HashSet<>(), description,
      fieldName, rules);

    Assertions.assertEquals(fieldName, basic.getName());
    Assertions.assertEquals(acronym, basic.getAcronym());
    Assertions.assertEquals(description, basic.getDescription());

    Assertions.assertNotNull(basic.getRules());
    Assertions.assertEquals(rules, basic.getRules());
  }

  @Test
  void testCopyConstructor() {
    final FieldDefinition basic = this.generateTestObject();
    final FieldDefinition copy = new FieldDefinitionDomain(basic);

    Assertions.assertEquals(basic, copy);
    Assertions.assertEquals(copy, copy);
    Assertions.assertEquals(basic.hashCode(), copy.hashCode());
  }

  @Test
  void testCopyConstructorWithInvalid() {
    final FieldDefinition basic = new FieldDefinitionDomain();
    final FieldDefinition nullCopy = new FieldDefinitionDomain(null);

    Assertions.assertEquals(basic, nullCopy);
    Assertions.assertEquals(basic.hashCode(), nullCopy.hashCode());
  }

  @Test
  void testEqualsWithInvalid() {
    final FieldDefinition basic = this.generateTestObject();

    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals(basic, new FieldDefinitionDomain());
    Assertions.assertNotEquals(basic.hashCode(), new FieldDefinitionDomain().hashCode());
  }

  @Test
  void testIsValid() {
    final FieldDefinition basic = this.generateTestObject();

    // Supply a value which is within our target range, this should pass and return the toString
    // of the rule we created above.
    final Collection<String> passed = basic.getRulesPassed(555);
    Assertions.assertNotNull(passed);
    Assertions.assertFalse(passed.isEmpty());
    Assertions.assertEquals(basic.getRules().size(), passed.size());
  }

  @Test
  void testGetRulesPassed() {
    final FieldDefinition basic = this.generateTestObject();

    // Supply a value which is within our target range, this should pass and return the toString
    // of the rule we created above.
    final Collection<String> passed = basic.getRulesPassed(555);
    Assertions.assertNotNull(passed);
    Assertions.assertFalse(passed.isEmpty());
    Assertions.assertEquals(basic.getRules().size(), passed.size());
  }

  @Test
  void testGetRulesPassedWithInvalid() {
    final FieldDefinition basic = this.generateTestObject();

    // Supply a value which is within our target range, this should pass and return the toString
    // of the rule we created above.
    final Collection<String> passed = basic.getRulesPassed(null);
    Assertions.assertNotNull(passed);
    Assertions.assertTrue(passed.isEmpty());
  }
}
