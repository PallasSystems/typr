package uk.pallas.typing.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.typing.entities.v1.domain.LongFieldDefinitionDomain;
import uk.pallas.typing.entities.v1.domain.StringFieldDefinitionDomain;

public interface LongFieldDefinitionRepository extends JpaRepository<LongFieldDefinitionDomain, Integer> {
}
