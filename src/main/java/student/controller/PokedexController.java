package student.controller;

import student.model.PokemonModel;
import student.model.PokeRecord;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The PokedexController class provides methods to filter and sort Pokémon data.
 */
public class PokedexController {
    private PokemonModel model;

    /**
     * Constructs a PokedexController with the given PokemonModel.
     *
     * @param model The model containing Pokémon data.
     */
    public PokedexController(){
        this.model = PokemonModel.getInstance();
    }

    /**
     * Returns a list of all Pokémon.
     *
     * @return A list of all Pokémon.
     */
    public List<PokeRecord> getAllPokemon(){
        try {
            return model.getAllPokemon();
        } catch (IOException e) {
            // Handle the IOException here
            e.printStackTrace();
            return new ArrayList<>(); // Or any other appropriate error handling
        }
    }

    /**
     * Filters Pokémon by ID.
     *
     * @param id The ID to filter by.
     * @return A list of Pokémon with the given ID.
     * @throws IOException 
     */
    public List<PokeRecord> filterByID(int id) throws IOException {
        return model.getAllPokemon().stream()
            .filter(pokemon -> pokemon.id() == id)
            .collect(Collectors.toList());
    }

    /**
     * Filters Pokémon by name.
     *
     * @param name The name to filter by.
     * @return A list of Pokémon with the given name.
     * @throws IOException 
     */
    public List<PokeRecord> filterByName(String name) throws IOException {
        return model.getAllPokemon().stream()
            .filter(pokemon -> pokemon.name().equalsIgnoreCase(name))
            .collect(Collectors.toList());
    }

    /**
     * Filters Pokémon by weight.
     *
     * @param minWeight The minimum weight to filter by.
     * @param maxWeight The maximum weight to filter by.
     * @return A list of Pokémon with weights between minWeight and maxWeight.
     * @throws IOException 
     */
    public List<PokeRecord> filterByWeight(double minWeight, double maxWeight) throws IOException {
        return model.getAllPokemon().stream()
                .filter(pokemon -> pokemon.weight() >= minWeight && pokemon.weight() <= maxWeight)
                .collect(Collectors.toList());
    }

    /**
     * Filters Pokémon by height.
     *
     * @param minHeight The minimum height to filter by.
     * @param maxHeight The maximum height to filter by.
     * @return A list of Pokémon with heights between minHeight and maxHeight.
     * @throws IOException 
     */
    public List<PokeRecord> filterByHeight(double minHeight, double maxHeight) throws IOException {
        return model.getAllPokemon().stream()
                .filter(pokemon -> pokemon.height() >= minHeight && pokemon.height() <= maxHeight)
                .collect(Collectors.toList());
    }

    /**
     * Filters Pokémon by type.
     *
     * @param type The type to filter by.
     * @return A list of Pokémon with the given type.
     * @throws IOException 
     */
    public List<PokeRecord> filterByType(String type) throws IOException {
        return model.getAllPokemon().stream()
            .filter(pokemon -> pokemon.types().contains(type))
            .collect(Collectors.toList());
    }

    /**
     * Sorts Pokémon by name.
     *
     * @param ascending Whether to sort in ascending order.
     * @return A list of Pokémon sorted by name.
     * @throws IOException 
     */
    public List<PokeRecord> sortByName(boolean ascending) throws IOException {
        return model.getAllPokemon().stream()
                .sorted((p1, p2) -> ascending ? p1.name().compareToIgnoreCase(p2.name()) : p2.name().compareToIgnoreCase(p1.name()))
                .collect(Collectors.toList());
    }

    /**
     * Sorts Pokémon by ID.
     *
     * @param ascending Whether to sort in ascending order.
     * @return A list of Pokémon sorted by ID.
     * @throws IOException 
     */
    public List<PokeRecord> sortByID(boolean ascending) throws IOException {
        return model.getAllPokemon().stream()
                .sorted((p1, p2) -> ascending ? Integer.compare(p1.id(), p2.id()) : Integer.compare(p2.id(), p1.id()))
                .collect(Collectors.toList());
    }

    /**
     * Sorts Pokémon by height.
     *
     * @param ascending Whether to sort in ascending order.
     * @return A list of Pokémon sorted by height.
     * @throws IOException 
     */
    public List<PokeRecord> sortByWeight(boolean ascending) throws IOException {
        return model.getAllPokemon().stream()
                .sorted((p1, p2) -> ascending ? Double.compare(p1.weight(), p2.weight()) : Double.compare(p2.weight(), p1.weight()))
                .collect(Collectors.toList());
    }

    /**
     * Sorts Pokémon by height.
     *
     * @param ascending Whether to sort in ascending order.
     * @return A list of Pokémon sorted by height.
     * @throws IOException 
     */
    public List<PokeRecord> sortByType(boolean ascending) throws IOException {
        return model.getAllPokemon().stream()
                .sorted((p1, p2) -> {
                    String type1 = p1.types().isEmpty() ? "" : p1.types().get(0).toString();
                    String type2 = p2.types().isEmpty() ? "" : p2.types().get(0).toString();
                    return ascending ? type1.compareToIgnoreCase(type2) : type2.compareToIgnoreCase(type1);
                })
                .collect(Collectors.toList());
    }
}
