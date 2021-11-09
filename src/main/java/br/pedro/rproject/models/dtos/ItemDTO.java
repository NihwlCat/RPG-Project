package br.pedro.rproject.models.dtos;

import br.pedro.rproject.models.embedded.Item;
import br.pedro.rproject.models.entities.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemDTO {
    private String id;
    private final List<Item> items = new ArrayList<>();

    public ItemDTO(Persona persona) {
        this.id = persona.getId();
        items.addAll(persona.getItems().stream().map(i -> new Item(i.getType(), i.getDescription(), i.getId()){
            private String stringType = i.getType().getValue();

            public String getStringType() {
                return stringType;
            }

            public void setStringType(String stringType) {
                this.stringType = stringType;
            }
        }).collect(Collectors.toList()));
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
