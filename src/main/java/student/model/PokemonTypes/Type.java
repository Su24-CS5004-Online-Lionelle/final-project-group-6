package student.model.PokemonTypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {

    @JsonProperty("name")
    private String typeName;

    // Constructor
    @JsonCreator
    public Type(@JsonProperty("name") String typeName) {
        this.typeName = typeName;
    }
}
