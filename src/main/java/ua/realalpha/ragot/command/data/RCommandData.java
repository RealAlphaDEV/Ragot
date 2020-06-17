package ua.realalpha.ragot.command.data;

import ua.realalpha.ragot.command.RCommandListener;
import ua.realalpha.ragot.command.RExecutorType;
import ua.realalpha.ragot.command.annotation.RCommand;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public final class RCommandData implements Cloneable {

    private final List<String> command;
    private final List<String> aliases;
    private final String description;
    private final String usage;
    private final List<String> args;
    private final RExecutorType rExecutorType;
    private final Method method;
    private final RCommandListener rCommandListener;

    public RCommandData(RCommand rCommand, Method method, RCommandListener rCommandListener) {
        this.command = Arrays.asList(rCommand.command());
        this.aliases = Arrays.asList(rCommand.aliases());
        this.description = rCommand.description();
        this.usage = rCommand.usage();
        this.args = Arrays.asList(rCommand.args());
        this.rExecutorType = rCommand.type();
        this.method = method;
        this.rCommandListener = rCommandListener;
    }

    public List<String> getCommand() {
        return command;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public List<String> getArgs() {
        return args;
    }

    public RExecutorType getRExecutorType() {
        return rExecutorType;
    }

    public Method getMethod() {
        return method;
    }

    public RCommandListener getRCommandListener() {
        return rCommandListener;
    }

    public RCommandData clone()  {
        try {
            return (RCommandData) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
