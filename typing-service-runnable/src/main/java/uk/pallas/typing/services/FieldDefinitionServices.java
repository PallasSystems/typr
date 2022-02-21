package uk.pallas.typing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.freeman.common.structured.definitions.FieldDefinition;
import uk.pallas.typing.domain.StringFieldRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldDefinitionServices {

  @Autowired
  private StringFieldRepository stringRepository;

  public List<FieldDefinition> getFieldDefinitions() {
    final List<FieldDefinition> results = new ArrayList<>();

    results.addAll(this.stringRepository.findAll());

    return results;
  }
}
