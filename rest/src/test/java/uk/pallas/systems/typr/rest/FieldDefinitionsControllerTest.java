package uk.pallas.systems.typr.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uk.pallas.systems.typr.entities.v1.FieldDefinition;
import uk.pallas.systems.typr.entities.v1.validation.ValidationRule;
import uk.pallas.systems.typr.rest.entities.v1.FieldDefinitionDTO;
import uk.pallas.systems.typr.rest.entities.v1.validation.number.LongValidationRuleDTO;
import uk.pallas.systems.typr.services.CountryService;
import uk.pallas.systems.typr.services.FieldDefinitionService;
import uk.pallas.systems.typr.services.UnitsService;

@ExtendWith(MockitoExtension.class)
class FieldDefinitionsControllerTest {
  @InjectMocks
  FieldDefinitionsController controller;

  @Mock
  FieldDefinitionService fieldDefDAO;

  @Mock
  UnitsService unitService;

  @Mock
  CountryService countryService;

  /**
   * This will generate a test object within a number validation rule, that has a range of 100-10000.
   * @return a valid and completly formed Field Definition.
   */
  private FieldDefinition generateTestObject() {
    final String fieldName = "FieldDefinitionsControllerTest-generateTestObject-name";
    final String acronym = "FDDT-gto";
    final String description = "FieldDefinitionsControllerTest-generateTestObject-description";

    final long max = 10000;
    final long min = 100;
    final String ruleDescription = "LongValidationRuleDomain-generateTestObject";
    final String unitName = "Degree Angle";
    final ValidationRule rule = new LongValidationRuleDTO(max,min,ruleDescription, unitName);
    final Set<ValidationRule> rules = new HashSet<>();
    rules.add(rule);

    return new FieldDefinitionDTO(acronym, new HashSet<>(), description, fieldName, rules);
  }


  /**
   * Creates a Happy Path for testing the rest controller is properly handling expected results.
   */
  @Test
  void testGetTypes() {
    // Create the response to mock in return
    final Collection<FieldDefinition> expected = new ArrayList<>();
    expected.add(this.generateTestObject());
    // set up the DAO to return our mock data.
    Mockito.when(fieldDefDAO.getFieldDefinitions()).thenReturn(expected);
    Mockito.when(unitService.isValid(Mockito.any(FieldDefinition.class))).thenReturn(true);
    Mockito.when(countryService.isValidISO31661Alpha3(Mockito.any(FieldDefinition.class))).thenReturn(true);

    final MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    final Collection<FieldDefinition> actual = controller.getTypes();
    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void testGetFieldDefinitionByNames() {
    // Create the response to mock in return
    final Collection<FieldDefinition> mockResults = new ArrayList<>();
    final FieldDefinition mockResult = this.generateTestObject();
    mockResults.add(mockResult);
    // set up the DAO to return our mock data.
    Mockito.when(fieldDefDAO.getFieldDefinitions()).thenReturn(mockResults);
    Mockito.when(unitService.isValid(Mockito.any(FieldDefinition.class))).thenReturn(true);
    Mockito.when(countryService.isValidISO31661Alpha3(Mockito.any(FieldDefinition.class))).thenReturn(true);

    final MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    final Collection<String> actual = controller.getFieldDefinitionNames();
    Assertions.assertNotNull(actual);

    // Extract the name in the way we expect it to be held
    final Collection<String> expected = new ArrayList<>();
    expected.add(mockResult.getAcronym() + " - " + mockResult.getName());

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void testGetFieldDefinitionByNamesWithInvalid() {
    // Create the response to mock in return
    final Collection<FieldDefinition> mockResults = new ArrayList<>();
    final FieldDefinition mockResult = new FieldDefinitionDTO();
    mockResults.add(mockResult);
    // set up the DAO to return our mock data.
    Mockito.when(fieldDefDAO.getFieldDefinitions()).thenReturn(mockResults);
    Mockito.when(unitService.isValid(Mockito.any(FieldDefinition.class))).thenReturn(true);
    Mockito.when(countryService.isValidISO31661Alpha3(Mockito.any(FieldDefinition.class))).thenReturn(true);

    final MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    final Collection<String> actual = controller.getFieldDefinitionNames();
    Assertions.assertNotNull(actual);
    Assertions.assertTrue(actual.isEmpty());
  }
}
