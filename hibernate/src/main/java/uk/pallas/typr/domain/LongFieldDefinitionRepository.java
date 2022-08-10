package uk.pallas.typr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.typr.entities.v1.domain.LongFieldDefinitionDomain;
import uk.pallas.typr.entities.v1.domain.StringFieldDefinitionDomain;

import java.util.List;

public interface LongFieldDefinitionRepository extends JpaRepository<LongFieldDefinitionDomain, Integer> {
  /**
   * Allows you to search for a field definition by the name of the definition.
   * @param name the name of the field definition.
   * @return null if nothin is found
   */
  List<StringFieldDefinitionDomain> findByName(String name);
}
