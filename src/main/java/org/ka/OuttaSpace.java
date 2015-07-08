package org.ka;

import org.ka.ref.Ref;

public interface OuttaSpace {
    <T> Ref<T> put(T o);
    <T> Ref<Iterable<T>> putList(Iterable<T> o);
    <T> T get(Ref<T> ref);
}
