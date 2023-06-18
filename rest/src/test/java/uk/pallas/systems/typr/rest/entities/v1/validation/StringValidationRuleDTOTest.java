package uk.pallas.systems.typr.rest.entities.v1.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;

class StringValidationRuleDTOTest extends AbstractValidationRuleDTOTest<StringValidationRuleDTO> {

  private static final String UK_POST_CODE_DETECT_REGEX = "([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})";

  private static final String UK_POST_CODE_EXTRACT_REGEX = "Meh";

  @Override
  StringValidationRuleDTO generateTestInstance() {
    final String description = "StringValidationRuleDTOTest";
    return new StringValidationRuleDTO(description, UK_POST_CODE_DETECT_REGEX, UK_POST_CODE_EXTRACT_REGEX);
  }

  @Test
  void testConstructor() {
    // Create Validation rule to be attached
    final String description = "StringValidationRuleDTOTest-testConstructor";
    final StringValidationRule
      basic = new StringValidationRuleDTO(description, UK_POST_CODE_DETECT_REGEX, UK_POST_CODE_EXTRACT_REGEX);

    Assertions.assertEquals(description, basic.getDescription());
    Assertions.assertEquals(UK_POST_CODE_DETECT_REGEX, basic.getDetectRegex());
    Assertions.assertEquals(UK_POST_CODE_EXTRACT_REGEX, basic.getExtractRegex());
  }

  @Test
  void testCopyConstructor() {
    // Create Validation rule to be attached
    final String description = "StringValidationRuleDTOTest-testCopyConstructor";

    final StringValidationRule
      basic = new StringValidationRuleDTO(description, UK_POST_CODE_DETECT_REGEX, UK_POST_CODE_EXTRACT_REGEX);
    final StringValidationRule copy = new StringValidationRuleDTO(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testIsValid() {
    // Create Validation rule to be attached
    final String description = "StringValidationRuleDTOTest-testIsValid";
    final StringValidationRule
      basic = new StringValidationRuleDTO(description, UK_POST_CODE_DETECT_REGEX, UK_POST_CODE_EXTRACT_REGEX);

    // This is a valid UK Post Code
    Assertions.assertTrue(basic.isValid("PL5 3HL"));
  }
}
