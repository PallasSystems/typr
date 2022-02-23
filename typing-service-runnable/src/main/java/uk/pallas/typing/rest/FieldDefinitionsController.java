package uk.pallas.typing.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;
import uk.pallas.typing.entities.v1.*;
import uk.pallas.typing.entities.v1.jackson.DoubleFieldDefinitionDTO;
import uk.pallas.typing.entities.v1.jackson.LongFieldDefinitionDTO;
import uk.pallas.typing.entities.v1.jackson.StringFieldDefinitionDTO;
import uk.pallas.typing.entities.v1.mapper.FieldDefinitionRegisterTypes;
import uk.pallas.typing.services.FieldDefinitionServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1")
public class FieldDefinitionsController {

  @Autowired
  private FieldDefinitionServices services;

  /**
   * Used to convert the incoming object into a DTO for egress
   * @param definition
   * @return
   */
  private JacksonFieldDefinition getDTO(final FieldDefinition definition) {
    final JacksonFieldDefinition result;

    if (definition instanceof StringFieldDefinition) {
      result = new StringFieldDefinitionDTO((StringFieldDefinition)definition);
    } else if (definition instanceof DoubleFieldDefinition) {
      result = new DoubleFieldDefinitionDTO((DoubleFieldDefinition)definition);
    } else if (definition instanceof LongFieldDefinition) {
      result = new LongFieldDefinitionDTO((LongFieldDefinition)definition);
    } else if (definition instanceof NumberFieldDefinition) {
      result = new DoubleFieldDefinitionDTO((NumberFieldDefinition)definition);
    } else {
      result = null;
    }

    return result;
  }


  @GetMapping("/types")
  public Collection<JacksonFieldDefinition> getTypes() {

    final Collection<FieldDefinition> definitions = this.services.getFieldDefinitions();

    final Collection<JacksonFieldDefinition> results;
    if (null == definitions || definitions.isEmpty()) {
      results = new ArrayList<>();
    } else {
      results = definitions.stream().filter(result -> null != result).map(result -> this.getDTO(result)).collect(Collectors.toList());
    }

    return results;
  }

  @PutMapping("/type")
  public JacksonFieldDefinition putType(@RequestBody final JacksonFieldDefinition definition) {
    final FieldDefinition saved = this.services.saveFieldDefintion(definition);
    return this.getDTO(saved);
  }
}
