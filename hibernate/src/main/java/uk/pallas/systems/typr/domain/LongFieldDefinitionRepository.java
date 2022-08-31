package uk.pallas.systems.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.systems.typr.entities.v1.domain.LongFieldDefinitionDomain;
import uk.pallas.systems.typr.entities.v1.domain.StringFieldDefinitionDomain;

import java.util.List;

public interface LongFieldDefinitionRepository extends JpaRepository<LongFieldDefinitionDomain, String> {

}
