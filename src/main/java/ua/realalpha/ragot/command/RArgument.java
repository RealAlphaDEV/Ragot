package ua.realalpha.ragot.command;

import org.bukkit.entity.Player;

public interface RArgument {

    String getStringArgument(String s);

    String getStringArgument(int i);

    long getLongArgument(String s);

    long getLongArgument(int i);

    int getIntArgument(String s);

    int getIntArgument(int i);

    float getFloatArgument(String s);

    float getFloatArgument(int i);

    double getDoubleArgument(String s);

    double getDoubleArgument(int i);

    char getCharArgument(String s);

    char getCharArgument(int i);

    byte getByteArgument(String s);

    byte getByteArgument(int i);

    boolean getBooleanArgument(String s);

    boolean getBooleanArgument(int i);

    Player getPlayerArgument(String s);

    Player getPlayerArgument(int i);

}
