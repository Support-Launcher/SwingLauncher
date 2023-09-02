package fr.cakihorse.utils;

import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Arrays;

public class MessageManager {



    private static CommandRegistry registry = new CommandRegistry();

    static {
        registry.addCommand(new Command(
                "test",
                "ping",
                new CommandTest(),
                "ping", "p?"
        ));

        registry.addCommand(new Command(
                "embed",
                "a embed gen",
                new CommandEmbed(),
                "embed", "emd"
        ));
    }

    private static final String PREFIX = "+";


    public static void create(MessageCreateEvent e) {

        if (e.getMessageContent().startsWith(PREFIX));
        {
            String[] args = e.getMessageContent().split(" ");
            String commandName = args[0].substring(PREFIX.length());
            args = args.length == 1 ? new String[0] : Arrays.copyOfRange(args, 1, args.length);

            String[] finalArgs = args;
            registry.getByAlias(commandName).ifPresent((cmd) -> {
                cmd.getExecutor().run(e, cmd, finalArgs);
            });
        }


    }
}
