package org.example;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface NewMap<K, V> extends Map<K, V> {
    void ifPresent(Object key, Consumer<? super V> action);

    V orElse(Object key, V other);

    <X extends Throwable> V orElseThrow(Object key, Supplier<? extends X> exceptionSupplier) throws X;

}
