package uk.pallas.typing.entities.v1.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import uk.pallas.typing.entities.v1.DoubleFieldDefinition;
import uk.pallas.typing.entities.v1.LongFieldDefinition;
import uk.pallas.typing.entities.v1.StringFieldDefinition;
import uk.pallas.typing.entities.v1.jackson.DoubleFieldDefinitionDTO;
import uk.pallas.typing.entities.v1.jackson.LongFieldDefinitionDTO;
import uk.pallas.typing.entities.v1.jackson.StringFieldDefinitionDTO;

public class FieldDefinitionRegisterTypes {

  /**
   * Function to configure an ObjectMapper so it can convert interfaces into classes.
   * @param mapper an object mapper instance to modify.
   */
  public static void setupMapper(final ObjectMapper mapper) {

    if (null != mapper) {

      final NamedType[] types = new NamedType[]{
          new NamedType(DoubleFieldDefinitionDTO.class, DoubleFieldDefinition.class.getSimpleName()),
          new NamedType(LongFieldDefinitionDTO.class, LongFieldDefinition.class.getSimpleName()),
          new NamedType(StringFieldDefinitionDTO.class, StringFieldDefinition.class.getSimpleName())
      };

      //
      mapper.registerSubtypes(types);
      mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
  }

}
