package uk.pallas.typing.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.pallas.freeman.common.structured.definitions.FieldDefinition;
import uk.pallas.typing.services.FieldDefinitionServices;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class FieldDefinitionsController {

  @Autowired
  private FieldDefinitionServices services;

  @GetMapping("/types")
  public List<FieldDefinition> getTypes() {
    return this.services.getFieldDefinitions();
  }
}
