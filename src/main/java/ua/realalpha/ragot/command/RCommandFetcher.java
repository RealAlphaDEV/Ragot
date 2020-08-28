package ua.realalpha.ragot.command;


import ua.realalpha.ragot.command.handler.RCommandHandler;
import ua.realalpha.ragot.command.handler.RCommandRegister;

import java.util.*;

public class RCommandFetcher {

    private final Set<RCommandHandler> rCommandHandlers;
    private final RCommandRegister rCommandRegister;
    public RCommandFetcher(RCommandRegister rCommandRegister, Set<RCommandHandler> rCommandHandlers) {
        this.rCommandHandlers = rCommandHandlers;
        this.rCommandRegister = rCommandRegister;
    }

    public Set<String[]> getCommand(RCommandHandler rCommandHandler) {
        final Set<String[]> listMatch = new HashSet<>();
        final Set<String[]> subCommands = new HashSet<>();
        final List<String> command = new ArrayList<>(Arrays.asList(rCommandHandler.aliases()));
        command.add((rCommandHandler.command().length == 1 ? rCommandHandler.command()[0] : rCommandHandler.command()[rCommandHandler.command().length - 1]));

        if (rCommandHandler.command().length == 1) {
            for (String s : command) {
                listMatch.add(new String[]{s});
            }
            return listMatch;
        }

        final List<String> subCommand = Arrays.asList(rCommandHandler.command()).subList(1, rCommandHandler.command().length - 1);
        final Optional<RCommandHandler> optionalRCommandHandler = this.getByListCommand(subCommand);
        if (optionalRCommandHandler.isPresent()) {
            subCommands.addAll(new RCommandFetcher(rCommandRegister, rCommandHandlers).getCommand(optionalRCommandHandler.get()));
        } else if (subCommand.size() > 1) {
            subCommands.addAll(new RCommandFetcher(rCommandRegister, rCommandHandlers).getCommand(new RMockCommandHandler(subCommand.toArray(new String[0]))));
        }

        for (String[] list : subCommands) {
            for (String alias : command) {
                List<String> strings = new ArrayList<>(Arrays.asList(list));
                strings.add(alias);
                listMatch.add(strings.toArray(new String[0]));
            }
        }
        return listMatch;
    }

    private Optional<RCommandHandler> getByListCommand(List<String> command){
        if (command.size() == 0) return Optional.of(new RMockCommandHandler(new String[]{rCommandRegister.command()}, rCommandRegister.aliases(), "", rCommandRegister.description(), rCommandRegister.usage()));
        List<String> strings = new ArrayList<>();
        strings.add(rCommandRegister.command());
        strings.addAll(command);
        for (RCommandHandler rCommandHandler : this.rCommandHandlers) {
            if (Arrays.asList(rCommandHandler.command()).toString().equalsIgnoreCase(strings.toString())) return Optional.of(rCommandHandler);
        }
        return Optional.empty();
    }






}
