package uk.pallas.systems.typr.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.systems.typr.domain.entities.v1.FieldDefinitionDomain;

public interface FieldDefinitionRespository extends JpaRepository<FieldDefinitionDomain, String> {

  List<FieldDefinitionDomain> findByCategories(String category);

}
