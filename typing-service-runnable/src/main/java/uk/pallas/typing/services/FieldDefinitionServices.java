package uk.pallas.typing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.freeman.common.structured.definitions.FieldDefinition;
import uk.pallas.freeman.common.structured.definitions.StringFieldDefinition;
import uk.pallas.freeman.common.structured.definitions.impl.StringFieldDefinitionImpl;
import uk.pallas.typing.domain.StringFieldDefinitionRepository;
import uk.pallas.typing.domain.entities.StringFieldDefinitionDomain;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldDefinitionServices {

  @Autowired
  private StringFieldDefinitionRepository stringRepository;

  public List<FieldDefinition> getFieldDefinitions() {
    final List<FieldDefinition> results = new ArrayList<>();

    results.addAll(this.stringRepository.findAll());

    final StringFieldDefinition example = new StringFieldDefinitionImpl();
    example.setName("UK Post Code");
    example.setDescription("Postal codes used in the United Kingdom, British Overseas Territories and Crown dependencies are known as postcodes (originally, postal codes).[1] They are alphanumeric and were adopted nationally between 11 October 1959 and 1974, having been devised by the General Post Office (Royal Mail).[2] A full postcode is known as a \"postcode unit\" and designates an area with several addresses or a single major delivery point.");
    example.setRegex("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$");

    results.add(example);

    return results;
  }

  public FieldDefinition saveFieldDefintion(final FieldDefinition definition) {

    final FieldDefinition result;

    if (definition instanceof StringFieldDefinition) {
      result = this.stringRepository.save(new StringFieldDefinitionDomain((StringFieldDefinition)definition));
    } else {
      result = null;
    }

    return result;
  }
}
