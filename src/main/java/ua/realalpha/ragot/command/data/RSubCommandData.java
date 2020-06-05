package ua.realalpha.ragot.command.data;

import ua.realalpha.ragot.command.RCommandListener;
import ua.realalpha.ragot.command.annotation.RSubCommand;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class RSubCommandData {

    private final String command;
    private final List<String> underCommands;
    private final String usage;
    private final String description;
    private final List<String> args;
    private final Method method;
    private final RCommandListener rCommandListener;

    public RSubCommandData(RSubCommand rSubCommand, Method method, RCommandListener rCommandListener) {
        this.command = rSubCommand.command();
        this.underCommands = Arrays.asList(rSubCommand.underCommand());
        this.usage = rSubCommand.usage();
        this.description = rSubCommand.description();
        this.args = Arrays.asList(rSubCommand.args());
        this.method = method;
        this.rCommandListener = rCommandListener;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getUnderCommands() {
        return underCommands;
    }

    public String getUsage() {
        return usage;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getArgs() {
        return args;
    }

    public Method getMethod() {
        return method;
    }

    public RCommandListener getRCommandListener() {
        return rCommandListener;
    }
}
