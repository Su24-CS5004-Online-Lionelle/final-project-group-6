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

public class PokemonModel {

    /** An instance of PokemonModel. */
    private static PokemonModel instance;

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
        // Get current list of DNRecords from the database.
        // List<DNRecord> currentRecordsList = getRecords(database);

        // boolean existed = true;

        // for (int i = 0; i < currentRecordsList.size(); i++) {
        //     DNRecord record = currentRecordsList.get(i);
        //     String existedHostname = record.hostname();
        //     // Check if the hostname is already existed in the databse.
        //     if (existedHostname.equals(hostname)) {
        //         existed = true;
        //         return record; 
        //     }
        // }
        PokeRecord fetchedRecord;

        // If the name is valid and does not exist in current records, create the record.
        // if (!existed) {
            // // Find the ip for the hostname.
            // String ip = NetUtil.lookUpIp(name);
            // Get the ip details.
            InputStream ipDetailStream = NetUtil.getIpDetails(name);
            String ipDetailStr = new String(ipDetailStream.readAllBytes()).replace("\n", "");
            // Change the IP details from input stream to a PokeRecord object using ObjectMapper。
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            TypeReference<PokeRecord> typeRef = new TypeReference<PokeRecord>() { };
            fetchedRecord = mapper.readValue(ipDetailStr, typeRef);
            return fetchedRecord;
        // }
        // return null;
    }
            
    //         currentRecordsList.add(newRecord);
            
    //         // Update database.
    //         FileOutputStream fileOutputStream = new FileOutputStream(database);
    //         DataFormatter.write(currentRecordsList, Formats.XML, fileOutputStream);
    //         return newRecord;
    //     }
    //     return null;
    // }

    // /**
    //  * Get the records as a list.
    //  * @param database the database
    //  * 
    //  * @return the list of records
    //  */
    // @Override
    // public List<DNRecord> getRecords(String database) throws FileNotFoundException, Exception {
    //     // Get current updated list of DNRecords from the database.
    //     List<DNRecord> currentRecordsList = new ArrayList<>();
    //     File file = new File(database);
    //     FileReader fileReader = new FileReader(file);
    //     // Read contents of the file into a character buffer.
    //     char[] buffer = new char[(int) file.length()];
    //     fileReader.read(buffer);
    //     fileReader.close();
    //     // Convert buffer to a String and conver the xml data in string to list.
    //     String xml = new String(buffer);
    //     ObjectMapper mapper = new XmlMapper();
    //     TypeReference<List<DNRecord>> typeRef = new TypeReference<List<DNRecord>>() { };
    //     currentRecordsList = mapper.readValue(xml, typeRef);
    //     return currentRecordsList;
    // }
}
