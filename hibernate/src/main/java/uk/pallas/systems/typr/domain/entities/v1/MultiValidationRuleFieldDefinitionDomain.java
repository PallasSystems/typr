package uk.pallas.systems.typr.domain.entities.v1;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.pallas.systems.typr.domain.entities.v1.validation.EnumValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.multi.CountryCodeRuleWrapperDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.MultiValidationRuleFieldDefinition;
import uk.pallas.systems.typr.entities.v1.SingleValidationRuleFieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.multi.CountryCodeRuleWrapper;
import uk.pallas.systems.typr.entities.v1.validation.multi.RuleWrapper;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The idea of a rule wrapper is to allow us to hold different rules for a field definition, for example postal code
 * is different within each country (e.g. UK Post Code, USA Zip Code, etc..). Similary some things are a construction
 * of other fields (e.g. Hostname will often have a Top Level Domain name included).
 *
 * The wrapper is a base interface for these different combinations to extend.
 */
@Entity
public class MultiValidationRuleFieldDefinitionDomain extends AbstractFieldDefinitionDomain implements MultiValidationRuleFieldDefinition {
    /**
     * Static Logger for the class.
     */
    private static final Log LOGGER = LogFactory.getLog(MultiValidationRuleFieldDefinitionDomain.class);

    /**
     * List of Validation rules for the field definition.
     */
    @OneToMany
    private Collection<CountryCodeRuleWrapperDomain> countryCodeRuleWrappers;

    /** Default Class Constructor. */
    public MultiValidationRuleFieldDefinitionDomain() {
        this(null);
    }

    /** Copy Constructor */
    public MultiValidationRuleFieldDefinitionDomain(final FieldDefinition data) {
        this(null != data ? data.getAcronym() : null, null != data ? data.getCategories() : null,
                null != data ? data.getDescription() : null, null != data ? data.getName() : null,
                null);
    }

    /** Copy Constructor */
    public MultiValidationRuleFieldDefinitionDomain(final MultiValidationRuleFieldDefinition data) {
        this(null != data ? data.getAcronym() : null, null != data ? data.getCategories() : null,
                null != data ? data.getDescription() : null, null != data ? data.getName() : null,
                null != data ? data.getRules() : null);
    }

    /**
     * Class Constructor.
     *
     * @param acryo The shortened name
     * @param cats The categories associated with our object
     * @param desc the description of the rule
     * @param name the name for the rule
     * @param theRulez the Validation rule to apply
     */
    public MultiValidationRuleFieldDefinitionDomain(final String acryo, final Collection<Category> cats, final String desc,
                                                 final String name, final Collection<RuleWrapper> theRulez) {
        super(acryo, cats, desc, name);

        //this.rule = DTOFactory.getValidationRuleDTO(theRulez);
    }
    /**
     * Is the supplied test object something that matches against our field definition regular expression?
     *
     * If validation optional is set to true this will return true, if the supplied object is null, this will always
     * return false. Other-wise this will call toString and then match the field definition regex tp confirm the object
     * matches our desired value.
     *
     * @param toTest to test is valid
     * @return false if the object fails the validation match.
     */
    @Override
    public boolean isValid(final Object toTest) {
        final Collection<String> results = this.getRulesPassed(toTest);
        return null != results && !results.isEmpty();
    }

    /**
     * Retrieves a list of all rules which have confirmed the incoming object is valid.
     * @param toTest the object to test against the rules stored within this object
     * @return an empty list or a list of identifiers for rules.
     */
    @Override
    public Collection<String> getRulesPassed(final Object toTest) {
        final Collection<String> results = new HashSet<>();

        if (null != this.getRules()) {
            for (final RuleWrapper rule: this.getRules()) {
                if (rule.isValid(toTest)) {
                    results.add(rule.toString());
                }
            }
        }

        return results;
    }

    @Override
    public Collection<RuleWrapper> getRules() {
        return new HashSet<>(this.countryCodeRuleWrappers);
    }

    @Override
    public void setRules(final Collection<RuleWrapper> values) {
        this.countryCodeRuleWrappers.clear();

        if (null != values) {
            final Collection<CountryCodeRuleWrapperDomain> countryCodes = values.stream()
                    .filter(value -> (value instanceof CountryCodeRuleWrapper))
                    .map(value -> new CountryCodeRuleWrapperDomain((CountryCodeRuleWrapper)value))
                    .collect(Collectors.toSet());

            this.countryCodeRuleWrappers.addAll(countryCodes);
        }
    }
}
