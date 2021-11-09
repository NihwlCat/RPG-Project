package br.pedro.rproject.models.dtos;

import br.pedro.rproject.models.embedded.Item;
import br.pedro.rproject.models.embedded.Spell;
import br.pedro.rproject.models.entities.Persona;

import java.util.ArrayList;
import java.util.List;

public class SpellDTO {
    private String id;
    private final List<Spell> spells = new ArrayList<>();

    public SpellDTO(Persona persona) {
        this.id = persona.getId();
        spells.addAll(persona.getSpells());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Spell> getSpells() {
        return spells;
    }
}
