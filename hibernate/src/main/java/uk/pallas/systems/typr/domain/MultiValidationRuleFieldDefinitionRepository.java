package uk.pallas.systems.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.systems.typr.domain.entities.v1.MultiValidationRuleFieldDefinitionDomain;
import uk.pallas.systems.typr.domain.entities.v1.SingleValidationRuleFieldDefinitionDomain;

public interface MultiValidationRuleFieldDefinitionRepository extends JpaRepository<MultiValidationRuleFieldDefinitionDomain, String> {

}
