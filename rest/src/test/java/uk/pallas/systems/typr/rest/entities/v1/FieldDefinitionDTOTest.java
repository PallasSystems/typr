package uk.pallas.systems.typr.rest.entities.v1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;

class FieldDefinitionDTOTest {

  /**
   * This will generate a test object within a number validation rule, that has a range of 100-10000.
   * @return a valid and completly formed Field Definition.
   */
  private FieldDefinition generateTestObject() {
    final String fieldName = "FieldDefinitionDomainTest-generateTestObject-name";
    final String acronym = "FDDT-gto";
    final String description = "FieldDefinitionDomainTest-generateTestObject-description";

    final long max = 10000;
    final long min = 100;
    final String ruleDescription = "LongValidationRuleDomain-generateTestObject";
    final String unitName = "Degree Angle";
    final ValidationRule rule = new LongValidationRuleDTO(max,min,ruleDescription, unitName);
    final Set<ValidationRule> rules = new HashSet<>();
    rules.add(rule);

    return new FieldDefinitionDTO(acronym, new HashSet<>(), description, fieldName, rules);
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
    final ValidationRule rule = new LongValidationRuleDTO(max,min,ruleDescription, unitName);
    final Set<ValidationRule> rules = new HashSet<>();
    rules.add(rule);

    final FieldDefinition basic = new FieldDefinitionDTO(acronym, new HashSet<>(), description,
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
    final FieldDefinition copy = new FieldDefinitionDTO(basic);

    Assertions.assertEquals(basic, copy);
    Assertions.assertTrue(copy.equals(copy));
    Assertions.assertEquals(basic.hashCode(), copy.hashCode());
  }

  @Test
  void testCopyConstructorWithInvalid() {
    final FieldDefinition basic = new FieldDefinitionDTO();
    final FieldDefinition nullCopy = new FieldDefinitionDTO(null);

    Assertions.assertEquals(basic, nullCopy);
    Assertions.assertEquals(basic.hashCode(), nullCopy.hashCode());
  }

  @Test
  void testEqualsWithInvalid() {
    final FieldDefinition basic = this.generateTestObject();

    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals(basic, new FieldDefinitionDTO());
    Assertions.assertNotEquals(basic.hashCode(), new FieldDefinitionDTO().hashCode());
  }

  @Test
  void testIsValid() {
    final FieldDefinition basic = this.generateTestObject();

    // Supply a value which is within our target range, this should pass and return the toString
    // of the rule we created above.
    Assertions.assertTrue(basic.isValid(555));
    Assertions.assertTrue(basic.isValid("666"));
  }

  @Test
  void testIsValidWithInvalid() {
    final FieldDefinition basic = this.generateTestObject();

    // Supply a value which is within our target range, this should pass and return the toString
    // of the rule we created above.
    Assertions.assertFalse(basic.isValid(null));
    Assertions.assertFalse(basic.isValid(Double.MIN_VALUE));
    Assertions.assertFalse(basic.isValid(Long.MAX_VALUE));
    Assertions.assertFalse(basic.isValid("ABC"));
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
    final Collection<String> nullPassed = basic.getRulesPassed(null);
    Assertions.assertNotNull(nullPassed);
    Assertions.assertTrue(nullPassed.isEmpty());

    final Collection<String> belowRangePassed = basic.getRulesPassed(Double.MIN_VALUE);
    Assertions.assertNotNull(belowRangePassed);
    Assertions.assertTrue(belowRangePassed.isEmpty());

    final Collection<String> aboveRangePassed = basic.getRulesPassed(Long.MAX_VALUE);
    Assertions.assertNotNull(aboveRangePassed);
    Assertions.assertTrue(aboveRangePassed.isEmpty());

    final Collection<String> nonNumberPassed = basic.getRulesPassed("abc");
    Assertions.assertNotNull(nonNumberPassed);
    Assertions.assertTrue(nonNumberPassed.isEmpty());
  }
}
