package student.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import student.model.Moves.PokemonMove;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Primary record to pass around between objects. Is immutable and uses Jackson annotations for
 * serialization.
 *
 * @param name the name of the pokemon
 * @param id the id of the pokemon
 * @param order order for sorting.
 * @param height the height of the pokemon
 * @param weight the weight of the pokemon

 * @param moves A list of moves along with learn methods and level details pertaining to specific version groups
 *
* @return the record
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name", "id", "species", "height", "weight", "moves"})
public record PokeRecord(
    @JsonProperty("name") String name,
    @JsonProperty("id") int id,
    @JsonProperty("order") int order,
    @JsonProperty("height") int height,
    @JsonProperty("weight") int weight,
    @JsonProperty("moves") List<PokemonMove> moves) {
}
