package br.pedro.rproject.repositories;

import br.pedro.rproject.models.entities.Persona;
import br.pedro.rproject.models.entities.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, String> {

    @Query(value = "{ }", fields = "{ sheet: false, history: false, items: false, spells: false }")
    List<Persona> findAllWithExcludedFields();

    @Query(value = "{ '_id': '?0' }", fields = "{ sheet: false, history: false, items: false, spells: false }")
    Optional<Persona> findWithExcludedFieldsMaster(String playerId);

    boolean existsByPlayerId(String playerId);

    Optional<Persona> findByPlayerId(String playerId);

    @Query(value = "{ 'playerId': '?0' }", fields = "{ sheet: false, history: false, items: false, spells: false }")
    Optional<Persona> findWithExcludedFields(String playerId);

    @Query(value = "{ 'playerId': '?0' }", fields = "{ spells: true }")
    Persona findSpellsFromPersona(String playerId);

    @Query(value = "{ 'playerId': '?0' }", fields = "{ items: true }")
    Persona findItemsFromPersona(String playerId);

    @Query(value = "{ 'playerId': '?0' }", fields = "{ sheet: true }")
    Persona findSheetFromPersona(String playerId);

    @Query(value = "{ 'playerId': '?0' }", fields = "{ history: true }")
    Persona findHistoryFromPersona(String playerId);

}
