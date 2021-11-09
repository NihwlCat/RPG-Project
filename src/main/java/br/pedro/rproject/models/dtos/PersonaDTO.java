package br.pedro.rproject.models.dtos;

import br.pedro.rproject.models.embedded.*;
import br.pedro.rproject.models.entities.Persona;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PersonaDTO {
    private String id;
    private Profile profile;
    private Individuality individuality;
    private Basic basic;
    private final List<Seal> seals = new ArrayList<>();

    public PersonaDTO() {
    }

    public PersonaDTO(Persona persona){
        this.id = persona.getId();
        this.individuality = persona.getIndividuality();
        this.basic = persona.getBasic();

        this.profile = new Profile(persona.getProfile()){

            @JsonProperty(access = JsonProperty.Access.READ_ONLY)
            private String statusVerbose = persona.getProfile().getStatus().getValue();

            public String getStatusVerbose() {
                return statusVerbose;
            }

            public void setStatusVerbose(String statusVerbose) {
                this.statusVerbose = statusVerbose;
            }
        };

        seals.addAll(persona.getSeals());
    }

    public String getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public Individuality getIndividuality() {
        return individuality;
    }

    public Basic getBasic() {
        return basic;
    }

    public List<Seal> getSeals() {
        return seals;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setIndividuality(Individuality individuality) {
        this.individuality = individuality;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "id='" + id + '\'' +
                ", profile=" + profile +
                ", individuality=" + individuality +
                ", basic=" + basic +
                ", seals=" + seals +
                '}';
    }
}
