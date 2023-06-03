package uk.pallas.systems.typr.domain.entities.v1.validation;

import uk.pallas.systems.typr.domain.entities.v1.CategoryDomain;
import uk.pallas.systems.typr.domain.entities.v1.SingleValidationRuleFieldDefinitionDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;

public final class FieldDefinitionDomainFactory {

  /**
   * Unusued utility class constructor.
   */
  private FieldDefinitionDomainFactory() {
    // empty
  }

  /**
   * Used to convert the incoming object into a DTO for egress.
   *
   * @param definition The definition to convert into a JACKON object.
   * @return Null if the supplied object doesn't map to a DTO type.
   */
  public static SingleValidationRuleFieldDefinitionDomain getFieldDefinitionDomain(final FieldDefinition definition) {
    final SingleValidationRuleFieldDefinitionDomain result;

    if (definition instanceof FieldDefinition) {
      result = new SingleValidationRuleFieldDefinitionDomain(definition);
    } else {
      result = null;
    }

    return result;
  }

  /**
   * Used to convert the incoming object into a DTO for egress.
   *
   * @param definition The definition to convert into a JACKON object.
   * @return Null if the supplied object doesn't map to a DTO type.
   */
  public static CategoryDomain getCategoryDomain(final Category definition) {
    final CategoryDomain result;

    if (definition instanceof Category) {
      result = new CategoryDomain(definition);
    } else {
      result = null;
    }

    return result;
  }

  /**
   * Used to convert the incoming object into a DTO for egress.
   *
   * @param definition The definition to convert into a JACKON object.
   * @return Null if the supplied object doesn't map to a DTO type.
   */
  public static AbstractValidationRuleDomain getValidationRuleDomain(final ValidationRule definition) {
    final AbstractValidationRuleDomain result;

    if (definition instanceof EnumValidationRule) {
      result = new EnumValidationRuleDomain((EnumValidationRule) definition);
    } else if (definition instanceof EnumValidationRule) {
      result = new EnumValidationRuleDomain((EnumValidationRule) definition);
    } else if (definition instanceof StringValidationRule) {
      result = new StringValidationRuleDomain((StringValidationRule) definition);
    } else if (definition instanceof DoubleValidationRule) {
      result = new DoubleValidationRuleDomain((DoubleValidationRule) definition);
    } else if (definition instanceof LongValidationRule) {
      result = new LongValidationRuleDomain((LongValidationRule) definition);
    } else {
      result = null;
    }

    return result;
  }
}
