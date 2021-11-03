package br.pedro.rproject.resources;

import br.pedro.rproject.models.dtos.PlayerDTO;
import br.pedro.rproject.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "players")
public class PlayerResource {
    private final PlayerService service;

    @Autowired
    PlayerResource(PlayerService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll(){
        var body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlayerDTO> findById(@PathVariable String id){
        var body = service.findById(id);
        return ResponseEntity.ok(body);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> updateAnnotations(@PathVariable String id, @RequestBody PlayerDTO dto){
        service.updateAnnotations(id,dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateAnnotations(@RequestBody PlayerDTO dto){
        service.updateAnnotationsFromAuthenticated(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO dto){
        var body = service.createPlayer(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(body.getId()).toUri();
        return ResponseEntity.ok(body);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable String id){
        service.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
