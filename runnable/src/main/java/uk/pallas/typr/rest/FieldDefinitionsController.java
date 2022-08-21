package uk.pallas.typr.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uk.pallas.typr.entities.v1.*;
import uk.pallas.typr.entities.v1.impl.DoubleFieldDefinitionDTO;
import uk.pallas.typr.entities.v1.impl.LongFieldDefinitionDTO;
import uk.pallas.typr.entities.v1.impl.StringFieldDefinitionDTO;
import uk.pallas.typr.services.FieldDefinitionServices;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1")
public class FieldDefinitionsController {

  @Autowired
  private FieldDefinitionServices services;


  @GetMapping("/types")
  public Collection<FieldDefinition> getTypes() {

    final Collection<FieldDefinition> definitions = this.services.getFieldDefinitions();

    final Collection<FieldDefinition> results;
    if (null == definitions || definitions.isEmpty()) {
      results = new ArrayList<>();
    } else {
      results = definitions.stream().filter(result -> null != result).map(result -> this.getDTO(result)).collect(Collectors.toList());
    }

    return results;
  }

  @GetMapping("/types/type/{type}")
  public Collection<FieldDefinition> getFieldDefinitionByType(@PathVariable("type") final String type) {

    final Collection<FieldDefinition> definitions = this.services.getFieldDefinitionsByType(type);

    final Collection<FieldDefinition> results;
    if (null == definitions || definitions.isEmpty()) {
      results = new ArrayList<>();
    } else {
      results = definitions.stream().filter(result -> null != result).map(result -> this.getDTO(result)).collect(Collectors.toList());
    }

    return results;
  }

  @GetMapping("/type/name/{name}")
  public FieldDefinition getFieldDefinitionByName(@PathVariable("name") final String name) {
    final FieldDefinition result = this.getDTO(this.services.getFieldDefinition(name));

    if (null == result) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No type definition found for:" + name);
    }

    return result;
  }

  @PutMapping("/type")
  public FieldDefinition putType(@RequestBody final FieldDefinition definition) {
    final FieldDefinition saved = this.services.saveFieldDefintion(definition);
    return this.getDTO(saved);
  }

  /**
   * Used to convert the incoming object into a DTO for egress
   * @param definition
   * @return
   */
  private FieldDefinition getDTO(final FieldDefinition definition) {
    final FieldDefinition result;

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
}
