package br.pedro.rproject.configs;

import br.pedro.rproject.models.embedded.Item;
import br.pedro.rproject.models.embedded.Spell;
import br.pedro.rproject.models.entities.Persona;
import br.pedro.rproject.models.entities.Player;
import br.pedro.rproject.models.enums.ItemType;
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

        Player p = new Player("PEDRO#2122","Pedro","DEFAULT","JOGADOR");
        Player m = new Player("MASTER#2122","Master","DEFAULT","MASTER");

        Persona char1 = new Persona(Utils.stringGenerate() + 1, "Bill Cipher");
        char1.setPlayerId(p.getId());

        char1.getProfile().setAge(24);
        char1.getProfile().setAlignment("Ordo Libertas - Líder Cumpremessa");

        char1.getBasic().setMaxLife(81);
        char1.getBasic().setLife(76);

        char1.getBasic().setAwakening(64); // 50
        char1.getBasic().setControl(47);

        char1.getIndividuality().setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        char1.getIndividuality().setValue(98);

        char1.setHistory("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        // Spells


        char1.getSpells().add(new Spell("Ignis Ignócia", "Ignis", "Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups."));
        char1.getSpells().add(new Spell("Naturalis", "Naturalis", "Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups."));
        char1.getSpells().add(new Spell("Invalidar Realidade", "Ignis", "Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups."));

        char1.getItems().add(new Item(ItemType.MELEE_WEAPON, "Um item muito legal mesmo!", 0));
        repository.save(char1);
        pRepository.save(p);
        pRepository.save(m);
    }
}
