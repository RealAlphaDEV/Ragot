package ua.realalpha.ragot.command.craft;

import ua.realalpha.ragot.command.entity.RTweakArgument;

import java.util.Optional;

public class CraftRTweakArgument implements RTweakArgument {

    private final String[] args;
    public CraftRTweakArgument(String[] args) {
        this.args = args;
    }

    @Override
    public Optional<String> asString(int i) {
        try {
            return Optional.of(args[i]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> asLong(int i) {
        try {
            return Optional.of(Long.parseLong(args[i]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> asInt(int i) {
        try {
           return Optional.of(Integer.valueOf(args[i]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Float> asFloat(int i) {
        try {
            return Optional.of(Float.valueOf(args[i]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Double> asDouble(int i) {
        try {
            return Optional.of(Double.valueOf(args[i]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Byte> asByte(int i) {
        try {
            return Optional.of(Byte.valueOf(args[i]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> asBoolean(int i) {
        try {
            return Optional.of(Boolean.valueOf(args[i]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
