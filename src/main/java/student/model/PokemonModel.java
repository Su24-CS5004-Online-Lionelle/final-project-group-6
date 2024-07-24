package student.model;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;
import java.util.Collection;
import student.GUIUtil;

import org.apache.commons.lang3.ObjectUtils.Null;

// import student.model.IModel;
// import student.model.formatters.DataFormatter;
// import student.model.formatters.Formats;
// import student.model.net.NetUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.IOException;

public class PokemonModel {

     /** An instance of PokemonModel for Singleton pattern. */
     private static PokemonModel instance;
     /** The path to the JSON database file. */
     private static final String DATABASE_FILE = "src/main/resources/database/pokerecords.json";
     /** The path to the JSON database file for the pokemon team. */
     private static final String DATABASE_TEAM_FILE = "src/main/resources/database/teamrecords.json";
     /** ObjectMapper instance for handling serialization and deserialization of PokeRecord objects. */
     private static ObjectMapper mapper = new ObjectMapper();


     /**
      * Private constructor to prevent instantiation from outside the class.
      */
     private PokemonModel() {}

     /**
      * Provides access to the singleton instance of the {@code PokemonModel} class.
      * If the instance does not exist, it is created.
      *
      * @return The singleton instance of {@code PokemonModel}.
      */
     public static PokemonModel getInstance() {
         if (instance == null) {
             instance = new PokemonModel();
         }
         return instance;
     }

    /**
     * Gets a single record by name.
     *
     * If the record does not exist, gets the information based off the IP address, builds the
     * record, adds (and saves) it to hostrecords.xml, then returns the new record.
     *
     * @param name the hostname to look up
     * @param database the database
     * @return the record
     * @see NetUtil#lookUpIp(String)
     * @see NetUtil#getIpDetails(String)
     */
    public PokeRecord getRecordFromAPI(String name) throws Exception, UnknownHostException {
        PokeRecord fetchedRecord;

            InputStream ipDetailStream = NetUtils.getIpDetails(name);
            String ipDetailStr = new String(ipDetailStream.readAllBytes()).replace("\n", "");
            // Change the IP details from input stream to a PokeRecord object using ObjectMapper。
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            TypeReference<PokeRecord> typeRef = new TypeReference<PokeRecord>() { };
            fetchedRecord = mapper.readValue(ipDetailStr, typeRef);
            System.out.println(fetchedRecord);
            return fetchedRecord;
    }

    /**
     * Saves the new pokemon as a record, to database of all pokemon.
     *
     * @param newRecord new record to save
     * @throws Exception
     */
    public void saveRecord(PokeRecord newRecord) throws Exception {
        // Load existing records
        File databaseFile = new File(DATABASE_FILE);
        List<PokeRecord> records;
        if (databaseFile.exists() && databaseFile.length() > 0) {
            records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
        } else {
            records = new ArrayList<>();
        }

        // Add the new record
        records.add(newRecord);

        // Save the updated records back to the file
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try (FileOutputStream fos = new FileOutputStream(DATABASE_FILE)) {
            mapper.writeValue(fos, records);
        }
    }

    /**
     * Retrieves a {@code PokeRecord} by its Pokémon ID.
     *
     * @param id The ID of the Pokémon.
     * @return The {@code PokeRecord} associated with the given ID.
     * @throws IOException If there is an error during the retrieval operation.
     */
    public PokeRecord getPokemonByID(int id) throws IOException {
        File databaseFile = new File(DATABASE_FILE);
        List<PokeRecord> records;
        PokeRecord result = null;

        // Read the existing records from the file
        if (databaseFile.exists() && databaseFile.length() > 0) {
            String content = new String(Files.readAllBytes(databaseFile.toPath()), StandardCharsets.UTF_8);
            if (!content.trim().isEmpty() && content.trim().startsWith("[") && content.trim().endsWith("]")) {
                try {
                    records = mapper.readValue(content, new TypeReference<List<PokeRecord>>() {});
                    for (PokeRecord record : records) {
                        if (record.id() == id) {
                            result = record;
                        }
                    }
            } catch (IOException e) {
                System.out.println("Pokemon not found in database.");
            }
        }
    }
    return result;
    }

