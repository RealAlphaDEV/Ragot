package ua.realalpha.ragot.command.craft;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ua.realalpha.ragot.command.entity.RArgument;
import ua.realalpha.ragot.command.entity.CommandResult;
import ua.realalpha.ragot.command.entity.RGroupCommand;

import java.util.Optional;

public class CraftCommandResult implements CommandResult {

    private final CommandSender commandSender;

    public CraftCommandResult(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public CommandSender asConsole() {
        return commandSender;
    }

    @Override
    public Optional<Player> asPlayer() {
        if (commandSender instanceof Player) return Optional.of((Player) commandSender);
        return Optional.empty();
    }

    @Override
    public RArgument getRArgument() {
        return null;
    }

    @Override
    public String[] getArgs() {
        return new String[0];
    }

    @Override
    public RGroupCommand getRGroupCommand() {
        return null;
    }
}
