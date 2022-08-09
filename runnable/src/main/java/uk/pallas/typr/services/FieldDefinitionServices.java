package uk.pallas.typr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.typr.domain.DoubleFieldDefinitionRepository;
import uk.pallas.typr.domain.LongFieldDefinitionRepository;
import uk.pallas.typr.domain.StringFieldDefinitionRepository;
import uk.pallas.typr.entities.v1.FieldDefinition;
import uk.pallas.typr.entities.v1.DoubleFieldDefinition;
import uk.pallas.typr.entities.v1.LongFieldDefinition;
import uk.pallas.typr.entities.v1.NumberFieldDefinition;
import uk.pallas.typr.entities.v1.StringFieldDefinition;
import uk.pallas.typr.entities.v1.domain.DoubleFieldDefinitionDomain;
import uk.pallas.typr.entities.v1.domain.LongFieldDefinitionDomain;
import uk.pallas.typr.entities.v1.domain.StringFieldDefinitionDomain;
import uk.pallas.typr.entities.v1.jackson.StringFieldDefinitionDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldDefinitionServices {

  @Autowired
  private DoubleFieldDefinitionRepository doubleDAO;

  @Autowired
  private LongFieldDefinitionRepository longDAO;

  @Autowired
  private StringFieldDefinitionRepository stringDAO;

  public List<FieldDefinition> getFieldDefinitions() {
    final List<FieldDefinition> results = new ArrayList<>();

    results.addAll(this.stringDAO.findAll());

    final StringFieldDefinition example = new StringFieldDefinitionDTO();
    example.setName("UK Post Code");
    example.setDescription("Postal codes used in the United Kingdom, British Overseas Territories and Crown dependencies are known as postcodes (originally, postal codes).[1] They are alphanumeric and were adopted nationally between 11 October 1959 and 1974, having been devised by the General Post Office (Royal Mail).[2] A full postcode is known as a \"postcode unit\" and designates an area with several addresses or a single major delivery point.");
    example.setRegex("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$");

    results.add(example);

    return results;
  }

  public FieldDefinition saveFieldDefintion(final FieldDefinition definition) {

    final FieldDefinition result;

    if (definition instanceof StringFieldDefinition) {
      final StringFieldDefinitionDomain domain = new StringFieldDefinitionDomain((StringFieldDefinition)definition);
      result = this.stringDAO.save(domain);
    } else if (definition instanceof DoubleFieldDefinition) {
      final DoubleFieldDefinitionDomain domain = new DoubleFieldDefinitionDomain((DoubleFieldDefinition)definition);
      result = this.doubleDAO.save(domain);
    } else if (definition instanceof LongFieldDefinition) {
      final LongFieldDefinitionDomain domain = new LongFieldDefinitionDomain((LongFieldDefinition)definition);
      result = this.longDAO.save(domain);
    } else if (definition instanceof NumberFieldDefinition) {
      final DoubleFieldDefinitionDomain domain = new DoubleFieldDefinitionDomain((NumberFieldDefinition)definition);
      result = this.doubleDAO.save(domain);
    } else {
      result = null;
    }

    return result;
  }
}
