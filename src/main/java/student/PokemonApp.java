package student;

import student.model.PokemonModel;
import java.net.UnknownHostException;
import student.model.PokeRecord;

public class PokemonApp {
    public static void main (String[] args) {
        PokemonModel model = PokemonModel.getInstance();
        try {
            PokeRecord record = model.getRecord("squirtle");
            System.out.println(record);
        } catch (UnknownHostException e) {
            System.err.println("Not found.");
        } catch (Exception e) {
            System.err.println("Error.");
        }
    }
}
