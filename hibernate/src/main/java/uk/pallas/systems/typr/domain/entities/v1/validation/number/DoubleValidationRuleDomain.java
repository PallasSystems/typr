package uk.pallas.systems.typr.domain.entities.v1.validation.number;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;

@Entity
@Table(name = "val_double_rules")
public class DoubleValidationRuleDomain extends AbstractNumberValidationRuleDomain<Double>
  implements DoubleValidationRule {

  /** Static Logger for the class. */
  private static final Log LOGGER = LogFactory.getLog(DoubleValidationRuleDomain.class);

  /**
   * Default constructor, sets everything to null and makes validation optional.
   */
  public DoubleValidationRuleDomain() {
    this(null);
  }

  /**
   * Copy Constructor, passes up to parent Copy Constructor and sets Minimum/Maximum values.
   *
   * @param data the object to create a duplicate off.
   */
  public DoubleValidationRuleDomain(final DoubleValidationRule data) {
    super(data);
  }

  /**
   * Constructor, allows us to set the internal abstract fields.
   *
   * @param max The upper bound allowed for the field
   * @param min the lower bound allowed for the field
   * @param detailedDescription Detailed description of the field definition.
   * @param unitName Defines a Unit name, to be used to when the same field has different units.
   */
  public DoubleValidationRuleDomain(final Double max, final Double min, final String detailedDescription,
                                    final String unitName) {
    super(max, min, detailedDescription, unitName);
  }

  /**
   * Attempts to confirm the supplied value is a whole number and will return it is true.
   *
   * @param toConvert the object to test (if Number or a String holding a valid whole number.
   * @return null .. or a valid Number.
   */
  @Override
  protected Double getNumber(final Object toConvert) {

    Double result = null;

    if (toConvert instanceof Number) {
      result = ((Number) toConvert).doubleValue();
    } else if (null != toConvert) {
      try {
        result = Double.parseDouble(toConvert.toString());
      } catch (final NumberFormatException exception) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info(String.format("getNumber - Unable to convert %s to Double", toConvert));
        }
      }
    }

    return result;
  }
}
