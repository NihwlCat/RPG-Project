package br.pedro.rproject.models.dtos;

import br.pedro.rproject.models.entities.Persona;
import br.pedro.rproject.models.enums.SheetAttribute;

import java.util.HashMap;
import java.util.Map;

public class SheetDTO {
    private String id;
    private Map<String,Integer> basic;
    private Map<String,Integer> general;
    private Map<String,Integer> offensive;

    private Map<String,Integer> iterateHash (Map<SheetAttribute,Integer> map) {
        Map<String,Integer> stringMap = new HashMap<>();
        for(SheetAttribute att : map.keySet()){
            stringMap.put(att.getValue(), map.get(att));
        }
        return stringMap;
    }


    public SheetDTO(Persona persona) {
        this.id = persona.getId();
        this.basic = iterateHash(persona.getSheet().getBasic());
        this.general = iterateHash(persona.getSheet().getGeneral());
        this.offensive = iterateHash(persona.getSheet().getOffensive());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Integer> getBasic() {
        return basic;
    }

    public void setBasic(Map<String, Integer> basic) {
        this.basic = basic;
    }

    public Map<String, Integer> getGeneral() {
        return general;
    }

    public void setGeneral(Map<String, Integer> general) {
        this.general = general;
    }

    public Map<String, Integer> getOffensive() {
        return offensive;
    }

    public void setOffensive(Map<String, Integer> offensive) {
        this.offensive = offensive;
    }
}
