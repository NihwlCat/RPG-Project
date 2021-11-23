package br.pedro.rproject.services;

import br.pedro.rproject.configs.Utils;
import br.pedro.rproject.configs.Utils;
import br.pedro.rproject.models.dtos.*;
import br.pedro.rproject.models.embedded.Item;
import br.pedro.rproject.models.embedded.Seal;
import br.pedro.rproject.models.embedded.Sheet;
import br.pedro.rproject.models.embedded.Spell;
import br.pedro.rproject.models.entities.Persona;
import br.pedro.rproject.models.enums.SheetAttribute;
import br.pedro.rproject.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaRepository repository;

    @Autowired
    PersonaService(PersonaRepository repository){
        this.repository = repository;
    }

    private String getAuthenticatedId(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private Persona recoverPersona(String id){
        return repository.findById(id).orElseThrow(() -> new ServiceException("Not found", HttpStatus.NOT_FOUND));
    }

    private Persona recoverFromAuthenticated(String cod){
        return repository.findByPlayerId(cod).orElseThrow(() -> new ServiceException("Not found", HttpStatus.NOT_FOUND));
    }

    private void updateProfile(Persona p, PersonaDTO dto){
        p.setProfile(dto.getProfile());
        p.setBasic(dto.getBasic());
        p.setIndividuality(dto.getIndividuality());
    }

    @SuppressWarnings("unchecked")
    private void updateSheet(Persona p, Map<String,Integer> toUpdate, String locale) throws IllegalAccessException, NoSuchFieldException {
        Sheet sheet = p.getSheet();
        Field f = sheet.getClass().getDeclaredField(locale);
        f.setAccessible(true);
        ((Map<SheetAttribute,Integer>) f.get(sheet)).replaceAll((key, value) -> toUpdate.getOrDefault(key.getValue(), value));

        p.setSheet(sheet);
    }

    public List<PersonaDTO> findAll(){
        return repository.findAllWithExcludedFields()
                .stream()
                .map(PersonaDTO::new)
                .collect(Collectors.toList());
    }

    public PersonaDTO findProfileByAuthenticated() {
        Persona p = repository.findWithExcludedFields(getAuthenticatedId())
                .orElseThrow(() -> new ServiceException("Not found", HttpStatus.NOT_FOUND));
        return new PersonaDTO(p);
    }

    public void deleteCharacter(String id){
        recoverPersona(id);
        repository.deleteById(id);
    }

    public PersonaDTO createCharacter(String name){
        String cod  = ((int) repository.count() + 1) + Utils.stringGenerate();
        Persona p = new Persona(cod, name);

        p = repository.save(p);
        return new PersonaDTO(p);
    }

    public <T> void updateByResourceAndAuthenticated(String locale, T object) {
        Persona p = recoverFromAuthenticated(getAuthenticatedId());
        makeUpdate(locale, object, p);
    }

    public <T> void deleteItemFromAuthenticated(String locale, T object){
        Persona p = recoverFromAuthenticated(getAuthenticatedId());

        if(locale.equals("items")){
            p.getItems().remove((Item) object);
        } else if(locale.equals("spells")) {
            p.getSpells().remove((Spell) object);
        } else {
            p.getSeals().remove((Seal) object);
        }
        repository.save(p);
    }

    @SuppressWarnings("unchecked")
    public <T> T findAttributesByAuthenticated(String resource){
        String cod = getAuthenticatedId();

        if(!repository.existsByPlayerId(cod)){
            throw new ServiceException("Not found", HttpStatus.NOT_FOUND);
        }

        T obj;

        switch (resource){
            case "sheet" -> obj =  (T) new SheetDTO(repository.findSheetFromPersona(cod));
            case "history" -> obj = (T) new HistoryDTO(repository.findHistoryFromPersona(cod));
            case "items" -> obj = (T) new ItemDTO(repository.findItemsFromPersona(cod));
            case "spells" -> obj = (T) new SpellDTO(repository.findSpellsFromPersona(cod));
            default -> throw new ServiceException("Invalid resource path", HttpStatus.BAD_REQUEST);
        }
        return obj;
    }

    public <T> void updateById(String id, String locale, T object) {
        Persona p = recoverPersona(id);
        makeUpdate(locale, object, p);
    }

    @SuppressWarnings("unchecked")
    private <T> void makeUpdate(String locale, T object, Persona p) {

        if(object instanceof PersonaDTO){
            updateProfile(p,(PersonaDTO) object);
        } else if(object instanceof Spell){
            p.getSpells().remove(object);
            p.getSpells().add((Spell) object);
        } else if (object instanceof Item){
            p.getItems().remove(object);
            p.getItems().add((Item) object);
        } else if (object instanceof HistoryDTO) {
            p.setHistory(((HistoryDTO) object).getHistory());
        } else if (object instanceof Seal) {
            p.getSeals().remove(object);
            p.getSeals().add((Seal) object);
        } else {
            try { updateSheet(p, (Map<String, Integer>) object, locale); }
            catch (IllegalAccessException | NoSuchFieldException e) { return; }
        }

        repository.save(p);
    }

    @SuppressWarnings("unchecked")
    public <T> T findAttributesById(String id, String resource) {
        Persona p = recoverPersona(id);

        T obj;

        switch (resource){
            case "sheet" -> obj =  (T) new SheetDTO(p);
            case "history" -> obj = (T) new HistoryDTO(p);
            case "items" -> obj = (T) new ItemDTO(p);
            case "spells" -> obj = (T) new SpellDTO(p);
            default -> throw new ServiceException("Invalid resource path", HttpStatus.BAD_REQUEST);
        }
        return obj;
    }

    public PersonaDTO findProfileById(String id) {
        Persona p = repository.findWithExcludedFieldsMaster(id).orElseThrow(() -> new ServiceException("Not found", HttpStatus.NOT_FOUND));
        return new PersonaDTO(p);
    }

    public <T> void deleteItemFromId(String id, String locale, T object) {
        Persona p = recoverPersona(id);

        if(locale.equals("items")){
            p.getItems().remove((Item) object);
        } else if(locale.equals("spells")) {
            p.getSpells().remove((Spell) object);
        } else {
            p.getSeals().remove((Seal) object);
        }
        repository.save(p);
    }
}
