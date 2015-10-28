package com.dnlegge;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

    private StringCalculator davidsStringCalculator;

    @Before
    public void setUp() {
        davidsStringCalculator = new DavidsStringCalculator();
    }

    @Test
    public void addEmptyStringTest() {

        final int add = davidsStringCalculator.add("");

        assertEquals(0, add);

    }

}
