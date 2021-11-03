package br.pedro.rproject.models.dtos;

import br.pedro.rproject.models.embedded.*;
import br.pedro.rproject.models.entities.Persona;

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
        this.profile = persona.getProfile();
        this.individuality = persona.getIndividuality();
        this.basic = persona.getBasic();

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
