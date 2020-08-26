package ua.realalpha.ragot.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import ua.realalpha.ragot.command.craft.CraftRCommandAction;
import ua.realalpha.ragot.command.craft.CraftRCommandResult;
import ua.realalpha.ragot.command.entity.RCommand;
import ua.realalpha.ragot.command.handler.RCommandHandler;
import ua.realalpha.ragot.command.handler.RCommandRegister;
import ua.realalpha.ragot.command.handler.RCommandRegisters;
import ua.realalpha.ragot.command.handler.RPermissionHandler;
import ua.realalpha.ragot.parameter.CraftRParameterManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class RCommandManager {

    private final Set<RCommandListener> rCommandListeners = new HashSet<>();
    private final CraftRParameterManager craftRParameterManager;

    public RCommandManager(CraftRParameterManager craftRParameterManager) {
        this.craftRParameterManager = craftRParameterManager;
    }

    /**
     *
     * @param rCommandsListener
     */
    public void registerRCommandListener(RCommandListener rCommandsListener) {
        this.rCommandListeners.add(rCommandsListener);
        Class<? extends RCommandListener> aClass = rCommandsListener.getClass();
        if (aClass.isAnnotationPresent(RCommandRegisters.class)) {
            for (RCommandRegister rCommandRegister : aClass.getAnnotation(RCommandRegisters.class).value()) {
               // if (rCommandRegister.command().length() != 0) registerCommand(new CraftRCommand(Collections.singletonList(rCommandRegister.command()), Collections.singletonList(rCommandRegister.command()), "", rCommandRegister.usage(), rCommandRegister.description()));
            }
        }
    }

    /**
     *
     * @param rCommand
     */
    private void registerCommand(RCommand rCommand) {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getServer());
            commandMap.register(rCommand.getCommand().get(0), new RCommandController(rCommand, this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param commandSender
     * @param command
     * @param args
     */
    public void handleRCommand(CommandSender commandSender, String command, String[] args) {
        final Set<RCommand> set = new HashSet<>();
        Optional<CraftRCommandAction> optionalCraftRCommandAction = this.getRCommand(command, args, set);
        optionalCraftRCommandAction.ifPresent(craftRCommandAction -> {
            final CraftRCommandResult craftRCommandResult = new CraftRCommandResult(commandSender, args, craftRCommandAction.getCommand().size() - 1, tweakRCommands(craftRCommandAction, set));
            try {
                Optional<CraftRCommandAction> rPermission = this.getRPermission(craftRCommandAction);
                boolean accept = true;
                if (rPermission.isPresent()) {
                    CraftRCommandAction craftRPermissionAction = rPermission.get();
                    accept = ((boolean) this.craftRParameterManager.tweakMethod(craftRPermissionAction.getRCommandListener(), craftRPermissionAction.getMethod(), craftRCommandResult));
                }
                if (accept) this.craftRParameterManager.tweakMethod(craftRCommandAction.getRCommandListener(), craftRCommandAction.getMethod(), craftRCommandResult);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     *
     * @param command
     * @param args
     * @param rCommands
     * @return
     */
    private Optional<CraftRCommandAction> getRCommand(String command, String[] args, Set<RCommand> rCommands) {
        final Map<RCommandHandler, CraftRCommandAction> rCommandActionMap = new HashMap<>();
        Optional<RCommandRegister> rCommandRegister = Optional.empty();
        for (RCommandListener rCommandListener : this.rCommandListeners) {
            rCommandRegister = getRCommandRegister(command, rCommandListener.getClass());
            for (Method method : getMethodWithAnnotation(rCommandListener.getClass(), RCommandHandler.class)) {
                RCommandHandler rCommandHandler = method.getAnnotation(RCommandHandler.class);
                if (rCommandHandler.command().length >= 1 && rCommandHandler.command()[0].equalsIgnoreCase(command))
                    rCommandActionMap.put(rCommandHandler, new CraftRCommandAction(rCommandHandler, rCommandListener, method));
            }
        }
        if (rCommandRegister.isPresent()) {
            final RCommandFetcher rCommandFetcher = new RCommandFetcher(rCommandRegister.get(), rCommandActionMap.keySet());
            final List<String> commandList = new ArrayList<>();
            commandList.add(rCommandRegister.get().command());
            commandList.addAll(Arrays.asList(args));
            final Map<String, CraftRCommandAction> commandActionMap = new HashMap<>();
            for (Map.Entry<RCommandHandler, CraftRCommandAction> entry : rCommandActionMap.entrySet()) {
                for (String[] strings : rCommandFetcher.getCommand(entry.getKey())) {
                    commandActionMap.put(Arrays.toString(strings), entry.getValue());
                }
            }
            for (int i = args.length + 1; i >= 1; i--) {
                List<String> list = commandList.subList(0, i);
                if (commandActionMap.containsKey(list.toString())) {
                    rCommands.addAll(rCommandActionMap.values());
                    return Optional.of(commandActionMap.get(list.toString()));
                }
            }
        }
        return Optional.empty();
    }

    /**
     *
     * @param rCommand
     * @return
     */
    private Optional<CraftRCommandAction> getRPermission(RCommand rCommand){
        if (!rCommand.getPermission().isEmpty()) {
            for (RCommandListener rCommandListener : this.rCommandListeners) {
                for (Method method : this.getMethodWithAnnotation(rCommandListener.getClass(), RPermissionHandler.class)) {
                    if (method.getReturnType() == Boolean.class || method.getReturnType() == boolean.class) {
                        RPermissionHandler rPermissionHandler = method.getAnnotation(RPermissionHandler.class);
                        if (rPermissionHandler.value().equalsIgnoreCase(rCommand.getPermission()))
                            return Optional.of(new CraftRCommandAction(rCommandListener, method));
                    }
                }
            }
        }
        return Optional.empty();
    }

    /**
     *
     * @param command
     * @param aClass
     * @return
     */
    private Optional<RCommandRegister> getRCommandRegister(String command, Class<? extends RCommandListener> aClass) {
        if (aClass.isAnnotationPresent(RCommandRegisters.class)) {
            for (RCommandRegister rCommandRegister : aClass.getAnnotation(RCommandRegisters.class).value()) {
                List<String> commands = new ArrayList<>();
                commands.add(rCommandRegister.command());
                commands.addAll(Arrays.asList(rCommandRegister.aliases()));
                for (String s : commands) {
                    if (s.equalsIgnoreCase(command)) return Optional.of(rCommandRegister);
                }
            }
        }
        return Optional.empty();
    }

    /**
     *
     * @param rCommand
     * @param rCommands
     * @return
     */
    private List<RCommand> tweakRCommands(RCommand rCommand, Set<RCommand> rCommands){
       final Map<RCommand, Integer> rCommandIntegerMap = new HashMap<>();
       final List<RCommand> rCommandList = new ArrayList<>();
        for (RCommand command : rCommands) {
            if (rCommand.getCommand().size() <= command.getCommand().size() && startWithRCommand(rCommand, command))
                rCommandIntegerMap.put(command, command.getCommand().size() - rCommand.getCommand().size()+4);
        }
        LinkedHashMap<RCommand, Integer> sortedMap = new LinkedHashMap<>();
        rCommandIntegerMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        for (Map.Entry<RCommand, Integer> entry : sortedMap.entrySet()) {
            rCommandList.add(entry.getKey());
        }
        return rCommandList;
    }

    /**
     * 
     * @param a
     * @param b
     * @return
     */
    private boolean startWithRCommand(RCommand a, RCommand b){
        int match = 0;
        for (int i = 0; i < a.getCommand().size(); i++) {
            if (a.getCommand().get(i).equalsIgnoreCase(b.getCommand().get(i))) match++;
        }
        return match == a.getCommand().size();
    }

    private  Set<Method> getMethodWithAnnotation(Class<? extends RCommandListener> aClass, Class<? extends Annotation> annotation){
        final Set<Method> methods = new HashSet<>();
        for (Method method : aClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                methods.add(method);
            }
        }
        return methods;
    }

}
