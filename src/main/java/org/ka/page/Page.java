package org.ka.page;

public class Page implements Comparable<Page> {
    public final long address;
    public long startOffset;

    public Page(long address) {
        this.address = address;
        this.startOffset = 0;
    }

    @Override
    public int compareTo(Page o) {
        int r = Long.compare(this.startOffset, o.startOffset);
        if (r != 0L) return r;
        return Long.compare(this.address, o.address);
    }
}
