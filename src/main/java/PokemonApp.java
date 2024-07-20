import java.io.InputStream; // Add this import statement
import java.io.InputStreamReader; // Add this import statement
import org.json.simple.parser.JSONParser; // Add this import statement
import org.json.simple.JSONObject; // Add this import statement
import java.io.IOException; // Add this import statement
import org.json.simple.parser.ParseException; // Add this import statement

import student.model.NetUtils;

public class PokemonApp {
    public static void main (String[] args) {
        InputStream inputStream = NetUtils.getIpDetails("pikachu");
        JSONParser jsonParser = new JSONParser();
        try {
            try {
                JSONObject jsonObject = (JSONObject)jsonParser.parse(
                    new InputStreamReader(inputStream, "UTF-8"));
                    System.out.println(jsonObject.get("name"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }



    }
}
