package uk.pallas.systems.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.systems.typr.entities.v1.domain.EnumFieldDefinitionDomain;

import java.util.List;

public interface EnumFieldDefinitionRepository extends JpaRepository<EnumFieldDefinitionDomain, String> {

}
