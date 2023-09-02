package fr.cakihorse.utils;

import org.javacord.api.event.message.MessageCreateEvent;

public interface CommandExecutor {

    void run(MessageCreateEvent e, Command command, String[] args);
}
