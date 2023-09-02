package fr.cakihorse.utils;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class CommandEmbed implements CommandExecutor {

    @Override
    public void run(MessageCreateEvent e, Command command, String[] args) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Disband")
                .setAuthor("Undercraft | Vénus")
                .setFooter("created by cakihorse")
                .addField("Pays", "**motif**")
                .setColor(Color.ORANGE);

        e.getChannel().sendMessage(embed);
    }

}
