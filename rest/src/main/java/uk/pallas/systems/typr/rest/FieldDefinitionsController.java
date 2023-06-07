package uk.pallas.systems.typr.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.rest.entities.v1.FieldDefinitionDTO;
import uk.pallas.systems.typr.services.CountryService;
import uk.pallas.systems.typr.services.FieldDefinitionService;
import uk.pallas.systems.typr.services.UnitsService;

/**
 * This Defines an interface for retrieving Field Definitions stored within Typr.
 */
@RestController("Typr Field API")
@RequestMapping("api/type/v1")
@OpenAPIDefinition(info = @Info(title = "Typr Field API"))
public class FieldDefinitionsController {

  /**
   * The backend service to retrieve.
   */
  @Autowired
  private FieldDefinitionService services;

  @Autowired
  private UnitsService unitService;

  @Autowired
  private CountryService countryService;

  public FieldDefinitionService getServices() {
    return services;
  }

  public void setServices(final FieldDefinitionService services) {
    this.services = services;
  }

  public UnitsService getUnitService() {
    return unitService;
  }

  public void setUnitService(UnitsService unitService) {
    this.unitService = unitService;
  }

  public CountryService getCountryService() {
    return countryService;
  }

  public void setCountryService(CountryService countryService) {
    this.countryService = countryService;
  }

  /**
   * Retrieves all Field Definitions held within Typr.
   *
   * @return A collection of Long, Double, String, Enumerate, etc... field definitions.
   */
  @GetMapping("/types")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved data from the database",
      content = @Content(mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(description = "Validation for the field definition.",
          oneOf = { FieldDefinitionDTO.class }))
      )
    )
  })
  public Collection<FieldDefinition> getTypes() {

    final Collection<FieldDefinition> definitions = this.getServices().getFieldDefinitions();
    if (null == definitions || definitions.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No definitions stored within Typr");
    }

    return definitions.stream().filter(Objects::nonNull)
      .map(FieldDefinitionDTO::new)
      .filter(value -> this.getUnitService().isValid(value))
      .filter(value -> this.getCountryService().isValidISO31661Alpha3(value))
      .collect(Collectors.toList());
  }

  /**
   * Retrieves a Field Definition based on the full length name.
   *
   * @param name The name of the field definition we want to retrieve from Typr.
   * @return A valid Field definition object if one is found within the database.
   */
  @GetMapping("/type/name/{name}")
  @Operation(summary = "Retrieves a Field Definition based on the full length name.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved data from the database",
      content = @Content(mediaType = "application/json",
        schema = @Schema(description = "Validation for the field definition.",
          oneOf = { FieldDefinitionDTO.class })
      )
    )
  })
  public FieldDefinition getFieldDefinitionByName(@PathVariable(name = "name") final String name) {
    final FieldDefinition result = this.getServices().getFieldDefinitionByName(name);

    if (null == result) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No type definition found for:" + name);
    } else if (!this.getUnitService().isValid(result)) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object had incorrect unit type");
    } else if (!this.getCountryService().isValidISO31661Alpha3(result)) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Object had incorrect Country code for rule");
    }

    return new FieldDefinitionDTO(result);
  }

  /**
   * Used to insert new Field Definitions into the Typr system.
   *
   * @param definition The definition to insert into the system.
   * @return The stored version of the field definition.
   */
  @PutMapping("/type")
  @Operation(summary = "Used to insert new Field Definitions into the Typr system.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved data from the database",
      content = @Content(mediaType = "application/json",
        schema = @Schema(description = "Validation for the field definition.",
          oneOf = { FieldDefinitionDTO.class })
      )
    ),
    @ApiResponse(responseCode = "500", description = "Unable to Save Definition")
  })
  public FieldDefinition putType(@Valid @RequestBody
                                 @Schema(oneOf = {FieldDefinitionDTO.class}) final FieldDefinition definition) {

    if (!this.getUnitService().isValid(definition)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Object had incorrect unit type");
    }

    if (!this.getCountryService().isValidISO31661Alpha3(definition)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Object had incorrect Country Code");
    }

    final FieldDefinition result = this.getServices().saveFieldDefintion(definition);

    if (null == result) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to Save Definition");
    }

    return new FieldDefinitionDTO(result);
  }
}
