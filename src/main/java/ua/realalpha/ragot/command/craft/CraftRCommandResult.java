package ua.realalpha.ragot.command.craft;

import org.bukkit.command.CommandSender;
import ua.realalpha.ragot.command.entity.RCommand;
import ua.realalpha.ragot.command.entity.RCommandResult;
import ua.realalpha.ragot.command.entity.RTweakArgument;

import java.util.Arrays;
import java.util.List;

public final class CraftRCommandResult implements RCommandResult {

    private final CommandSender commandSender;
    private final CraftRTweakArgument craftRArgument;
    private final String[] args;
    private List<RCommand> rCommands;

    public CraftRCommandResult(CommandSender commandSender, String[] args, int index, List<RCommand> rCommands) {
        this.commandSender = commandSender;
        this.args = tweakArguments(Arrays.asList(args), index);
        this.craftRArgument = new CraftRTweakArgument(this.args);
        this.rCommands = rCommands;
    }


    @Override
    public <T extends CommandSender> T asSender(Class<T> tClass) {
        return (T) commandSender;
    }

    @Override
    public RTweakArgument getRTweakArgument() {
        return craftRArgument;
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public List<RCommand> getTopRsCommand() {
        return rCommands;
    }

    private String[] tweakArguments(List<String> args, int index){
        return args.subList(index, args.size()).toArray(new String[]{});
    }
}
