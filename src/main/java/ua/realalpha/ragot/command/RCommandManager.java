package ua.realalpha.ragot.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import ua.realalpha.ragot.command.annotation.RCommand;
import ua.realalpha.ragot.command.craft.CraftRCommandRequest;
import ua.realalpha.ragot.command.data.RCommandData;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RCommandManager {

    private final List<RCommandData> rCommandDataList = new ArrayList<>();

    public void registerListener(RCommandListener rCommandListener){
        this.getCommandsByAnnotation(rCommandListener, RCommand.class).forEach(method -> {
            RCommand rCommand = method.getAnnotation(RCommand.class);
            RCommandData rCommandData = new RCommandData(rCommand, method, rCommandListener);
            this.rCommandDataList.add(rCommandData);
            //if (rCommandData.getCommand().size() == 1) this.registerCommands(rCommandData);
        });
    }


    private List<Method> getCommandsByAnnotation(RCommandListener rCommandListener, Class<? extends Annotation> annotation){
        final List<Method> list = new ArrayList<>();
        for (Method method : rCommandListener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)){
                if (method.getParameterTypes().length == 1 && method.getParameterTypes()[0].equals(RCommandRequest.class)){
                    list.add(method);
                }
            }
        }
        return list;
    }

    private void registerCommands(RCommandData rCommandData) {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getServer());
            commandMap.register(rCommandData.getCommand().get(0), new RCommandHandler(rCommandData, this));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private List<RCommandData> getSubCommandOfCommand(String command){
        final List<RCommandData> list = new ArrayList<>();
        for (RCommandData rCommandData : this.rCommandDataList) {
            if (rCommandData.getCommand().size() != 1 && rCommandData.getCommand().get(0).equalsIgnoreCase(command)) list.add(rCommandData);
        }
        return list;
    }

    private RCommandData getRCommandData(String command){
        for (RCommandData rCommandData : this.rCommandDataList) {
            if (rCommandData.getCommand().get(0).equalsIgnoreCase(command)) return rCommandData;
        }
        throw new NullPointerException();
    }

    public void handleRCommand(CommandSender commandSender, String command, String[] args){
        try {
            RCommandData rCommandData = this.getRCommandData(command);
            RCommandFetcher rCommandFetcher = new RCommandFetcher(rCommandData);
            RCommandData rCommand = rCommandFetcher.getRCommand(this.getSubCommandOfCommand(command), Arrays.asList(args));
            rCommand.getMethod().invoke(rCommand.getRCommandListener(), new CraftRCommandRequest(commandSender));
        }catch (NullPointerException | IllegalAccessException | InvocationTargetException ignored){

        }
    }

}
