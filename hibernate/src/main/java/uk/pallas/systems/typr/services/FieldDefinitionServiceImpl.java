package uk.pallas.systems.typr.services;

import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.systems.typr.domain.CategoryRepository;
import uk.pallas.systems.typr.domain.FieldDefinitionRespository;
import uk.pallas.systems.typr.domain.entities.v1.CategoryDomain;
import uk.pallas.systems.typr.domain.entities.v1.FieldDefinitionDomain;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class FieldDefinitionServiceImpl implements FieldDefinitionService {

  @Autowired
  private CategoryRepository categoryDAO;

  @Autowired
  private FieldDefinitionRespository fieldDefDAO;


  @Override
  public Collection<Category> getCategories() {
    final List<CategoryDomain> results = this.categoryDAO.findAll();
    return results.stream().map(value -> (Category)value).toList();
  }

  @Override
  public Category getCategoryByName(final String name) {
    final Optional<CategoryDomain> results =  this.categoryDAO.findById(name);
    return results.orElse(null);
  }

  /**
   *
   * @return
   */
  public Collection<FieldDefinition> getFieldDefinitions() {
    return new HashSet<>(this.fieldDefDAO.findAll());
  }

  @Override
  public Collection<FieldDefinition> getFieldDefinitionsByCategory(final String category) {
    return null;
  }

  /**
   *
   * @param name
   * @return
   */
  public FieldDefinition getFieldDefinitionByName(final String name) {
    final FieldDefinition result;

    final Optional<FieldDefinitionDomain> defResults = this.fieldDefDAO.findById(name);
    result = defResults.orElse(null);

    return result;
  }

  /**
   *
   * @param fieldDef
   * @return
   */
  public FieldDefinition saveFieldDefintion(final FieldDefinition fieldDef) {



    return this.fieldDefDAO.save(new FieldDefinitionDomain(fieldDef));
  }
}
