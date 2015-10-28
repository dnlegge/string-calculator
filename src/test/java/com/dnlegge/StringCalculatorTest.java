package com.dnlegge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

    @Test
    public void addOneNumberTest() {

        final int add = davidsStringCalculator.add("1");

        assertEquals(1, add);

    }

    @Test
    public void addTwoNumbersTest() {

        final int add = davidsStringCalculator.add("1, 2");

        assertEquals(3, add);

    }

    @Test
    public void addThreeNumbersTest() {

        final int add = davidsStringCalculator.add("1, 2, 3");

        assertEquals(6, add);

    }

    @Test
    public void addThreeNumbersWithLineBreakTest() {

        final int add = davidsStringCalculator.add("1\n 2, 3");

        assertEquals(6, add);

    }

    @Test(expected = NumberFormatException.class)
    public void addThreeNumbersIllegalTest() {

        davidsStringCalculator.add("1,\n 2, 3");

        fail("Shouldn't have got here with incorrect input");

    }

    @Test
    public void addWithChangedDelimiterTest() {

        final int add = davidsStringCalculator.add("//;\n1;2");

        assertEquals(3, add);

    }

}
