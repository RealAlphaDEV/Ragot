package ua.realalpha.ragot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ua.realalpha.ragot.command.builder.RParentCommand;

import java.util.List;

public class RCommandListener extends Command {

    private final RCommandManager rCommandManager;
    public RCommandListener(RParentCommand rCommand, RCommandManager rCommandManager) {
        super(rCommand.getCommand(), rCommand.getDescription(), rCommand.getUsage(), rCommand.getAliases());
        this.rCommandManager = rCommandManager;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return super.tabComplete(sender, alias, args);
    }
}
