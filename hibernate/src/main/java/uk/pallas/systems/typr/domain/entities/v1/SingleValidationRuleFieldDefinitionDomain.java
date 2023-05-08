package uk.pallas.systems.typr.domain.entities.v1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.DoubleValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.SingleValidationRuleFieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.EnumValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.StringValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.domain.entities.v1.validation.EnumValidationRuleDomain;
import uk.pallas.systems.typr.domain.entities.v1.validation.StringValidationRuleDomain;
import uk.pallas.systems.typr.entities.v1.validation.number.DoubleValidationRule;
import uk.pallas.systems.typr.entities.v1.validation.number.LongValidationRule;
import uk.pallas.systems.typr.domain.entities.v1.validation.number.LongValidationRuleDomain;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class SingleValidationRuleFieldDefinitionDomain extends AbstractFieldDefinitionDomain implements SingleValidationRuleFieldDefinition {
    /**
     * Static Logger for the class.
     */
    private static final Log LOGGER = LogFactory.getLog(SingleValidationRuleFieldDefinitionDomain.class);

    /**
     * Validation for the field definition.
     */
    @OneToOne
    private EnumValidationRuleDomain enumRule;

    /**
     * Validation for the field definition.
     */
    @OneToOne
    private StringValidationRuleDomain stringRule;

    /**
     * Validation for the field definition.
     */
    @OneToOne
    private DoubleValidationRuleDomain doubleRule;

    /**
     * Validation for the field definition.
     */
    @OneToOne
    private LongValidationRuleDomain longRule;

    /**
     * Default Class Constructor.
     */
    public SingleValidationRuleFieldDefinitionDomain() {
        this(null);
    }

    /**
     * Copy Constructor
     */
    public SingleValidationRuleFieldDefinitionDomain(final FieldDefinition data) {
        this(null != data ? data.getAcronym() : null, null != data ? data.getCategories() : null,
                null != data ? data.getDescription() : null, null != data ? data.getName() : null, null);
    }

    /**
     * Copy Constructor
     */
    public SingleValidationRuleFieldDefinitionDomain(final SingleValidationRuleFieldDefinition data) {
        this(null != data ? data.getAcronym() : null, null != data ? data.getCategories() : null,
                null != data ? data.getDescription() : null, null != data ? data.getName() : null,
                null != data ? data.getRule() : null);
    }

    /**
     * Class Constructor.
     *
     * @param acryo    The shortened name
     * @param cats     The categories associated with our object
     * @param desc     the description of the rule
     * @param name     the name for the rule
     * @param theRulez the Validation rules to apply
     */
    public SingleValidationRuleFieldDefinitionDomain(final String acryo, final Collection<Category> cats, final String desc, final String name,
                                                     final ValidationRule theRulez) {
        super(acryo, cats, desc, name);

        this.setRule(theRulez);
    }

    /**
     * Compares the supplied object to this one, it checks the supplied object is a FieldDefinition and then checks
     * the definition applies validation, its name and description to see if they are matching objects.
     *
     * @param toCompare the object to compare (can be null or a child class, etc..)
     * @return false if the name/validation and description fields in a field definition are
     * different (or it isn't a field definition).
     */
    @Override
    public boolean equals(final Object toCompare) {

        final boolean result;
        if (this == toCompare) {
            result = true;
        } else if (toCompare instanceof SingleValidationRuleFieldDefinition) {
            final SingleValidationRuleFieldDefinition that = (SingleValidationRuleFieldDefinition) toCompare;
            result = super.equals(toCompare) && Objects.equals(this.getRule(), that.getRule());
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Generates a Unique hashcode for the field definition class.
     *
     * @return a valid integer representation of this object,
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getRule());
    }

    /**
     * Is the supplied test object something that matches against our field definition regular expression?
     * <p>
     * If validation optional is set to true this will return true, if the supplied object is null, this will always
     * return false. Other-wise this will call toString and then match the field definition regex tp confirm the object
     * matches our desired value.
     *
     * @param toTest to test is valid
     * @return false if the object fails the validation match.
     */
    @Override
    public boolean isValid(final Object toTest) {
        final boolean result;

        if (null == this.getRule()) {
            result = false;
            LOGGER.warn(String.format("isValid - No Rule to test object: %s", toTest));
        } else {
            result = this.getRule().isValid(toTest);
        }

        return result;
    }

    /**
     * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
     *
     * @return non null value (if field definition is valid).
     */
    public DoubleValidationRule getDoubleRule() {
        return this.doubleRule;
    }

    /**
     * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
     *
     * @param value the rules to add to our field definition
     */
    public void setDoubleRule(final DoubleValidationRule value) {
        this.doubleRule = new DoubleValidationRuleDomain((DoubleValidationRule) value);
    }

    /**
     * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
     *
     * @return non null value (if field definition is valid).
     */
    public EnumValidationRule getEnumRule() {
        return this.enumRule;
    }

    /**
     * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
     *
     * @param value the rules to add to our field definition
     */
    public void setEnumRule(final EnumValidationRule value) {
        this.enumRule = new EnumValidationRuleDomain((EnumValidationRule) value);
    }

    /**
     * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
     *
     * @return non null value (if field definition is valid).
     */
    public LongValidationRule getLongRule() {
        return this.longRule;
    }

    /**
     * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
     *
     * @param value the rules to add to our field definition
     */
    public void setLongRule(final LongValidationRule value) {
        this.longRule = new LongValidationRuleDomain((LongValidationRule) value);
    }

    /**
     * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
     *
     * @return non null value (if field definition is valid).
     */
    public StringValidationRule getStringRule() {
        return this.stringRule;
    }

    /**
     * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
     *
     * @param value the rules to add to our field definition
     */
    public void setStringRule(final StringValidationRule value) {
        this.stringRule = new StringValidationRuleDomain((StringValidationRule) value);
    }

    /**
     * Retrieves the rule of the field definition e.g. post code, uk mobile, IPv4 Address, etc..
     *
     * @return non null value (if field definition is valid).
     */
    public ValidationRule getRule() {
        final ValidationRule result;

        if (null == this.getDoubleRule()) {
            if (null == this.getEnumRule()) {
                if (null == this.getLongRule()) {
                    if (null == this.getStringRule()) {
                        result = null;
                    } else {
                        result = this.getStringRule();
                    }
                } else {
                    result = this.getLongRule();
                }
            } else {
                result = this.getEnumRule();
            }
        } else {
            result = this.getDoubleRule();
        }

        return result;
    }

    /**
     * Sets the validation for the field definition e.g. Post Code, Ipv6, Mobile Country Code, etc...
     *
     * @param value the rules to add to our field definition
     */
    public void setRule(final ValidationRule value) {

        this.doubleRule = null;
        this.enumRule = null;
        this.longRule = null;
        this.stringRule = null;

        if (value instanceof DoubleValidationRule) {
            this.doubleRule = new DoubleValidationRuleDomain((DoubleValidationRule) value);
        } else if (value instanceof EnumValidationRule) {
            this.enumRule = new EnumValidationRuleDomain((EnumValidationRule) value);
        } else if (value instanceof LongValidationRule) {
            this.longRule = new LongValidationRuleDomain((LongValidationRule) value);
        } else if (value instanceof StringValidationRule) {
            this.stringRule = new StringValidationRuleDomain((StringValidationRule) value);
        } else {
            LOGGER.warn(String.format("appendRule - Unsupported Rule Type supplied: %s", value));
        }
    }
}