package uk.pallas.systems.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.systems.typr.domain.entities.v1.SingleValidationRuleFieldDefinitionDomain;

public interface SingleValidationRuleFieldDefinitionRepository
  extends JpaRepository<SingleValidationRuleFieldDefinitionDomain, String> {

}
