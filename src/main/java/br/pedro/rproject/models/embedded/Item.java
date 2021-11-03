package br.pedro.rproject.models.embedded;

import br.pedro.rproject.models.enums.ItemType;

import java.util.Objects;

public class Item {
    private int id;
    private ItemType type;
    private String description;

    public Item() {
    }

    public Item(ItemType type, String description, int id) {
        this.type = type;
        this.description = description;
        this.id = id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
