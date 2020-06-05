package ua.realalpha.ragot.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface RCommandRequest  {

    CommandSender asConsole();

    Player asPlayer();

    RArgument getRArgument();

    String[] getArgs();





}
