package student.model;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;
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
import java.io.IOException;

public class PokemonModel {

    /** An instance of PokemonModel. */
    private static PokemonModel instance;
    /** Database string. */
    private static final String DATABASE_FILE = "src/main/resources/database/pokerecords.json";

    /**
     * Constructor of DomainNameModel class.
     *
     * @return an instance of DomainNameModel
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
    // @Override
    public PokeRecord getRecord(String name) throws Exception, UnknownHostException {
        PokeRecord fetchedRecord;

            InputStream ipDetailStream = NetUtil.getIpDetails(name);
            String ipDetailStr = new String(ipDetailStream.readAllBytes()).replace("\n", "");
            // Change the IP details from input stream to a PokeRecord object using ObjectMapper。
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            TypeReference<PokeRecord> typeRef = new TypeReference<PokeRecord>() { };
            fetchedRecord = mapper.readValue(ipDetailStr, typeRef);
            System.out.println(fetchedRecord);
            return fetchedRecord;
    }

    // /**
    //  * Get the records as a list.
    //  * @param database the database
    //  *
    //  * @return the list of records
    //  */
    // @Override
    // public List<PokeRecord> getRecords() throws Exception, UnknownHostException {
    //     InputStream ipDetailStream = NetUtil.getIpDetails("all");
    //     List<PokeRecord> pokeRecords;

    //     ObjectMapper mapper = new ObjectMapper();
    //     mapper.enable(SerializationFeature.INDENT_OUTPUT);

    //     TypeReference<PokeRecord> typeRef = new TypeReference<PokeRecord>() { };

    //     return pokeRecords;
    // }

    public void saveRecord(PokeRecord newRecord) throws Exception {
        // Load existing records
        ObjectMapper mapper = new ObjectMapper();
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
     * Gets the PokeRecord by pokemon id.
     *
     * @param id id of the pokemon, also corresponds to its order in pokedex
     * @return PokeRecord
     * @throws IOException
     */
    public PokeRecord getPokemonByID(int id) throws IOException {
        File databaseFile = new File(DATABASE_FILE);
        ObjectMapper mapper = new ObjectMapper();
        List<PokeRecord> records;

        // Read the existing records from the file
        if (databaseFile.exists() && databaseFile.length() > 0) {
            try {
                records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
                  // Search for the record with the given ID
                for (PokeRecord record : records) {
                    if (record.id() == id) { // Assuming PokeRecord has a getId() method
                        return record;
                    }
                }
            } catch (IOException e) {
                System.out.println("Pokemon not found in database.");
            }
        }
        // Return null or throw an exception if the record is not found
        throw new IOException();
    }

    /**
     * Gets the pokemon as a PokeRecord by name, case-insensitive search.
     *
     * @param pokemonName name of the pokemon
     * @return PokeRecord
     * @throws IOException
     */
    public PokeRecord getPokemonByName(String pokemonName) throws IOException {
        File databaseFile = new File(DATABASE_FILE);
        ObjectMapper mapper = new ObjectMapper();
        List<PokeRecord> records;

        // Read the existing records from the file
        if (databaseFile.exists() && databaseFile.length() > 0) {
            try {
                records = mapper.readValue(databaseFile, new TypeReference<List<PokeRecord>>() {});
                  // Search for the record with the given ID
                for (PokeRecord record : records) {
                    if (record.name().toLowerCase() == pokemonName.toLowerCase().strip()) { // Assuming PokeRecord has a getId() method
                        return record;
                    }
                }
            } catch (IOException e) {
                System.out.println("Pokemon not found in database.");
            }
        }
        // Return null or throw an exception if the record is not found
        throw new IOException();
    }

    public List<PokeRecord> getAllPokemon() throws IOException{
        File databaseFile = new File(DATABASE_FILE);
        ObjectMapper mapper = new ObjectMapper();
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
}
