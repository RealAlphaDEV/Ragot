package ua.realalpha.ragot.command.craft;

import ua.realalpha.ragot.command.entity.RCommand;

import java.util.ArrayList;
import java.util.List;

public class CraftRCommand implements RCommand {

    private final List<String> commands = new ArrayList<>();
    private final List<String> aliases = new ArrayList<>();
    private final String permission;
    private final String usage;
    private final String description;

    public CraftRCommand(List<String> commands, List<String> aliases, String permission, String usage, String description) {
        this.commands.addAll(commands);
        this.aliases.addAll(aliases);
        this.permission = permission;
        this.usage = usage;
        this.description = description;
    }

    @Override
    public List<String> getCommand() {
        return this.commands;
    }

    @Override
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override
    public String getPermission() {
        return this.permission;
    }

    @Override
    public String getUsage() {
        return this.usage;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
