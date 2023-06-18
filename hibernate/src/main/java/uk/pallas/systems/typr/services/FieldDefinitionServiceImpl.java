package uk.pallas.systems.typr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.systems.typr.domain.FieldDefinitionRespository;
import uk.pallas.systems.typr.domain.entities.v1.FieldDefinitionDomain;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Service
public class FieldDefinitionServiceImpl implements FieldDefinitionService {

  @Autowired
  private FieldDefinitionRespository fieldDefDAO;

  /**
   *
   * @return
   */
  public Collection<FieldDefinition> getFieldDefinitions() {
    return new HashSet<>(this.fieldDefDAO.findAll());
  }

  @Override
  public Collection<FieldDefinition> getFieldDefinitionsByCategory(final java.lang.String string) {
    return null;
  }

  /**
   *
   * @param name
   * @return
   */
  public FieldDefinition getFieldDefinitionByName(final java.lang.String name) {
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
