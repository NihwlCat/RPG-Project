package br.pedro.rproject.resources;

import br.pedro.rproject.configs.Utils;
import br.pedro.rproject.models.dtos.PersonaDTO;
import br.pedro.rproject.services.PersonaService;
import br.pedro.rproject.services.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/characters")
public class PersonaResource {

    private final PersonaService service;

    @Autowired
    PersonaResource(PersonaService service){
        this.service = service;
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<PersonaDTO> findProfileByAuthenticated(){
        var body = service.findProfileByAuthenticated();
        return ResponseEntity.ok(body);
    }

    @GetMapping(value = "/profile/resources")
    public <T> ResponseEntity<T> findByAuthenticated(@RequestParam String resource){
        T body = service.findAttributesByAuthenticated(resource);
        return ResponseEntity.ok(body);
    }

    @PatchMapping(value = "/profile")
    public ResponseEntity<Void> updateByResource(@RequestParam String resource, @RequestBody String json) throws JsonProcessingException {
        var classes = Utils.getAvailableParams();
        if(!classes.containsKey(resource)){
            throw new ServiceException("Invalid parameter type", HttpStatus.BAD_REQUEST);
        }
        ObjectMapper mapper = new ObjectMapper();


        var obj = mapper.readValue(json, classes.get(resource));
        service.updateByResourceAndAuthenticated(resource, obj);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/profile")
    public ResponseEntity<Void> deleteItemFromInventory(@RequestParam String resource, @RequestBody String json) throws JsonProcessingException {
        if(!resource.equals("spells") && !resource.equals("items") && !resource.equals("seals")){
            throw new ServiceException("Invalid parameter type", HttpStatus.BAD_REQUEST);
        }
        ObjectMapper mapper = new ObjectMapper();
        var obj = mapper.readValue(json, Utils.getAvailableParams().get(resource));
        service.deleteItemFromAuthenticated(resource,obj);
        return ResponseEntity.noContent().build();
    }

    ///

    @GetMapping(value = "/master")
    public ResponseEntity<List<PersonaDTO>> findAll(){
        var body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @PostMapping(value = "/master")
    public ResponseEntity<PersonaDTO> createCharacter(@RequestBody String name){
        var obj = service.createCharacter(name);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/master/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }


    @DeleteMapping(value = "/master/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable String id){
        service.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/master/{id}/resources")
    public ResponseEntity<Void> updateById(@PathVariable String id, @RequestParam String resource, @RequestBody String json) throws JsonProcessingException {
        var classes = Utils.getAvailableParams();
        if(!classes.containsKey(resource)){
            throw new ServiceException("Invalid parameter type", HttpStatus.BAD_REQUEST);
        }
        ObjectMapper mapper = new ObjectMapper();

        var obj = mapper.readValue(json, classes.get(resource));
        service.updateById(id, resource, obj);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/master/{id}/resources")
    public <T> ResponseEntity<T> findById(@PathVariable String id, @RequestParam String resource){
        T body = service.findAttributesById(id,resource);
        return ResponseEntity.ok(body);
    }

    @GetMapping(value = "/master/{id}")
    public ResponseEntity<PersonaDTO> findProfileById(@PathVariable String id){
        var body = service.findProfileById(id);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping(value = "/master/{id}/resources")
    public ResponseEntity<Void> deleteItemFromInventory(@PathVariable String id, @RequestParam String resource, @RequestBody String json) throws JsonProcessingException {
        if(!resource.equals("spells") && !resource.equals("items") && !resource.equals("seals")){
            throw new ServiceException("Invalid parameter type", HttpStatus.BAD_REQUEST);
        }
        ObjectMapper mapper = new ObjectMapper();
        var obj = mapper.readValue(json, Utils.getAvailableParams().get(resource));
        service.deleteItemFromId(id,resource,obj);
        return ResponseEntity.noContent().build();
    }
}
