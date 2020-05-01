package ua.realalpha.ragot.item;


import java.io.Serializable;
import java.util.function.Consumer;

@FunctionalInterface
public interface RConsumer<T> extends Consumer<T>, Serializable {
    void accept(T t);
}

