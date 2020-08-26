package ua.realalpha.ragot.command.entity;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface RCommandResult {

    <T extends CommandSender> T asSender(Class<T> tClass);

    RTweakArgument getRTweakArgument();

    String[] getArgs();

    List<RCommand> getTopRsCommand();


}
