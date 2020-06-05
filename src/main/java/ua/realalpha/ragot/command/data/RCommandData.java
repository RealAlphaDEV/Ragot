package ua.realalpha.ragot.command.data;

import ua.realalpha.ragot.command.RCommandListener;
import ua.realalpha.ragot.command.RExecutorType;
import ua.realalpha.ragot.command.annotation.RCommand;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RCommandData {

    private final String command;
    private List<String> aliases;
    private String description;
    private RExecutorType rExecutorType;
    private Method method;
    private RCommandListener rCommandListener;
    private final Map<String, RSubCommandData> subCommandDataMap = new HashMap<>();

    public RCommandData(String command) {
        this.command = command;
    }

    public RCommandData setUp(RCommand rCommand, Method method, RCommandListener rCommandListener){
        this.aliases = Arrays.asList(rCommand.aliases());
        this.description = rCommand.description();
        this.rExecutorType = rCommand.type();
        this.method = method;
        this.rCommandListener = rCommandListener;
        return this;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getDescription() {
        return description;
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

    public void addSubCommand(RSubCommandData rSubCommandData){
        this.subCommandDataMap.put(rSubCommandData.getUnderCommands().toString(), rSubCommandData);
    }
}
