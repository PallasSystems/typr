package uk.pallas.systems.typr.domain.entities.v1.validation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.wrapper.CountryCodeRuleWrapperDomain;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;

class EnumValidationRuleDomainTest extends AbstractValidationRuleDomainTest<EnumValidationRuleDomain>{

  /**
   * This will generate a collection of alphaNumeric strings to use to test various 'enumerates'.
   * @return a collection of size > 0
   */
  Collection<String> generateValidTestData() {
    final Set<String> results = new HashSet<>();

    final Random random = new Random();
    final int maxEnumsToGenerate = 100;
    // ensure we generate at least 1 string
    int entries = random.nextInt(maxEnumsToGenerate);
    while (entries < 1) {
      entries = random.nextInt(maxEnumsToGenerate);
    }

    // Maximum length we allow a string to be.
    final int maxStringLength = 4096;
    // On ASCII 0 is number 48, while z is 122, this
    final int alphaNumericRange = (int)'z' - (int) '0';

    for (int index = 0; index < entries; index++) {
      final StringBuilder enumerate = new StringBuilder();
      int length = random.nextInt(maxStringLength);
      while (length < 1) {
        length = random.nextInt(maxStringLength);
      }

      for (int strPos = 0; strPos < length; strPos++) {
        enumerate.append((char)(random.nextInt(alphaNumericRange) + '0'));
      }

      results.add(enumerate.toString());
    }

    return results;
  }

  @Override
  EnumValidationRuleDomain generateTestInstance() {
    final Collection<String> enums = this.generateValidTestData();
    return new EnumValidationRuleDomain("Description", enums);
  }

  @Test
  void testConstructor() {
    // Create Validation rule to be attached
    final String description = "EnumValidationRuleDomainTest-testConstructor";
    final Collection<String> enums = this.generateValidTestData();

    final EnumValidationRule basic = new EnumValidationRuleDomain(description, enums);

    Assertions.assertEquals(description, basic.getDescription());
    Assertions.assertEquals(enums, basic.getEnumerates());
  }

  @Test
  void testCopyConstructor() {
    // Create Validation rule to be attached
    final String description = "EnumValidationRuleDomainTest-testCopyConstructor";
    final Collection<String> enums = this.generateValidTestData();

    final EnumValidationRule basic = new EnumValidationRuleDomain(description, enums);
    final EnumValidationRule copy = new EnumValidationRuleDomain(basic);

    Assertions.assertEquals(basic, copy);
  }
}
