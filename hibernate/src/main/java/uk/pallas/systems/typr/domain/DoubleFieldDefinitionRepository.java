package uk.pallas.systems.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.systems.typr.entities.v1.domain.DoubleFieldDefinitionDomain;
import uk.pallas.systems.typr.entities.v1.domain.StringFieldDefinitionDomain;

import java.util.List;

public interface DoubleFieldDefinitionRepository extends JpaRepository<DoubleFieldDefinitionDomain, String> {

}
