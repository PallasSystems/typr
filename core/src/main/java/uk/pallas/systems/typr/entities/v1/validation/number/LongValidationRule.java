package uk.pallas.systems.typr.entities.v1.validation.number;

/**
 * This is used for whole integer numbers when used in fields and allows us to validate that they are within an
 * acceptable range. E.g. we would expect an Age (years) to have a minimum of 0 and a maximum of 150.
 */
public interface LongValidationRule extends NumberValidationRule<Long> {
}
