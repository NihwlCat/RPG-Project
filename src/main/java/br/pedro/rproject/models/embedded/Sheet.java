package br.pedro.rproject.models.embedded;

import br.pedro.rproject.models.enums.SheetAttribute;

import java.util.HashMap;
import java.util.Map;

public class Sheet {

    private Map<SheetAttribute,Integer> general = new HashMap<>();
    private Map<SheetAttribute,Integer> basic = new HashMap<>();
    private Map<SheetAttribute,Integer> offensive = new HashMap<>();

    public Sheet() {
        setUp();
    }



    private void setUp() {
        basic.putAll(Map.ofEntries(
                Map.entry(SheetAttribute.SIZE,0),
                Map.entry(SheetAttribute.EDUCATION,0),
                Map.entry(SheetAttribute.INTELLIGENCE,0),
                Map.entry(SheetAttribute.DEXTERITY,0),
                Map.entry(SheetAttribute.APPEARANCE,0),
                Map.entry(SheetAttribute.POWER,0),
                Map.entry(SheetAttribute.STRENGTH,0),
                Map.entry(SheetAttribute.CONSTITUTION,0)
        ));

        general.putAll(Map.ofEntries(
                Map.entry(SheetAttribute.DODGE,0),
                Map.entry(SheetAttribute.PERSUADE,0),
                Map.entry(SheetAttribute.LISTEN,0),
                Map.entry(SheetAttribute.SEARCH,0),
                Map.entry(SheetAttribute.PSYCHOLOGY,0),
                Map.entry(SheetAttribute.STEALTH,0),
                Map.entry(SheetAttribute.THROW,0),
                Map.entry(SheetAttribute.AUTOMOBILES,0),
                Map.entry(SheetAttribute.SOS,0)
        ));

        offensive.putAll(Map.ofEntries(
                Map.entry(SheetAttribute.STRUGGLE,0),
                Map.entry(SheetAttribute.PRIMARY,0),
                Map.entry(SheetAttribute.SECONDARY,0),
                Map.entry(SheetAttribute.OTHERS,0)
        ));
    }

    public Map<SheetAttribute, Integer> getGeneral() {
        return general;
    }

    public Map<SheetAttribute, Integer> getBasic() {
        return basic;
    }

    public Map<SheetAttribute, Integer> getOffensive() {
        return offensive;
    }

    public void setGeneral(Map<SheetAttribute, Integer> general) {
        this.general = general;
    }

    public void setBasic(Map<SheetAttribute, Integer> basic) {
        this.basic = basic;
    }

    public void setOffensive(Map<SheetAttribute, Integer> offensive) {
        this.offensive = offensive;
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "general=" + general +
                ", basic=" + basic +
                ", offensive=" + offensive +
                '}';
    }
}
