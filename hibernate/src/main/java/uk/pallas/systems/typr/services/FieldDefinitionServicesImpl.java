package uk.pallas.systems.typr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.systems.typr.domain.*;
import uk.pallas.systems.typr.entities.v1.*;
import uk.pallas.systems.typr.entities.v1.domain.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FieldDefinitionServicesImpl implements FieldDefinitionServices {

  @Autowired
  private DoubleFieldDefinitionRepository doubleDAO;

  @Autowired
  private CategoryRepository categoryDAO;

  @Autowired
  private EnumFieldDefinitionRepository enumDAO;

  @Autowired
  private LongFieldDefinitionRepository longDAO;

  @Autowired
  private StringFieldDefinitionRepository stringDAO;

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

    results.addAll(this.doubleDAO.findAll());
    results.addAll(this.enumDAO.findAll());
    results.addAll(this.longDAO.findAll());
    results.addAll(this.stringDAO.findAll());

    return results;
  }

  @Override
  public Collection<FieldDefinition> getFieldDefinitionsByCategory(final String category) {
    return null;
  }

  /**
   *
   * @param type
   * @return
   */
  public Collection<FieldDefinition> getFieldDefinitionsByType(final String type) {
    final Collection<FieldDefinition> results = new HashSet<>();

    if ("string".equalsIgnoreCase(type)) {
      results.addAll(this.stringDAO.findAll());
    } else if ("double".equalsIgnoreCase(type)) {
      results.addAll(this.doubleDAO.findAll());
    } else if ("long".equalsIgnoreCase(type)) {
      results.addAll(this.longDAO.findAll());
    } else if ("enum".equalsIgnoreCase(type)) {
      results.addAll(this.enumDAO.findAll());
    } else if ("number".equalsIgnoreCase(type)) {
      results.addAll(this.longDAO.findAll());
      results.addAll(this.doubleDAO.findAll());
    }

    return results;
  }


  /**
   *
   * @param name
   * @return
   */
  public FieldDefinition getFieldDefinitionByName(final String name) {
    final FieldDefinition result;
    final Optional<DoubleFieldDefinitionDomain> dblResults = this.doubleDAO.findById(name);
    if (dblResults.isPresent()) {
      result = dblResults.get();
    } else {
      final Optional<EnumFieldDefinitionDomain> enumResults = this.enumDAO.findById(name);
      if (enumResults.isPresent()) {
        result = enumResults.get();
      } else {
        final Optional<LongFieldDefinitionDomain> longResults = this.longDAO.findById(name);
        if (longResults.isPresent()) {
          result = longResults.get();
        } else {
          final Optional<StringFieldDefinitionDomain> stringResults = this.stringDAO.findById(name);
          result = stringResults.get();
        }
      }
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

    if (definition instanceof StringFieldDefinition) {
      final StringFieldDefinitionDomain domain = new StringFieldDefinitionDomain((StringFieldDefinition)definition);
      result = this.stringDAO.save(domain);
    } else if (definition instanceof DoubleFieldDefinition) {
      final DoubleFieldDefinitionDomain domain = new DoubleFieldDefinitionDomain((DoubleFieldDefinition)definition);
      result = this.doubleDAO.save(domain);
    } else if (definition instanceof EnumFieldDefinition) {
      final EnumFieldDefinitionDomain domain = new EnumFieldDefinitionDomain((EnumFieldDefinition)definition);
      result = this.enumDAO.save(domain);
    } else if (definition instanceof LongFieldDefinition) {
      final LongFieldDefinitionDomain domain = new LongFieldDefinitionDomain((LongFieldDefinition)definition);
      result = this.longDAO.save(domain);
    } else if (definition instanceof NumberFieldDefinition) {
      final DoubleFieldDefinitionDomain domain = new DoubleFieldDefinitionDomain((NumberFieldDefinition)definition);
      result = this.doubleDAO.save(domain);
    } else {
      result = null;
    }

    return result;
  }
}
