package com.dnlegge;

public class DavidsStringCalculator implements StringCalculator {

    public static final String LIST_OF_TOKENS_TO_SPLIT_ON_REGEX = "[,\\n]";

    @Override
    public int add(String numbers) {
        //shortcut if empty - doesn't currently handle null gracefully
        if (numbers.isEmpty()) {
            return 0;
        }

        return handleArbitraryListOfNumbers(numbers);
    }

    private int handleArbitraryListOfNumbers(String numbers) {
        final String[] split = numbers.split(LIST_OF_TOKENS_TO_SPLIT_ON_REGEX);

        return iterateThroughArrayOfNumbers(split);
    }

    private int iterateThroughArrayOfNumbers(String[] split) {
        int total = 0;
        for (String number : split) {
            total += extractIntValue(number);
        }
        return total;
    }

    private int extractIntValue(String number) {
        return parseNumber(sanitiseInput(number));
    }

    private String sanitiseInput(String number) {
        return number.trim();
    }

    private int parseNumber(String number) {
        return Integer.parseInt(number);
    }
}
