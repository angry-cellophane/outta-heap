package org.ka.ref;

import org.ka.page.Page;

public class RefImpl<T> implements Ref<T> {
    private final long address;

    public RefImpl(long address) {
        this.address = address;
    }

    @Override
    public long address() {
        return address;
    }
}
