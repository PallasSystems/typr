package uk.pallas.systems.typr.entities.v1.validation.multi;

import com.neovisionaries.i18n.CountryCode;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

public interface CountryCodeRuleWrapper extends RuleWrapper {

    CountryCode getCountryCode();

    void setCountryCode(final CountryCode code);
}
