package uk.pallas.systems.categr.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import uk.pallas.systems.categr.rest.entities.v1.CategoryDTO;
import uk.pallas.systems.categr.entities.v1.Category;
import uk.pallas.systems.categr.services.CategoriesDAOService;

/**
 * This Defines an interface for retrieving Field Definitions stored within Typr.
 */
@RestController("Categories API")
@RequestMapping("/categr/v1")
@OpenAPIDefinition(info = @Info(title = "Categories API"))
public class CategoriesController {

  /**
   * The backend service to retrieve.
   */
  @Autowired
  private CategoriesDAOService services;

  public CategoriesDAOService getServices() {
    return services;
  }

  public void setServices(final CategoriesDAOService fieldDefSrv) {
    this.services = fieldDefSrv;
  }

  /**
   * Retrieves all Categories held within Categr.
   *
   * @return A list of categories to be offered to the user
   */
  @GetMapping("/")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved data from the database",
      content = @Content(mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(description = "Categories storied within the system.",
          oneOf = {CategoryDTO.class}))
        )
      ),
    @ApiResponse(
      responseCode = "404",
      description = "No Categories stored within categr"
    )
  })
  public Collection<CategoryDTO> getCategories() {

    final Collection<Category> cats = this.getServices().findAll();
    if (null == cats || cats.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Categories stored within categr");
    }

    return cats.stream().filter(Objects::nonNull)
      .map(CategoryDTO::new)
      .collect(Collectors.toList());
  }

  /**
   * Retrieves the Names of all categories held within Categr.
   *
   * @return A list of categories to be offered to the user
   */
  @GetMapping("/names")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved data from the database",
      content = @Content(mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(description = "Categories storied within the system.",
          oneOf = {CategoryDTO.class}))
      )
      ),
    @ApiResponse(
      responseCode = "404",
      description = "No Categories stored within categr"
      )
  })
  public Collection<String> getCategoryNames() {

    final Collection<Category> cats = this.getServices().findAll();
    if (null == cats || cats.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Categories stored within categr");
    }

    return cats.stream().filter(Objects::nonNull)
      .map(value -> value.getName())
      .collect(Collectors.toList());
  }

  /**
   * Retrieves the Names of all categories held within Categr.
   *
   * @return A list of categories to be offered to the user
   */
  @GetMapping("/names/{name}")
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Successfully retrieved data from the database",
      content = @Content(mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(description = "Categories storied within the system.",
          oneOf = {CategoryDTO.class}))
        )
      ),
    @ApiResponse(
      responseCode = "400",
      description = "The supplied name path variable was blank or missing"
    ),
    @ApiResponse(
      responseCode = "404",
      description = "No Categories stored within categr"
    )
  })
  public Category getCategoryByName(@PathVariable(name = "name") final String name) {

    if (null == name || name.isBlank()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Category Name was supplied.");
    }

    final Category cats = this.getServices().findByName(name);
    if (null == cats) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Categories found with that name");
    }

    // Convert into
    return new CategoryDTO(cats);
  }
}
