package uk.pallas.systems.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.systems.typr.entities.v1.domain.DoubleFieldDefinitionDomain;
import uk.pallas.systems.typr.entities.v1.domain.StringFieldDefinitionDomain;

import java.util.List;

public interface DoubleFieldDefinitionRepository extends JpaRepository<DoubleFieldDefinitionDomain, Integer> {
  /**
   * Allows you to search for a field definition by the name of the definition.
   * @param name the name of the field definition.
   * @return null if nothin is found
   */
  List<StringFieldDefinitionDomain> findByName(String name);
}
