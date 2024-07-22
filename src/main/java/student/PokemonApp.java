package student;

import student.model.PokemonModel;

import java.net.UnknownHostException;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import student.model.PokeRecord;

public class PokemonApp {
    public static void main (String[] args) {
        PokemonModel model = PokemonModel.getInstance();
        try {
            final int maxRetries = 5; // Maximum number of retries
            final long retryDelay = 5000; // Delay between retries in milliseconds (5 seconds)
        for (int i = 1; i < 2; i++) {
            int retryCount = 0;
            while (retryCount < maxRetries) {
                try {
                    PokeRecord record = model.getRecord(String.format("%d", i));
                    System.out.println(record.types());
                    model.saveRecord(record);
                    break; // Break out of the retry loop on successful execution
                } catch (MismatchedInputException e) {
                    System.err.println("No content to map for ID: " + i + ". Skipping...");
                    e.printStackTrace();
                    break; // No retries needed for this exception, move to the next iteration
                } catch (UnknownHostException e) {
                    System.err.println("Network issue encountered. Retrying in 5 seconds...");
                    retryCount++;
                    try {
                        Thread.sleep(retryDelay); // Wait for 5 seconds before retrying
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt(); // Restore the interrupted status
                        System.err.println("Thread interrupted during retry delay.");
                        break;
                    }
                } catch (Exception e) {
                    System.err.println("Error processing ID: " + i + ". " + e.getMessage());
                    break; // Break on any other exception
                }
            }
            if (retryCount == maxRetries) {
                System.err.println("Max retries reached for ID: " + i + ". Moving to next.");
            }
        }


        // } catch (UnknownHostException e) {
        //     System.err.println("Not found.");
        // } catch (Exception e) {
        //     System.err.println("Error.");
        //     e.printStackTrace();
        // }

    } finally {
        System.err.println();
    }
}
}
