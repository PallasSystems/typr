package uk.pallas.systems.typr.rest;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.rest.entities.v1.utils.DTOFactory;
import uk.pallas.systems.typr.rest.entities.v1.MultiValidationRuleFieldDefinitionDTO;
import uk.pallas.systems.typr.rest.entities.v1.SingleValidationRuleFieldDefinitionDTO;
import uk.pallas.systems.typr.services.FieldDefinitionServices;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * This Defines an interface for retrieving Field Definitions stored within Typr.
 */
@RestController("Typr API")
@RequestMapping("api/type/v1")
public class FieldDefinitionsController {

  /** The backend service to retrieve. */
  @Autowired
  private FieldDefinitionServices services;

  public FieldDefinitionServices getServices() {
    return services;
  }

  public void setServices(FieldDefinitionServices services) {
    this.services = services;
  }

  /**
   * Retrieves all Field Definitions held within Typr.
   * @return A collection of Long, Double, String, Enumerate, etc... field definitions.
   */
  @GetMapping("/types")
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Successfully retrieved data from the database",
                  content = @Content(mediaType = "application/json",
                                     array = @ArraySchema(schema = @Schema(description = "Validation for the field definition.",
                                                                           oneOf={ SingleValidationRuleFieldDefinitionDTO.class,
                                                                              MultiValidationRuleFieldDefinitionDTO.class}))
                  )
          )
  })
  public Collection<FieldDefinition> getTypes() {

    final Collection<FieldDefinition> definitions = this.getServices().getFieldDefinitions();
    if (null == definitions || definitions.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No definitions stored within Typr" );
    }

    return definitions.stream().filter(result -> null != result)
            .map(value -> DTOFactory.getFieldDefinitionDTO(value))
            .collect(Collectors.toList());
  }

  /**
   * Retrieves a Field Definition based on the full length name.
   * @param name The name of the field definition we want to retrieve from Typr.
   * @return A valid Field definition object if one is found within the database.
   */
  @GetMapping("/type/name/{name}")
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Successfully retrieved data from the database",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(description = "Validation for the field definition.",
                                  oneOf={ SingleValidationRuleFieldDefinitionDTO.class,
                                          MultiValidationRuleFieldDefinitionDTO.class})
                  )
          )
  })
  public FieldDefinition getFieldDefinitionByName(@PathVariable(name="name", required = true) final String name) {
    final FieldDefinition result = this.getServices().getFieldDefinitionByName(name);

    if (null == result) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No type definition found for:" + name);
    }

    return DTOFactory.getFieldDefinitionDTO(result);
  }

  /**
   * Used to insert new Field Definitions into the Typr system.
   * @param definition The definition to insert into the system.
   * @return The stored version of the field definition.
   */
  @PutMapping("/type")
  @ApiResponses(value = {
          @ApiResponse(
                  responseCode = "200",
                  description = "Successfully retrieved data from the database",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(description = "Validation for the field definition.",
                                  oneOf={ SingleValidationRuleFieldDefinitionDTO.class,
                                          MultiValidationRuleFieldDefinitionDTO.class})
                  )
          ),
          @ApiResponse(responseCode = "500", description = "Unable to Save Definition" )
  })
  public FieldDefinition putType(@Valid @RequestBody(required = true)
                                 @Schema(oneOf={ SingleValidationRuleFieldDefinitionDTO.class,
                                 MultiValidationRuleFieldDefinitionDTO.class}) final FieldDefinition definition) {
    final FieldDefinition result = this.getServices().saveFieldDefintion(definition);

    if (null == result) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to Save Definition");
    }

    return DTOFactory.getFieldDefinitionDTO(result);
  }
}
