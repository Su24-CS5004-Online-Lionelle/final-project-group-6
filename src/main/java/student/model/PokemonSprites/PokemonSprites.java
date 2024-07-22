package student.model.PokemonSprites;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonSprites {
    @JsonProperty("sprites")
    Sprites sprites;

    // Constructor
    @JsonCreator
    public PokemonSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}
