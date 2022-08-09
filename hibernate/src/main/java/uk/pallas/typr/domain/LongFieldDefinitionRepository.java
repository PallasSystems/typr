package uk.pallas.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.typr.entities.v1.domain.LongFieldDefinitionDomain;

public interface LongFieldDefinitionRepository extends JpaRepository<LongFieldDefinitionDomain, Integer> {
}
