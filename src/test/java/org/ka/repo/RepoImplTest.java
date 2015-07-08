package org.ka.repo;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepoImplTest {

    @Test public void testInit() {
        new RepoImpl(4L);
    }

    @Test public void testObjectSize() {
        Integer i = 4;
        assertNotEquals(new RepoImpl(4L).sizeOf(i), 0);
    }

}