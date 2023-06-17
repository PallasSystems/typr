package uk.pallas.systems.categr.services;

import java.util.Collection;
import uk.pallas.systems.categr.entities.v1.Category;

public interface CategoriesDAOService {

  Category findByName(String id);

  Collection<Category> findAll();
}
