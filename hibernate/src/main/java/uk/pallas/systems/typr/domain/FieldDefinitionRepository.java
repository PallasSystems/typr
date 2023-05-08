package uk.pallas.systems.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.systems.typr.domain.entities.v1.SingleValidationRuleFieldDefinitionDomain;

public interface FieldDefinitionRepository extends JpaRepository<SingleValidationRuleFieldDefinitionDomain, String> {

}
