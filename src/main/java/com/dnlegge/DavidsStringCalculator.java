package com.dnlegge;

public class DavidsStringCalculator implements StringCalculator {

    public static final String LIST_OF_TOKENS_TO_SPLIT_ON_REGEX = ",\\n";

    @Override
    public int add(String numbers) {
        //shortcut if empty - doesn't currently handle null gracefully
        if (numbers.isEmpty()) {
            return 0;
        }

        String preprocessedNumbers = numbers;

        String possibleDelimiters = LIST_OF_TOKENS_TO_SPLIT_ON_REGEX;
        if (numbers.startsWith("//")) {
            final int indexOfFirstLineBreak = numbers.indexOf("\n");
            possibleDelimiters = numbers.substring(2, indexOfFirstLineBreak);
            preprocessedNumbers = numbers.substring(indexOfFirstLineBreak);
        }

        possibleDelimiters = "[" + possibleDelimiters + "]";

        return handleArbitraryListOfNumbers(preprocessedNumbers, possibleDelimiters);
    }

    private int handleArbitraryListOfNumbers(String numbers, String possibleDelimiters) {
        final String[] split = numbers.split(possibleDelimiters);

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
