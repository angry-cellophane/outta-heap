package org.ka.repo;

import org.junit.Assert;
import org.junit.Test;
import org.ka.ref.Ref;

import static org.junit.Assert.*;

public class RepoImplTest {

    @Test public void testInit() {
        new RepoImpl(4L);
    }

    @Test public void testObjectSize() {
        Integer i = 4;
        assertNotEquals(new RepoImpl(4L).sizeOf(i), 0);
    }

    @Test public void testPutAndGetInteger() {
        RepoImpl repo = new RepoImpl(32);
        Integer i = 10;
        Ref<Integer> ref = repo.put(i);
        Integer j = repo.get(ref);
        assertEquals(i,j);
        repo.clean();
    }

}