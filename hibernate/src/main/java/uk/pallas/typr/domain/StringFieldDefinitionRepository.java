package uk.pallas.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.typr.entities.v1.domain.StringFieldDefinitionDomain;

public interface StringFieldDefinitionRepository extends JpaRepository<StringFieldDefinitionDomain, Integer> {
}
