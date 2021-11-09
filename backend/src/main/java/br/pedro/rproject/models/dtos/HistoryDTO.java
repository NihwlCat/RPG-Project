package br.pedro.rproject.models.dtos;

import br.pedro.rproject.models.entities.Persona;

public class HistoryDTO {
    private String id;
    private String history;

    public HistoryDTO() {
    }

    public HistoryDTO(Persona persona) {
        this.id = persona.getId();
        this.history = persona.getHistory();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
