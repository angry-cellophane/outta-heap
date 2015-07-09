package org.ka.repo;

import org.junit.Test;

import static org.junit.Assert.*;

public class RepoImplTest {

    @Test public void testInit() {
        new RepoImpl(4L).clean();
    }

    @Test public void testIntegerSize() {
        RepoImpl repo = new RepoImpl(16L);
        Integer i = 4;
        assertEquals(16L, repo.sizeOf(i));
        repo.clean();
    }

}