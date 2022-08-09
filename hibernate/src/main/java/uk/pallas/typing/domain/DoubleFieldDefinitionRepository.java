package uk.pallas.typing.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.typing.entities.v1.domain.DoubleFieldDefinitionDomain;

public interface DoubleFieldDefinitionRepository extends JpaRepository<DoubleFieldDefinitionDomain, Integer> {
}
