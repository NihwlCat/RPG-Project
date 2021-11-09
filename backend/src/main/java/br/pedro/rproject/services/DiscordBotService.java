package br.pedro.rproject.services;

import br.pedro.rproject.configs.Utils;
import br.pedro.rproject.models.embedded.Sheet;
import br.pedro.rproject.models.entities.Persona;
import br.pedro.rproject.models.enums.SheetAttribute;
import br.pedro.rproject.repositories.PersonaRepository;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.Map;

@Service
public class DiscordBotService implements Bot{
    private JDA jda;
    private final PersonaRepository repository;

    @Autowired
    DiscordBotService(PersonaRepository repository, @Value("${discord.token}") String TOKEN){
        this.repository = repository;

        try {
            jda = JDABuilder.createLight(TOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS).build();
            jda.getPresence().setStatus(OnlineStatus.ONLINE);
            jda.getPresence().setActivity(Activity.watching("Lavender Fields"));
            jda.awaitReady();
            jda.getGuilds().forEach(g -> System.out.println(g.getId()+ ","+ g.getName() + "::" + g.getChannels()));
            //jda.getGuilds().forEach(x -> Objects.requireNonNull(x.getDefaultChannel()).sendMessage("~ MUSICA SINISTRA TAM TAM TAAAM ~ Voltei").queue());
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Map<SheetAttribute, Integer> argBelongs(Sheet sheet, String attribute) {
        Map<SheetAttribute, Integer> map = null;
        if(sheet.getBasic().containsKey(SheetAttribute.fromString(attribute))){
            map = sheet.getBasic();
        } else if(sheet.getOffensive().containsKey(SheetAttribute.fromString(attribute))){
            map = sheet.getOffensive();
        } else if(sheet.getGeneral().containsKey(SheetAttribute.fromString(attribute))) {
            map = sheet.getGeneral();
        }
        return map;
    }

    public String diceRolling(String attribute, String personaId){
        Persona p = repository.findById(personaId).orElseThrow(() -> new ServiceException("Not found", HttpStatus.NOT_FOUND));
        return getDiceRolling(attribute, p);
    }

    @SuppressWarnings("ConstantConditions")
    public void sendMessage(String guild, String channel, MessageEmbed message) {
        try {
            jda.getGuildById(guild).getTextChannelById(channel).sendMessageEmbeds(message).queue();
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public MessageEmbed getDiceMessage (String result, String attribute) {
        String[] camps = result.split(",");
        EmbedBuilder message = new EmbedBuilder();
        message.setTitle("\uD83C\uDFB2 TESTE DE DADO");
        message.setColor(Color.decode("#D7B4F3"));

        message.addField(attribute.toUpperCase(),"",false);
        message.addField(camps[2],camps[3],false);
        message.addField(camps[0],camps[1],false);
        return  message.build();
    }

    public String diceRollingAuthenticated(String attribute) {
        String cod = SecurityContextHolder.getContext().getAuthentication().getName();
        Persona p = repository.findByPlayerId(cod).orElseThrow(() -> new ServiceException("Not found", HttpStatus.NOT_FOUND));
        return getDiceRolling(attribute, p);
    }

    @NotNull
    private String getDiceRolling(String attribute, Persona p) {
        String message;
        if(attribute.equalsIgnoreCase("EXPOSIÇÃO")){
            int total = p.getBasic().getAwakening() + p.getBasic().getControl();
            message = Utils.diceResult(total);
        } else if (attribute.equalsIgnoreCase("INDIVIDUALIDADE")){
            int value = p.getIndividuality().getValue();
            message = Utils.diceResult(value);
        } else {
            var map = argBelongs(p.getSheet(),attribute);
            int value = map.get(SheetAttribute.fromString(attribute));
            message = Utils.diceResult(value);
        }

        return message + "," + p.getProfile().getName() + "," + p.getProfile().getStatus().getValue();
    }
}
