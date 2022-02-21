package uk.pallas.typing.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.typing.domain.entities.StringFieldDefinitionDomain;

public interface StringFieldRepository extends JpaRepository<StringFieldDefinitionDomain, Integer> {
}
