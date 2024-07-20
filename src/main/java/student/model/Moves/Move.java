package student.model.Moves;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Move {

    private String name;
    private String url;
    private MoveDetails details;

    // Constructor
    @JsonCreator
    public Move(@JsonProperty("name") String name, @JsonProperty("url") String url) {
        this.name = name;
        this.url = url;
        // this.details = details;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @JsonIgnore
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private MoveDetails fetchMoveDetails() {
        // Need to make another api call to get the move details json file
        // and populate the MoveDetails objects
        // Finally, add the move details as an object to move name

        // empty stub
        return details;
    }
}
