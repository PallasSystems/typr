package uk.pallas.typr.entities.v1;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import uk.pallas.typr.entities.v1.jackson.DoubleFieldDefinitionDTO;
import uk.pallas.typr.entities.v1.jackson.LongFieldDefinitionDTO;
import uk.pallas.typr.entities.v1.jackson.StringFieldDefinitionDTO;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes({@Type(value = DoubleFieldDefinitionDTO.class, name = "DoubleFieldDefinition"),
               @Type(value = LongFieldDefinitionDTO.class, name = "LongFieldDefinition"),
               @Type(value = StringFieldDefinitionDTO.class, name = "StringFieldDefinition")})
public interface JacksonFieldDefinition extends FieldDefinition {

  /**
   * Defines the class type so we can associate with it.
   * @return a valid field name for association.
   */
  String getType();
}
