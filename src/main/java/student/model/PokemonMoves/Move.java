package student.model.PokemonMoves;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import student.model.NetUtil;
import student.model.PokeRecord;
import student.model.PokemonMoves.NetUtilsMoveDetails;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Move {

    private String name;
    private String url;
    @JsonProperty("details")
    private MoveDetails details;

    // Constructor
    @JsonCreator
    public Move(@JsonProperty("name") String name, @JsonProperty("url") String url) {
        this.name = name;
        this.url = url;
        setMoveDetails();
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MoveDetails getMoveDetails(MoveDetails details) {
        return details;
    }

    public void setMoveDetails() {
        try {
            this.details = fetchMoveDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MoveDetails fetchMoveDetails() throws Exception, IOException {
        // Need to make another api call to get the move details json file
        // and populate the MoveDetails objects
        // Finally, add the move details as an object to move name
        MoveDetails moveDetails;

        InputStream moveDetailsStream = NetUtilsMoveDetails.getMoveDetails(getUrl());
            String moveDetailsString = new String(moveDetailsStream.readAllBytes()).replace("\n", "");
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            TypeReference<MoveDetails> typeRef = new TypeReference<MoveDetails>() { };
            moveDetails = mapper.readValue(moveDetailsString, typeRef);
            return moveDetails;
    }
}
