package uk.pallas.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.typr.entities.v1.domain.DoubleFieldDefinitionDomain;

public interface DoubleFieldDefinitionRepository extends JpaRepository<DoubleFieldDefinitionDomain, Integer> {
}
