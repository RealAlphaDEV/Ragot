package ua.realalpha.ragot.command;

import ua.realalpha.ragot.command.annotation.RCommand;
import ua.realalpha.ragot.command.annotation.RSubCommand;
import ua.realalpha.ragot.command.data.RCommandData;
import ua.realalpha.ragot.command.data.RSubCommandData;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RCommandManager {

    private final Map<String, RCommandData> rCommandDataMap = new HashMap<>();

    public void registerListener(RCommandListener rCommandListener){
        this.getCommandsByAnnotation(rCommandListener, RCommand.class).forEach(method -> {
            RCommand rCommand = method.getAnnotation(RCommand.class);
            if (!this.rCommandDataMap.containsKey(rCommand.command())) this.rCommandDataMap.put(rCommand.command(), new RCommandData(rCommand.command()).setUp(rCommand, method, rCommandListener));
            else this.rCommandDataMap.get(rCommand.command()).setUp(rCommand, method, rCommandListener);
        });

        this.getCommandsByAnnotation(rCommandListener, RSubCommand.class).forEach(method -> {
            RSubCommand rSubCommand = method.getAnnotation(RSubCommand.class);
            if (!this.rCommandDataMap.containsKey(rSubCommand.command())) this.rCommandDataMap.put(rSubCommand.command(), new RCommandData(rSubCommand.command()));
            this.rCommandDataMap.get(rSubCommand.command()).addSubCommand(new RSubCommandData(rSubCommand, method, rCommandListener));
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

    private void callCommand(String command, String[] args){
        if (!this.rCommandDataMap.containsKey(command)) return;
        this.rCommandDataMap.get(command);
    }

}
