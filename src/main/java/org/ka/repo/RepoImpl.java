package org.ka.repo;


import org.github.jamm.MemoryMeter;
import org.ka.ForTesting;
import org.ka.page.Page;
import org.ka.ref.Ref;
import org.ka.ref.RefImpl;
import sun.instrument.InstrumentationImpl;
import sun.misc.Unsafe;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public final class RepoImpl implements Repo {

    private final Unsafe U;
    private final long bufferSize;
    private final MemoryMeter mm;

    private final TreeSet<Page> pages;

    RepoImpl(long bufferSize) {
        U = unsafe();

        this.mm = new MemoryMeter();
        this.bufferSize = bufferSize;
        this.pages = new TreeSet<>();
    }

    private Unsafe unsafe() {
        Unsafe U;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            U = (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return U;
    }

    public <T> Ref<T> put(T o) {
        long size = sizeOf(o);
        if (size > bufferSize) {
            throw new RuntimeException("The object's size is bigger then the page size. Please increase the page size");
        }

        Page page = null;
        for (Page p : pages) {
            if (bufferSize - p.startOffset >= size) {
                page = p;
                break;
            }
        }

        if (page == null) {
            page = new Page(U.allocateMemory(bufferSize));
            pages.add(page);
        }

        return put0(o, size, page);
    }

    private <T> Ref<T> put0(T o, long objectSize, Page p) {
        U.copyMemory(o, 0, null, p.address + p.startOffset, objectSize);
        RefImpl<T> ref = new RefImpl<>(p.startOffset, objectSize, p);
        p.startOffset += objectSize + 1;
        return ref;
    }

    @Override
    public <T> Ref<Iterable<T>> putList(Iterable<T> o) {
        return null;
    }

    public <T> T get(Ref<T> ref) {
        return null;
    }

    @Override public void clean() {
        for (Page p : pages) {
            U.freeMemory(p.address);
        }
    }

    @ForTesting long sizeOf(Object o) {
        return mm.measureDeep(o);
    }
}