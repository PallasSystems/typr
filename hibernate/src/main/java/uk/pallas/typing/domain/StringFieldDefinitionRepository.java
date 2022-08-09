package uk.pallas.typing.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.typing.entities.v1.domain.StringFieldDefinitionDomain;

public interface StringFieldDefinitionRepository extends JpaRepository<StringFieldDefinitionDomain, Integer> {
}
