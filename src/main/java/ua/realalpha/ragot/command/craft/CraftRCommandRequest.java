package ua.realalpha.ragot.command.craft;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.realalpha.ragot.command.RArgument;
import ua.realalpha.ragot.command.RCommandRequest;

public class CraftRCommandRequest implements RCommandRequest {

    private final CommandSender commandSender;

    public CraftRCommandRequest(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public CommandSender asConsole() {
        return commandSender;
    }

    @Override
    public Player asPlayer() {
        return (Player) commandSender;
    }

    @Override
    public RArgument getRArgument() {
        return null;
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }
}
