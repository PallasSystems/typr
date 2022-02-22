package uk.pallas.typing.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;
import uk.pallas.freeman.common.structured.definitions.FieldDefinition;
import uk.pallas.typing.services.FieldDefinitionServices;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class FieldDefinitionsController {

  @Autowired
  private FieldDefinitionServices services;

  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    final ObjectMapper mapper = new ObjectMapper();
    FieldDefinitionRegisterTypes.setupMapper(mapper);
    return mapper;
  }


  @GetMapping("/types")
  public List<FieldDefinition> getTypes() {
    return this.services.getFieldDefinitions();
  }

  @PutMapping("/type")
  public FieldDefinition putType(@RequestBody final FieldDefinition definition) {
    return this.services.saveFieldDefintion(definition);
  }
}
