package com.test.baseproject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() throws Exception {
        com.test.baseproject.test.Test test = new com.test.baseproject.test.Test();
        assertEquals(1, test.dos());
    }
}