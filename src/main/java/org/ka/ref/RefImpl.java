package org.ka.ref;

import org.ka.page.Page;

public class RefImpl<T> implements Ref<T> {
    private final long offset;
    private final long size;
    private final Page page;

    public RefImpl(long offset, long size, Page page) {
        this.offset = offset;
        this.size = size;
        this.page = page;
    }
}
