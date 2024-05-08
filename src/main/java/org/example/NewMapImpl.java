package org.example;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NewMapImpl<K, V> extends HashMap<K, V> implements NewMap<K, V> {

    public static void main(String[] args) {

    }

    //Метод находит значение по ключу, если оно имеется, Consumer принимает на вход это значение
    @Override
    public void ifPresent(Object key, Consumer<? super V> action) {
        V value = this.get(key);
        if (value != null) {
            action.accept(value);
        }
    }

    //Метод возвращает значение по ключу, если оно имеется, иначе возвращает значение из аргумента метода
    @Override
    public V orElse(Object key, V other) {
        V value = this.get(key);
        return value != null ? value : other;
    }

    //Метод возвращает значение по ключу, если оно имеется, иначе возвращает исключение, которое будет указано в Supplier
    @Override
    public <X extends Throwable> V orElseThrow(Object key, Supplier<? extends X> exceptionSupplier) throws X {
        V value = this.get(key);
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }
}