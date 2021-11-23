package br.pedro.rproject.resources;

import br.pedro.rproject.services.DiscordBotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/interactions")
public class BotResource {
    private final DiscordBotService service;

    BotResource(DiscordBotService service){
        this.service = service;
    }

    @PostMapping(value = "/bot")
    public ResponseEntity<String> msgDiscord(@RequestBody String attribute, @RequestParam(defaultValue = "") String personaId){
        String msg;
        if(personaId.equals("")){
            msg = service.diceRollingAuthenticated(attribute);
        } else {
            msg = service.diceRolling(attribute, personaId);
        }
        service.sendMessage("762811309263552523", "895851040304431144", service.getDiceMessage(msg,attribute));
        return ResponseEntity.ok(msg);
    }
}
