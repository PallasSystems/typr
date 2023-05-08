package uk.pallas.systems.typr.rest.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.MultiValidationRuleFieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.multi.RuleWrapper;
import uk.pallas.systems.typr.rest.entities.v1.validation.multi.CountryCodeRuleWrapperDTO;

import java.util.Collection;
import java.util.HashSet;

public class MultiValidationRuleFieldDefinitionDTO extends AbstractFieldDefinitionDTO implements MultiValidationRuleFieldDefinition {

    @ArraySchema(schema = @Schema(description = "List of Validation rules for the field definition.",
                                  anyOf={ CountryCodeRuleWrapperDTO.class }),
                 uniqueItems = true,
                 minItems=1)
    private Collection<RuleWrapper> rules;

    /** Default Class Constructor. */
    public MultiValidationRuleFieldDefinitionDTO() {
        this(null);
    }

    /** Copy Constructor */
    public MultiValidationRuleFieldDefinitionDTO(final FieldDefinition data) {
        this(null != data ? data.getAcronym() : null, null != data ? data.getCategories() : null,
                null != data ? data.getDescription() : null, null != data ? data.getName() : null,
                null);
    }

    /** Copy Constructor */
    public MultiValidationRuleFieldDefinitionDTO(final MultiValidationRuleFieldDefinition data) {
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
    public MultiValidationRuleFieldDefinitionDTO(final String acryo, final Collection<Category> cats, final String desc,
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

    @JsonIgnore
    @Override
    public Collection<String> getRulesPassed(final Object toTest) {
        final Collection<String> results = new HashSet<>();

        if (null != this.getRules()) {
            for (final RuleWrapper rule: this.getRules()) {
                if (null != rule.getRule() && rule.getRule().isValid(toTest)) {
                    results.add(rule.getId());
                }
            }
        }

        return results;
    }

    @Override
    public Collection<RuleWrapper> getRules() {
        return new HashSet<>(this.rules);
    }

    @Override
    public void setRules(final Collection<RuleWrapper> values) {
        this.rules.clear();

        if (null != values) {
            this.rules.addAll(values);
        }
    }
}