    /**
     * Retrieves a {@code PokeRecord} by the Pokémon's name using a case-insensitive search.
     *
     * @param pokemonName The name of the Pokémon.
     * @return The {@code PokeRecord} associated with the given name.
     * @throws IOException If the Pokémon is not found or there is an error during the retrieval operation.
     */
    public PokeRecord getPokemonByName(String pokemonName) throws IOException {
        File databaseFile = new File(DATABASE_FILE);
        List<PokeRecord> records;
        PokeRecord result = null;

        // Read the existing records from the file
        if (databaseFile.exists() && databaseFile.length() > 0) {
            try {
                records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
                for (PokeRecord record : records) {
                    if (record.name().toLowerCase().equals(pokemonName.toLowerCase())) {
                        result = record;
                    }
                }
            } catch (IOException e) {
                System.out.println("Pokemon not found in database.");
            }
        }
        if (result == null) {
            throw new IOException("Pokemon not found.");
        }
        return result;
    }


     /**
     * Retrieves all Pokémon records from the database as a list.
     *
     * @return A list of {@code PokeRecord} objects representing all Pokémon in the database.
     * @throws IOException If there is an error during the retrieval operation.
     */
    public List<PokeRecord> getAllPokemon() throws IOException{
        File databaseFile = new File(DATABASE_FILE);
        List<PokeRecord> records;

        // Read the existing records from the file
        if (databaseFile.exists() && databaseFile.length() > 0) {
            try {
                records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
                return records;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error returning the pokemon list.");
            }
        }
        // Return null or throw an exception if the record is not found
        throw new IOException();
    }

    /**
     * Checks if a given Pokémon is in the team by comparing it against records in the database.
     *
     * @param pokemon The {@link PokeRecord} object representing the Pokémon to check.
     * @return {@code true} if the Pokémon is found in the team, {@code false} otherwise.
     */
    public boolean isPokemonInTeam(PokeRecord pokemon) {
        File databaseFile = new File(DATABASE_TEAM_FILE);
        List<PokeRecord> records = new ArrayList<>();
        boolean inTeam = false;

        if (databaseFile.exists()) {
            try {
                records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
            } catch (IOException e) {
                System.out.println("Failed to read the database.");
            }
        } else {
            System.out.println("Database not found.");
        }

        for (PokeRecord record : records) {
            if (record.name().equals(pokemon.name())) {
                inTeam = true;
            }
        }
        return inTeam;
    }

    /**
     * Saves the new pokemon to the team database.
     *
     * @param newRecord new record to save
     * @throws Exception
     */
    public void addPokemonToTeam(PokeRecord newRecord) {
        // Load existing records
        File databaseFile = new File(DATABASE_TEAM_FILE);
        List<PokeRecord> records = new ArrayList<>();
        if (databaseFile.exists()) {
            try {
                records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
            } catch (IOException e) {
                System.out.println("Failed to add");
                e.printStackTrace();
            }

        if (!isPokemonInTeam(newRecord)) {
            records.add(newRecord);
        }

        // Save the updated records back to the file
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try (FileOutputStream fos = new FileOutputStream(DATABASE_TEAM_FILE)) {
            mapper.writeValue(fos, records);
        } catch (Exception  e) {
            System.out.println("Database not found.");
        }
    }
    }

    /**
     * Retrieves all Pokémon in the team from the database.
     *
     * This method reads the Pokémon records from a database file and returns a list of {@link PokeRecord} objects
     * representing each Pokémon in the team. If the database file does not exist or an error occurs during reading,
     * this method will print the error stack trace and a message, then potentially return null or throw an IOException,
     * depending on how the method's error handling is implemented.
     *
     * @return A list of {@link PokeRecord} objects representing all Pokémon in the team, may be an empty
     * list if the team is empty, or null if an error occurs
     * and the method is designed to return null in such cases.
     * @throws IOException If an error occurs while reading the database file.
     */
    public List<PokeRecord> getAllPokemonInTeam() throws IOException {
        File databaseFile = new File(DATABASE_TEAM_FILE);
        List<PokeRecord> records;

        // Read the existing records from the file
        if (databaseFile.exists()) {
            try {
                records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
                return records;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error returning the pokemon list.");
            }
        }
        // Return null or throw an exception if the record is not found
        throw new IOException();
    }

