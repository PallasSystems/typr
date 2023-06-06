package uk.pallas.systems.typr.services.impl;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Service;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.wrapper.CountryCodeWrapper;
import uk.pallas.systems.typr.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

  /**
   * {@inheritDoc}
   *
   * @param value the value to test if it is correct.
   * @return false if the definition is invalid or if a country code wrapper exists with an invalid code.
   */
  @Override
  public boolean isValidISO31661Alpha3(final String value) {
    return null != CountryCode.getByAlpha3Code(value);
  }


  /**
   * {@inheritDoc}
   *
   * @param definition the value to test if it is correct.
   * @return false if the definition is invalid or if a country code wrapper exists with an invalid code.
   */
  @Override
  public boolean isValidISO31661Alpha3(final FieldDefinition definition) {
    final boolean result;

    if (null == definition || null == definition.getRules() || definition.getRules().isEmpty()) {
      result = false;
    } else {
      boolean valid = true;
      for (final ValidationRule rule : definition.getRules()) {
        if (rule instanceof CountryCodeWrapper) {
          valid = this.isValidISO31661Alpha3(((CountryCodeWrapper) rule).getCountryCode());
        }
        // if we find an invalid rule then stop and assume the whole definition isn't valid.
        if (!valid) {
          break;
        }
      }

      result = valid;
    }

    return result;
  }
}
