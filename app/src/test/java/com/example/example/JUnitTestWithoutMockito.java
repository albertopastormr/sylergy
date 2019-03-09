package com.example.example;

import com.example.example.TestClasses.JUnitTestClasses;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTestWithoutMockito {
    @Test
    public void highest_test(){
        Integer[] list = {1, 2, 3, 4};
        Integer expected = 4;
        Integer result = JUnitTestClasses.getMax(list);

        assertEquals(expected, result);
    }

    @Test
    public void hypotenuse_test(){
        Float cat1 = 5f;
        Float cat2 = 5f;
        Float expected = 50f;
        Float result = JUnitTestClasses.getHypotenuse(cat1, cat2);

        assertEquals(expected, result);
    }
}