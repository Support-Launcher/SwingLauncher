package fr.cakihorse;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.io.File;

public class MyListener implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();
        if (message.getContent().equalsIgnoreCase("!ping")) {
            new MessageBuilder()
                    .append("Test d'embed ")
                    .setEmbed(new EmbedBuilder()
                            .setTitle("Disband")
                            .setDescription("decription!")
                            .setColor(Color.ORANGE))
                    .send(event.getChannel());
        }
    }

}