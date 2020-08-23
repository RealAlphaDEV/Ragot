package ua.realalpha.ragot.command.entity;

import java.util.Optional;

public interface RArgument {

    Optional<String> asString(int i);

    Optional<Long> asLong(int i);

    Optional<Integer> asInt(int i);

    Optional<Float> asFloat(int i);

    Optional<Double> asDouble(int i);

    Optional<Byte> asByte(int i);

    Optional<Boolean> asBoolean(int i);

}
