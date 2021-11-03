package br.pedro.rproject.models.dtos;

import br.pedro.rproject.models.embedded.Item;
import br.pedro.rproject.models.entities.Persona;

import java.util.ArrayList;
import java.util.List;

public class ItemDTO {
    private String id;
    private final List<Item> items = new ArrayList<>();

    public ItemDTO(Persona persona) {
        this.id = persona.getId();
        items.addAll(persona.getItems());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }
}
