package fr.cakihorse;

import fr.cakihorse.utils.MessageManager;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandOption;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Insert your bot's token here
        String token = "MTE0NzI3MDkwMzkzMjE0NTY4OA.GEAIqc.3dtYZp4AedoH1JIYb75p5OYtjrEOK6NORJO4vE";

        DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();



        api.addMessageCreateListener(MessageManager::create);


        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }

}