package uk.pallas.systems.typr.entities.v1.validation.multi;

import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;

public interface OrderedRuleWrapper extends RuleWrapper {

    int getPosition();

    void setPosition(final int value);


}
