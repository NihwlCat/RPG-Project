package br.pedro.rproject.models.dtos;

import br.pedro.rproject.models.embedded.Sheet;
import br.pedro.rproject.models.entities.Persona;

public class SheetDTO {
    private String id;
    private Sheet sheet;

    public SheetDTO(Persona persona) {
        this.id = persona.getId();
        this.sheet = persona.getSheet();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
}
