package student.model.PokemonTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonType {
    @JsonProperty("type")
    private Type type;

    // Constructors, getters, and setters
    public PokemonType() {}

    public PokemonType(Type type) {
        this.type = type;
    }
}
