package br.pedro.rproject.configs;

import br.pedro.rproject.models.entities.Persona;
import br.pedro.rproject.models.entities.Player;
import br.pedro.rproject.repositories.PersonaRepository;
import br.pedro.rproject.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestClass {

    @Autowired
    private PersonaRepository repository;

    @Autowired
    private PlayerRepository pRepository;

    @PostConstruct
    void setUp(){
        repository.deleteAll();
        pRepository.deleteAll();

        //

        Player p =  new Player("PEDRO#2122","Pedro","DEFAULT","JOGADOR");
        Player m = new Player("MASTER#2122","Master","DEFAULT","MASTER");

        Persona char1 = new Persona(Utils.stringGenerate() + 1, "Bill Cipher");
        char1.setPlayerId(p.getId());

        char1.getProfile().setAge(999);
        char1.getProfile().setAlignment("Dimensão do caos");

        char1.getBasic().setMaxLife(9999);
        char1.getBasic().setLife(756);

        char1.getBasic().setAwakening(33); // 50
        char1.getBasic().setControl(17);

        char1.getIndividuality().setDescription("Conjura o estranhagedom");
        char1.getIndividuality().setValue(88);

        char1.setHistory("Um triângulo capaz de destruir o universo");

        //

        repository.save(char1);
        pRepository.save(p);
        pRepository.save(m);
    }
}
