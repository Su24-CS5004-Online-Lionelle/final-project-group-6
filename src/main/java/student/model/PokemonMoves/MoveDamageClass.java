package student.model.PokemonMoves;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.j2objc.annotations.J2ObjCIncompatible;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveDamageClass {
    @JsonProperty("name")
    private String damageType;

    // Constructors, getters, and setters
    public MoveDamageClass() {}

    public MoveDamageClass(String damageType) {
        this.damageType = damageType;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }
}
