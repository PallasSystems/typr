package uk.pallas.systems.typr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.systems.typr.domain.*;
import uk.pallas.systems.typr.domain.entities.v1.CategoryDomain;
import uk.pallas.systems.typr.domain.entities.v1.SingleValidationRuleFieldDefinitionDomain;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FieldDefinitionServiceImpl implements FieldDefinitionService {

  @Autowired
  private CategoryRepository categoryDAO;

  @Autowired
  private SingleValidationRuleFieldDefinitionRepository singleDefinitionDAO;
  @Autowired
  private MultiValidationRuleFieldDefinitionRepository multiDefinitionDAO;


  @Override
  public Collection<Category> getCategories() {
    final List<CategoryDomain> results = this.categoryDAO.findAll();
    return results.stream().map(value -> (Category)value).collect(Collectors.toList());
  }

  @Override
  public Category getCategoryByName(final String name) {
    final Optional<CategoryDomain> results =  this.categoryDAO.findById(name);
    return results.isPresent() ? results.get() : null;
  }

  /**
   *
   * @return
   */
  public Collection<FieldDefinition> getFieldDefinitions() {
    final Collection<FieldDefinition> results = new HashSet<>();

    results.addAll(this.singleDefinitionDAO.findAll());
    results.addAll(this.multiDefinitionDAO.findAll());

    return results;
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

    final Optional<SingleValidationRuleFieldDefinitionDomain> defResults = this.singleDefinitionDAO.findById(name);
    if (defResults.isPresent()) {
      result = defResults.get();
    } else {
      result = null;
    }

    return result;
  }

  /**
   *
   * @param definition
   * @return
   */
  public FieldDefinition saveFieldDefintion(final FieldDefinition definition) {

    final FieldDefinition result;

    if (definition instanceof FieldDefinition) {
      final SingleValidationRuleFieldDefinitionDomain domain = new SingleValidationRuleFieldDefinitionDomain(definition);
      result = this.singleDefinitionDAO.save(domain);
    } else {
      result = null;
    }

    return result;
  }
}
