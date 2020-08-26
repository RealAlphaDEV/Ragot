package ua.realalpha.ragot.command;

import ua.realalpha.ragot.command.handler.RCommandHandler;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class RMockCommandHandler implements RCommandHandler {

    private final String[] command;
    private final String[] aliases;
    private final String permission;
    private final String description;
    private final String usage;


    public RMockCommandHandler(String[] command) {
        this(command, new String[0], "", "", "");
    }

    public RMockCommandHandler(String[] command, String[] aliases, String permission, String description, String usage) {
        this.command = command;
        this.aliases = aliases;
        this.permission = permission;
        this.description = description;
        this.usage = usage;
    }

    @Override
    public String[] command() {
        return this.command;
    }

    @Override
    public String permission() {
        return this.permission;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String usage() {
        return this.usage;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return RCommandHandler.class;
    }

    @Override
    public String toString() {
        return this.getClass()+"@"+this.hashCode()+String.format("(command=%s, permission=%s, description=%s, usage=%s, aliases=%s)", Arrays.toString(this.command), this.permission, this.description, this.usage, Arrays.toString(this.aliases));
    }
}
