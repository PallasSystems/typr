package uk.pallas.systems.typr.rest.entities.v1.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.MultiValidationRuleFieldDefinition;
import uk.pallas.systems.typr.entities.v1.SingleValidationRuleFieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.rest.entities.v1.AbstractFieldDefinitionDTO;
import uk.pallas.systems.typr.rest.entities.v1.MultiValidationRuleFieldDefinitionDTO;
import uk.pallas.systems.typr.rest.entities.v1.SingleValidationRuleFieldDefinitionDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.AbstractValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.EnumValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.StringValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.DoubleValidationRuleDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;

public class DTOFactory {
    /** Static Logger for the class. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DTOFactory.class);

    public static AbstractFieldDefinitionDTO getFieldDefinitionDTO(final FieldDefinition value) {
        final AbstractFieldDefinitionDTO result;

        if (value instanceof SingleValidationRuleFieldDefinition) {
            result = new SingleValidationRuleFieldDefinitionDTO((SingleValidationRuleFieldDefinition)value);
        } else if (value instanceof MultiValidationRuleFieldDefinition) {
            result = new MultiValidationRuleFieldDefinitionDTO((MultiValidationRuleFieldDefinition)value);
        } else {
            result = null;
            LOGGER.warn(String.format("getFieldDefinitionDTO - Unsupported Definition supplied: %s", value));
        }

        return result;
    }


    public static AbstractValidationRuleDTO getValidationRuleDTO(final ValidationRule value) {
        final AbstractValidationRuleDTO result;

        if (value instanceof DoubleValidationRule) {
            result = new DoubleValidationRuleDTO((DoubleValidationRule)value);
        } else if (value instanceof EnumValidationRule) {
            result = new EnumValidationRuleDTO((EnumValidationRule)value);
        } else if (value instanceof LongValidationRule) {
            result = new LongValidationRuleDTO((LongValidationRule)value);
        } else if (value instanceof StringValidationRule) {
            result = new StringValidationRuleDTO((StringValidationRule)value);
        } else {
            result = null;
            LOGGER.warn(String.format("getValidationRuleDTO - Unsupported Rule Type supplied: %s", value));
        }

        return result;
    }

    /** Unusued utility class constructor. */
    private DTOFactory() {
        super();
    }
}
