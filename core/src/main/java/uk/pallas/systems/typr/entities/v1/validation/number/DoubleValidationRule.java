package uk.pallas.systems.typr.entities.v1.validation.number;

/**
 * This rule assumes the field is a valid double number and allows us to place ranges on what value the field can be
 * (e.g. Direction could have a Min 0.0 and Max 360.0. So then when a System supplied 342.5 we know its valid.
 */
public interface DoubleValidationRule extends NumberValidationRule<Double> {
}
