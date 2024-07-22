package student;

import student.model.PokemonModel;

import java.io.IOException;
import java.net.UnknownHostException;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import student.model.PokeRecord;

public class PokemonApp {
    public static void main (String[] args) {
        PokemonModel model = PokemonModel.getInstance();
    //     for (int i = 111; i <= 151; i++) {
    //         try {
    //             PokeRecord record = model.getRecord(String.format("%d", i));
    //             System.out.println(record.types());
    //             model.saveRecord(record);
    //         } catch (UnknownHostException e) {
    //             System.err.println("Not found.");
    //         } catch (Exception e) {
    //             System.err.println("Error.");
    //             e.printStackTrace();
    //         }
    // }
    try {
        System.out.println(model.getPokemonByID(1).name());
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
