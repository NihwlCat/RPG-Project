package br.pedro.rproject.models.embedded;

import java.util.Objects;

public class Seal {
    private String name;
    private String description;
    private String imgUrl;

    public Seal() {
    }

    public Seal(String name, String description, String imgUrl) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seal seal = (Seal) o;
        return name.equals(seal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
