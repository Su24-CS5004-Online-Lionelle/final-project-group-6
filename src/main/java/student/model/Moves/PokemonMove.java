package student.model.Moves;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.j2objc.annotations.J2ObjCIncompatible;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonMove {
    @JsonProperty("move")
    private Move move;

    // Constructors, getters, and setters
    public PokemonMove() {}

    public PokemonMove(Move move) {
        this.move = move;
    }
}