    /**
     * Removes a specified Pokémon from the team.
     *
     * This method first checks if the specified Pokémon is in the team. If it is, the method removes the Pokémon
     * from the list of records and then attempts to save the updated list back to the database file. If the database
     * file does not exist or an error occurs during the process, an error message is printed and the exception stack
     * trace is printed to the console.
     *
     * @param pokemon The {@link PokeRecord} object representing the Pokémon to be removed from the team.
     */
    public void removePokemonFromTeam(PokeRecord pokemon) {
        File databaseFile = new File(DATABASE_TEAM_FILE);
        List<PokeRecord> records = new ArrayList<>();
        if (databaseFile.exists()) {
            try {
                records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
            } catch (IOException e) {
                System.out.println("Failed to add");
                e.printStackTrace();
            }

        if (isPokemonInTeam(pokemon)) {
            PokeRecord pokemonToRemove = records.stream()
            .filter(item -> item.name().equals(pokemon.name())).toList().get(0);
            records.remove(pokemonToRemove);
        }

        // Save the updated records back to the file
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try (FileOutputStream fos = new FileOutputStream(DATABASE_TEAM_FILE)) {
            mapper.writeValue(fos, records);
        } catch (Exception  e) {
            System.out.println("Database not found.");
        }
    }
    }

    /**
     * Retrieves a Pokémon from the team based on its ID.
     *
     * This method searches through the team's Pokémon records for a Pokémon with the specified ID. If a Pokémon
     * with the given ID is found, it is returned. If no Pokémon with the specified ID is found or if an error occurs
     * during the process, an IOException is thrown.
     *
     * @param id The unique identifier for the Pokémon to be retrieved.
     * @return The {@link PokeRecord} object representing the Pokémon with the specified ID.
     * @throws IOException If the Pokémon is not found in the team or if an error occurs during the process.
     */
    public PokeRecord getPokemonFromTeamByID(int id) throws IOException {
        try {
            List<PokeRecord> records = getAllPokemonInTeam();
            for (PokeRecord record : records) {
                if (record.id() == id) {
                    return record;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IOException("Pokemon not in team.");
    }

    /**
     * Retrieves a Pokémon from the team based on its name.
     *
     * This method searches through the team's Pokémon records for a Pokémon with the specified name. If a Pokémon
     * with the given name is found, it is returned. If no Pokémon with the specified name is found or if an error occurs
     * during the process, an IOException is thrown.
     *
     * @param name The name of the Pokémon to be retrieved.
     * @return The {@link PokeRecord} object representing the Pokémon with the specified name.
     * @throws IOException If the Pokémon is not found in the team or if an error occurs during the process.
     */
    public PokeRecord getPokemonFromTeamByName(String name) throws IOException {
        try {
            List<PokeRecord> records = getAllPokemonInTeam();
            for (PokeRecord record : records) {
                if (record.name().toLowerCase().equals(name.toLowerCase())) {
                    return record;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IOException("Pokemon not in team.");
    }

    /**
     * Write the data as JSON.
     * 
     * @param list the list to write
     * @param outputFile the output file to write to
     */
    public void writeJsonData(List<PokeRecord> list, String outputFile) {
        try {
            Collection<PokeRecord> records = list;
            File output = new File(outputFile);
            FileOutputStream out = new FileOutputStream(output);
            // Serialize a list of domain records to json.
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(out, records);
            GUIUtil.showMessage("Your team is sucessfully saved out.", "Info");
        } catch (IOException e) {
            GUIUtil.showMessage("IOException: " + e.getMessage(), "Error");
        } catch (Exception e) {
            GUIUtil.showMessage("Exception: " + e.getMessage(), "Error");
        }
    }
}
