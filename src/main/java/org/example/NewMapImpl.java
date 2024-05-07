package org.example;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NewMapImpl<K, V> extends HashMap<K, V> implements NewMap<K, V> {


    public static void main(String[] args) {
        NewMap<Integer, Integer> newMap = new NewMapImpl<>();
        newMap.put(1, 2);
        newMap.put(2, 2);
        newMap.put(3, 2);
        newMap.put(4, 2);
        newMap.ifPresent(4, System.out::println);
        newMap.ifPresent(0, System.out::println);
        newMap.ifPresent(10, System.out::println);
        System.out.println(newMap.orElse(0, 45));
        //System.out.println(newMap.orElseThrow(0));
    }


    @Override
    public void ifPresent(Object key, Consumer<? super V> action) {
        V value = this.get(key);
        if (value != null) {
            action.accept(value);
        }
    }

    @Override
    public V orElse(Object key, V other) {
        V value = this.get(key);
        return value != null ? value : other;
    }


    @Override
    public V orElseThrow(Object key) {
        V value = this.get(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

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