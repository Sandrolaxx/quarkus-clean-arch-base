package com.aktie.domain.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author SRamos
 */
@SuppressWarnings("all")
public class ListUtil {

    public synchronized static <T> T first(Collection<T> list) {
        if (isNotNullOrEmpty(list)) {
            return list.iterator().next();
        }

        return null;
    }

    public synchronized static <T> T last(Collection<T> list) {
        T item = null;

        if (isNotNullOrEmpty(list)) {
            Iterator<T> iterator = list.iterator();

            while (iterator.hasNext()) {
                item = iterator.next();
            }
        }

        return item;
    }

    public synchronized static boolean isNotNullOrEmpty(Collection<?> list) {
        return !isNullOrEmpty(list);
    }

    public synchronized static boolean isNotNullOrEmpty(Object[] array) {
        return !isNullOrEmpty(array);
    }

    public synchronized static boolean isNullOrEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public synchronized static boolean isNullOrEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public synchronized static <T> List<T> toList(T... values) {
        return Arrays.asList(values);
    }

    public static <T> Stream<T> stream(Collection<T> list) {
        return isNotNullOrEmpty(list) ? Collections.unmodifiableList(new ArrayList<>(list)).stream()
                : new ArrayList<T>().stream();
    }

    public static <T> Optional<Stream<T>> optionalStream(Collection<T> list) {
        return Optional.of(isNotNullOrEmpty(list) ? Collections.unmodifiableList(new ArrayList<>(list)).stream()
                : null);
    }

}
