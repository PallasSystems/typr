package uk.pallas.systems.typr.domain.entities.v1.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.pallas.systems.typr.domain.entities.v1.AbstractFieldDefinitionDomain;
import uk.pallas.systems.typr.domain.entities.v1.SingleValidationRuleFieldDefinitionDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.AbstractValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.EnumValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.SingleValidationRuleFieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;

public class DomainFactory {
    /** Static Logger for the class. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DomainFactory.class);

    public static AbstractFieldDefinitionDomain getFieldDefinitionDTO(final FieldDefinition value) {
        final AbstractFieldDefinitionDomain result;

        if (value instanceof SingleValidationRuleFieldDefinition) {
            result = new SingleValidationRuleFieldDefinitionDomain((SingleValidationRuleFieldDefinition)value);
        } else {
            result = null;
            LOGGER.warn(String.format("getFieldDefinitionDTO - Unsupported Definition supplied: %s", value));
        }

        return result;
    }


    public static AbstractValidationRuleDomain getValidationRuleDomain(final ValidationRule value) {
        final AbstractValidationRuleDomain result;

        if (value instanceof DoubleValidationRule) {
            result = new DoubleValidationRuleDomain((DoubleValidationRule)value);
        } else if (value instanceof EnumValidationRule) {
            result = new EnumValidationRuleDomain((EnumValidationRule)value);
        } else if (value instanceof LongValidationRule) {
            result = new LongValidationRuleDomain((LongValidationRule)value);
        } else if (value instanceof StringValidationRule) {
            result = new StringValidationRuleDomain((StringValidationRule)value);
        } else {
            result = null;
            LOGGER.warn(String.format("getValidationRuleDomain - Unsupported Rule Type supplied: %s", value));
        }

        return result;
    }

    /** Unusued utility class constructor. */
    private DomainFactory() {
        super();
    }
}
