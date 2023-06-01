package uk.pallas.systems.typr.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import uk.pallas.systems.typr.entities.v1.Category;
import uk.pallas.systems.typr.rest.entities.v1.CategoryDTO;
import uk.pallas.systems.typr.rest.entities.v1.MultiValidationRuleFieldDefinitionDTO;
import uk.pallas.systems.typr.rest.entities.v1.SingleValidationRuleFieldDefinitionDTO;
import uk.pallas.systems.typr.services.FieldDefinitionService;

/**
 * This Defines an interface for retrieving Field Definitions stored within Typr.
 */
@RestController("Categories API")
@RequestMapping("api/categories/v1")
@OpenAPIDefinition(info = @Info(title = "Categories API"))
public class CategoriesController {

  /**
   * The backend service to retrieve.
   */
  @Autowired
  private FieldDefinitionService services;

  public FieldDefinitionService getServices() {
    return services;
  }

  public void setServices(FieldDefinitionService services) {
    this.services = services;
  }

  /**
   * Retrieves all Field Definitions held within Typr.
   *
   * @return A collection of Long, Double, String, Enumerate, etc... field definitions.
   */
  @GetMapping("/categories")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved data from the database",
      content = @Content(mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(description = "Validation for the field definition.",
          oneOf = {SingleValidationRuleFieldDefinitionDTO.class,
            MultiValidationRuleFieldDefinitionDTO.class}))
      )
    )
  })
  public Collection<CategoryDTO> getCategories() {

    final Collection<Category> cats = this.getServices().getCategories();
    if (null == cats || cats.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Categories stored within Typr");
    }

    return cats.stream().filter(result -> null != result)
      .map(value -> new CategoryDTO(value))
      .collect(Collectors.toList());
  }
}
