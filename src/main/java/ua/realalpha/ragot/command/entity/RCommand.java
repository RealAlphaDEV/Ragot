package ua.realalpha.ragot.command.entity;

import java.util.List;

public interface RCommand {

    List<String> getCommand();

    List<String> getAliases();

    String getPermission();

    String getUsage();

    String getDescription();
}
