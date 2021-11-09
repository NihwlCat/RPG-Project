package br.pedro.rproject.models.dtos;

import br.pedro.rproject.models.entities.Player;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class PlayerDTO {
    private String id;
    private String name;
    private String role;
    private String annotations;

    public PlayerDTO() {
    }

    public PlayerDTO(String name, String annotations) {
        this.name = name;
        this.annotations = annotations;
    }

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.role = player.getRole();
        this.annotations = player.getAnnotations();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }
}
