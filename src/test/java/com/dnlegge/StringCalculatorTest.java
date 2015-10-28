package com.dnlegge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StringCalculatorTest {

    @Test
    public void addTest() {

        final StringCalculator davidsStringCalculator = new DavidsStringCalculator();

        final int add = davidsStringCalculator.add("1, 2");

        assertEquals(0, add);

        fail("start with failing test to know its running");
    }

}
