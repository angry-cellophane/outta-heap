package org.ka.repo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.ka.ref.Ref;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class RepoImplPrimitivesTest<T> {

    @Parameters public static Collection<Object[]> parameters() {
        return Arrays.asList(
            new Object[][]{
                    {1, 2},
                    {1L, 2L},
                    {1.0f, 2.0f},
                    {1.0d, 2.0d},
                    {'c','d'},
                    {"hello","world"},
                    {1,"world"},
                    {'c',"world"},
                    {'c',2.0d},
            }
        );
    }

    @Parameter public T object1;
    @Parameter(value = 1) public T object2;

    private RepoImpl repo;

    @Before public void setup() {
        repo = new RepoImpl(64L);
    }

    @After public void teardown() {
        if (repo != null) repo.clean();
    }

    @Test public void testPutGet() {
        Ref<T> ref = repo.put(object1);
        assertEquals(object1,repo.get(ref));
    }

    @Test public void testPutPutGetGet() {
        Ref<T> ref1 = repo.put(object1);
        Ref<T> ref2 = repo.put(object2);

        assertEquals(object1, repo.get(ref1));
        assertEquals(object2, repo.get(ref2));
    }

    @Test public void testPutGetPutGet() {
        Ref<T> ref1 = repo.put(object1);
        assertEquals(object1, repo.get(ref1));

        Ref<T> ref2 = repo.put(object2);
        assertEquals(object2, repo.get(ref2));
    }


}