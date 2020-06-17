package ua.realalpha.ragot.command;

import ua.realalpha.ragot.command.data.RCommandData;

import java.util.*;
import java.util.stream.Collectors;


public class RCommandFetcher {

    private final RCommandData rCommandData;

    public RCommandFetcher(RCommandData rCommandData) {
        this.rCommandData = rCommandData;
    }

    public RCommandData getRCommand(List<RCommandData> list, List<String> args) {
        final Map<RCommandData, Integer> rCommandDataIntegerMap = new HashMap<>();
        list.forEach(rCommandData -> rCommandDataIntegerMap.put(rCommandData, getMatchLevel(rCommandData, args)));

        LinkedHashMap<RCommandData, Integer> collect = rCommandDataIntegerMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        Iterator<Map.Entry<RCommandData, Integer>> iterator = collect.entrySet().iterator();
        if (iterator.hasNext()){
            final Map.Entry<RCommandData, Integer> next = iterator.next();
            final List<String> command = new ArrayList<>(next.getKey().getCommand());
            command.remove(0);
            final List<String> commonElement = findCommonElement(command, args);
            if (!next.getKey().getArgs().isEmpty()){
                if ((commonElement.size() + next.getKey().getArgs().size()) == args.size()) return next.getKey();
            } else {
                if (!commonElement.isEmpty()) return next.getKey();
            }
        }

        return rCommandData;
    }


    private int getMatchLevel(RCommandData rCommandData, List<String> args) {
        final List<String> command = new ArrayList<>(rCommandData.getCommand());
        command.remove(0);
        List<String> commonElement = findCommonElement(command, args);
        if (!commonElement.isEmpty()) {
            int argsRequired = commonElement.size() + rCommandData.getArgs().size();
            if (!rCommandData.getArgs().isEmpty()) {
                if (argsRequired == args.size()) return argsRequired;
                return commonElement.size();
            }
            return commonElement.size();
        }
        return 0;
    }

    private List<String> findCommonElement(List<String> a, List<String> b){
        List<String> list = new ArrayList<>(a);
        list.retainAll(b);
        return list;
    }
}
