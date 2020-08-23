package ua.realalpha.ragot.command.entity;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public interface CommandResult {

    CommandSender asConsole();

    Optional<Player> asPlayer();

    RArgument getRArgument();

    String[] getArgs();

    RGroupCommand getRGroupCommand();





}
