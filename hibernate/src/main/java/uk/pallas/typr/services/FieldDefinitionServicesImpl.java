package uk.pallas.typr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.typr.domain.DoubleFieldDefinitionRepository;
import uk.pallas.typr.domain.LongFieldDefinitionRepository;
import uk.pallas.typr.domain.StringFieldDefinitionRepository;
import uk.pallas.typr.entities.v1.FieldDefinition;
import uk.pallas.typr.entities.v1.DoubleFieldDefinition;
import uk.pallas.typr.entities.v1.LongFieldDefinition;
import uk.pallas.typr.entities.v1.NumberFieldDefinition;
import uk.pallas.typr.entities.v1.StringFieldDefinition;
import uk.pallas.typr.entities.v1.domain.DoubleFieldDefinitionDomain;
import uk.pallas.typr.entities.v1.domain.LongFieldDefinitionDomain;
import uk.pallas.typr.entities.v1.domain.StringFieldDefinitionDomain;

import java.util.Collection;
import java.util.HashSet;

@Service
public class FieldDefinitionServicesImpl implements FieldDefinitionServices {

  @Autowired
  private DoubleFieldDefinitionRepository doubleDAO;

  @Autowired
  private LongFieldDefinitionRepository longDAO;

  @Autowired
  private StringFieldDefinitionRepository stringDAO;

  public Collection<FieldDefinition> getFieldDefinitions() {
    return this.getFieldDefinitionsByType(null);
  }

  public Collection<FieldDefinition> getFieldDefinitionsByType(final String type) {
    final Collection<FieldDefinition> results = new HashSet<>();

    if (null == type || type.isBlank()) {
      results.addAll(this.doubleDAO.findAll());
      results.addAll(this.longDAO.findAll());
      results.addAll(this.stringDAO.findAll());
    } else if ("string".equalsIgnoreCase(type)) {
      results.addAll(this.stringDAO.findAll());
    } else if ("double".equalsIgnoreCase(type)) {
      results.addAll(this.doubleDAO.findAll());
    } else if ("long".equalsIgnoreCase(type)) {
      results.addAll(this.longDAO.findAll());
    } else if ("number".equalsIgnoreCase(type)) {
      results.addAll(this.longDAO.findAll());
      results.addAll(this.doubleDAO.findAll());
    }

    return results;
  }


  public FieldDefinition getFieldDefinition(final String name) {
    final Collection<FieldDefinition> results = new HashSet<>();

    results.addAll(this.doubleDAO.findByName(name));
    results.addAll(this.longDAO.findByName(name));
    results.addAll(this.stringDAO.findByName(name));

    return results.isEmpty() ? null : results.stream().findFirst().get();
  }

  public FieldDefinition saveFieldDefintion(final FieldDefinition definition) {

    final FieldDefinition result;

    if (definition instanceof StringFieldDefinition) {
      final StringFieldDefinitionDomain domain = new StringFieldDefinitionDomain((StringFieldDefinition)definition);
      result = this.stringDAO.save(domain);
    } else if (definition instanceof DoubleFieldDefinition) {
      final DoubleFieldDefinitionDomain domain = new DoubleFieldDefinitionDomain((DoubleFieldDefinition)definition);
      result = this.doubleDAO.save(domain);
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
