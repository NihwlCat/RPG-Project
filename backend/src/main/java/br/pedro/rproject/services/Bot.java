package br.pedro.rproject.services;

import net.dv8tion.jda.api.entities.MessageEmbed;

public interface Bot {
    void sendMessage(String guild, String channel, MessageEmbed message);
}
