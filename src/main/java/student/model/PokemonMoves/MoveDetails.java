package student.model.PokemonMoves;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.j2objc.annotations.J2ObjCIncompatible;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveDetails {
    // This class will contain details fetched from another api call
    // for each move
    // The instance of this class represents an inner json object of the move object of each pokemon in the moves list
    @JsonProperty("accuracy")
    private int accuracy;
    @JsonProperty("damage_class")
    private MoveDamageClass damageClass;
    @JsonProperty("power")
    private int power;
    @JsonProperty("pp")
    private int pp;

    // Constructors, getters, and setters
    public MoveDetails() {}

    public MoveDetails(int accuracy, MoveDamageClass damageClass, int power, int pp) {
        this.accuracy = accuracy;
        this.damageClass = damageClass;
        this.power = power;
        this.pp = pp;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public MoveDamageClass getDamageClass() {
        return damageClass;
    }

    public void setDamageClass(MoveDamageClass damageClass) {
        this.damageClass = damageClass;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }
}
