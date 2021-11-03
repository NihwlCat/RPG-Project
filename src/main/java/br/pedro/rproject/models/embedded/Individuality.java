package br.pedro.rproject.models.embedded;

public class Individuality {
    private int value;
    private String description;

    public Individuality() {
    }

    public Individuality(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Individuality{" +
                "value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}
