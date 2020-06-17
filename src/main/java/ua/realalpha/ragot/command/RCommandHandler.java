package ua.realalpha.ragot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ua.realalpha.ragot.command.data.RCommandData;

import java.util.List;

public class RCommandHandler extends Command {

    private final RCommandManager rCommandManager;
    public RCommandHandler(RCommandData rCommandData, RCommandManager rCommandManager) {
        super(rCommandData.getCommand().get(0), rCommandData.getDescription(), rCommandData.getUsage(), rCommandData.getAliases());
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
