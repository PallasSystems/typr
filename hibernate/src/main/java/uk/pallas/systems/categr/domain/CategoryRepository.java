package uk.pallas.systems.categr.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.pallas.systems.categr.domain.entities.v1.CategoryDomain;

/**
 * Extends Spring Data JPA to provide access to Category objects within a relational data store.
 */
public interface CategoryRepository extends JpaRepository<CategoryDomain, String> {

}
