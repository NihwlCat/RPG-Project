package br.pedro.rproject.models.entities;

import br.pedro.rproject.models.embedded.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "doc_characters")
public class Persona {

    @Id
    private String id;
    private Profile profile;
    private Individuality individuality;
    private Basic basic;

    private String playerId;


    // Nao trazer
    @TextIndexed
    private String history;
    private Sheet sheet;


    // Não trazer
    private List<Seal> seals = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Spell> spells = new ArrayList<>();

    @PersistenceConstructor
    public Persona() {

    }

    public Persona(String id, String personaName){
        this.id = id;
        this.sheet = new Sheet();
        this.profile = new Profile(personaName);
        this.individuality = new Individuality();
        this.basic = new Basic();
        this.playerId = "";
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Individuality getIndividuality() {
        return individuality;
    }

    public void setIndividuality(Individuality individuality) {
        this.individuality = individuality;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public List<Seal> getSeals() {
        return seals;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSeals(List<Seal> seals) {
        this.seals = seals;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", profile=" + profile +
                ", individuality=" + individuality +
                ", basic=" + basic +
                ", history='" + history + '\'' +
                ", sheet=" + sheet +
                ", seals=" + seals +
                ", items=" + items +
                ", spells=" + spells +
                '}';
    }
}
