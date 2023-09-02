package fr.cakihorse.utils;

import org.javacord.api.event.message.MessageCreateEvent;

public class CommandTest implements CommandExecutor {
    @Override
    public void run(MessageCreateEvent e, Command command, String[] args) {
        e.getChannel().sendMessage("test good ! ");
    }
}
