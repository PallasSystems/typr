package uk.pallas.systems.typr.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uk.pallas.systems.typr.entities.v1.*;
import uk.pallas.systems.typr.entities.v1.impl.DoubleFieldDefinitionDTO;
import uk.pallas.systems.typr.entities.v1.impl.LongFieldDefinitionDTO;
import uk.pallas.systems.typr.entities.v1.impl.StringFieldDefinitionDTO;
import uk.pallas.systems.typr.services.FieldDefinitionServices;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This Defines an interface for retrieving Field Definitions stored within Typr.
 */
@RestController
@RequestMapping("api/type/v1")
public class FieldDefinitionsController {

  @Autowired
  private FieldDefinitionServices services;

  /**
   * Retrieves all Field Definitions held within Typr.
   * @return an empty collection if no definitions of the supplied type exist
   */
  @GetMapping("/types")
  public Collection<FieldDefinition> getTypes() {

    final Collection<FieldDefinition> definitions = this.services.getFieldDefinitions();
    if (null == definitions || definitions.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No definitions stored within Typr" );
    }

    return definitions.stream().filter(result -> null != result)
                               .map(result -> this.getDTO(result))
                               .collect(Collectors.toList());
  }

  /**
   * Retrieves all Field Definitions of a specific type within the system
   * @param type the Field Definition type eg. string, enum, double, long, number
   * @return A collection of definitions if the supplied type exist, or a NOT FOUND if nothing is found.
   */
  @GetMapping("/types/type/{type}")
  public Collection<FieldDefinition> getFieldDefinitionByType(@PathVariable("type") final String type) {

    final Collection<FieldDefinition> definitions = this.services.getFieldDefinitionsByType(type);
    if (null == definitions || definitions.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No definitions for type:" + type);
    }

    return definitions.stream().filter(result -> null != result)
                               .map(result -> this.getDTO(result))
                               .collect(Collectors.toList());
  }

  /**
   * Retrieves a Field Definition
   * @param name the name of the field definition we want to retrieve from Typr.
   * @return A definition if one exists, or a NOT FOUND if nothing is found.
   */
  @GetMapping("/type/name/{name}")
  public FieldDefinition getFieldDefinitionByName(@PathVariable("name") final String name) {
    final FieldDefinition result = this.getDTO(this.services.getFieldDefinition(name));

    if (null == result) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No type definition found for:" + name);
    }

    return result;
  }

  /**
   * Used to insert new Field Definitions into the Typr system.
   * @param definition the definition to insert into the system.
   * @return the stored version of the field definition.
   */
  @PutMapping("/type")
  public FieldDefinition putType(@RequestBody final FieldDefinition definition) {
    final FieldDefinition saved = this.services.saveFieldDefintion(definition);
    return this.getDTO(saved);
  }

  /**
   * Used to convert the incoming object into a DTO for egress
   * @param definition the definition to convert into a JACKON object
   * @return null if the supplied object doesn't map to a DTO type.
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
