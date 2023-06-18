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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import uk.pallas.systems.typr.entities.v1.Category;
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

  /** Static Logger for the class. */
  private static final Log LOGGER = LogFactory.getLog(FieldDefinitionsController.class);

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

  public void setServices(final FieldDefinitionService service) {
    this.services = service;
  }

  public UnitsService getUnitService() {
    return unitService;
  }

  public void setUnitService(final UnitsService service) {
    this.unitService = service;
  }

  public CountryService getCountryService() {
    return this.countryService;
  }

  public void setCountryService(final CountryService service) {
    this.countryService = service;
  }

  private Collection<FieldDefinition> convertToDTO(final Collection<FieldDefinition> definitions) {
    return definitions.stream().filter(Objects::nonNull)
      .map(FieldDefinitionDTO::new)
      .filter(value -> this.getUnitService().isValid(value))
      .filter(value -> this.getCountryService().isValidISO31661Alpha3(value))
      .collect(Collectors.toList());
  }

  /**
   * Retrieves all Field Definitions held within Typr.
   *
   * @return A collection of Long, Double, String, Enumerate, etc... field definitions.
   */
  @GetMapping("/")
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
    // Retrieve all Type Definitions
    final Collection<FieldDefinition> definitions = this.getServices().getFieldDefinitions();
    if (null == definitions || definitions.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No definitions stored within Typr");
    }

    return this.convertToDTO(definitions);
  }

  /**
   * Retrieves the Acronym and Names from the database and returns them as a list to support various components.
   *
   * @return A String array holding "Acronym - Name" or "Name" for each field definition within the library
   */
  @GetMapping("/name")
  @Operation(summary = "Retrieves the Acronym and Names from the database and returns them as a list to "
    + "support various components.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved data from the database",
      content = @Content(mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(description = "All Unique Identifiers within ",
          oneOf = { String.class }))
      )
    )
  })
  public Collection<String> getFieldDefinitionNames() {
    // Retrieve all Type Definitions
    final Collection<FieldDefinition> definitions = this.getTypes();

    final List<String> results = new ArrayList<>(definitions.size());
    // We want to extract the Acronym - Name for each type
    for (final FieldDefinition definition : definitions) {
      // Validate the field definition
      if (this.getUnitService().isValid(definition)) {
        if (this.getCountryService().isValidISO31661Alpha3(definition)) {
          final String name = this.getDefinitionName(definition.getAcronym(), definition.getName());

          if (null == name || name.isBlank()) {
            if (LOGGER.isWarnEnabled()) {
              LOGGER.warn("getDefinitionName - No Name or Arconym for field: " + definition);
            }
          } else {
            results.add(name);
          }
        }
      }
    }

    Collections.sort(results);

    return results;
  }

  /**
   * Attempts to extract a human readble name for a supplied field definition.
   * @param acronym the acronym returned by a field definition
   * @param name the name returned by a field definition
   * @return an empty string if acronym and name are blank.
   */
  private String getDefinitionName(final String acronym, final String name) {
    final String result;

    if (null == acronym || acronym.isBlank()) {
      if (null == name || name.isBlank()) {
        result = "";
      } else {
        result = name;
      }
    } else if (null == name || name.isBlank()) {
      result = acronym;
    } else {
      result = acronym + " - " + name;
    }

    return result;
  }


  /**
   * Retrieves a Field Definition based on the full length name.
   *
   * @param name The name of the field definition we want to retrieve from Typr.
   * @return A valid Field definition object if one is found within the database.
   */
  @GetMapping("/name/{name}")
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
   * Retrieves the Unique categories associated with the field definitions.
   *
   * @return A String array holding "Acronym - Name" or "Name" for each field definition within the library
   */
  @GetMapping("/category")
  @Operation(summary = "Retrieves the Unique categories associated with the field definitions.")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved data from the database",
      content = @Content(mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(description = "All Unique Identifiers within ",
          oneOf = { String.class }))
      )
    )
  })
  public Collection<String> getFieldDefinitionCategories() {
    // Retrieve all Type Definitions
    final Collection<FieldDefinition> definitions = this.getServices().getFieldDefinitions();
    if (null == definitions || definitions.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No definitions stored within Typr");
    }

    // Use a set to ensure unique results
    final Set<String> results = new HashSet<>(definitions.size());
    // Iterate over all results and pull out the category name
    for (final FieldDefinition definition : definitions) {
      if (null != definition.getCategories()) {
        for (final Category category : definition.getCategories()) {
          results.add(category.getName());
        }
      }
    }

    return results;
  }


  /**
   * Retrieves a Field Definition based on a specific category it is associated with.
   *
   * @param name The name of the Category we want associated records for.
   * @return A valid Field definition object if one is found within the database.
   */
  @GetMapping("/category/{name}")
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
  public Collection<FieldDefinition> getFieldDefinitionByCategoryName(@PathVariable(name = "name") final String name) {
    final Collection<FieldDefinition> definitions = this.getServices().getFieldDefinitionsByCategory(name);
    if (null == definitions || definitions.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No definitions associated with " + name);
    }

    return this.convertToDTO(definitions);
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
