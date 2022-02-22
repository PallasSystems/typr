package uk.pallas.typing.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import uk.pallas.freeman.common.structured.definitions.impl.StringFieldDefinitionImpl;

public class FieldDefinitionRegisterTypes {

  /**
   * Function to configure an ObjectMapper so it can convert interfaces into classes.
   * @param mapper an object mapper instance to modify.
   */
  public static void setupMapper(final ObjectMapper mapper) {

    if (null != mapper) {

      final NamedType[] types = new NamedType[]{
          new NamedType(StringFieldDefinitionImpl.class, StringFieldDefinitionImpl.class.getSimpleName())
      };

      //
      mapper.registerSubtypes(types);
      mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


  }

}
