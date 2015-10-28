package com.dnlegge;

public class DavidsStringCalculator implements StringCalculator {

    public static final String LIST_OF_DEFAULT_DELIMITERS = ",\\n";

    @Override
    public int add(String numbers) {
        //shortcut if empty - doesn't currently handle null gracefully
        if (numbers.isEmpty()) {
            return 0;
        }

        final String[] split = splitStringWithDefaultAndAnyExtraDelimiters(numbers);

        return iterateThroughArrayOfNumbers(split);
    }

    private String[] splitStringWithDefaultAndAnyExtraDelimiters(String numbers) {
        String possibleDelimiters = getListOfDelimiters(numbers);

        String preprocessedNumbers = getStringWithoutListOfExtraDelimitersIfIncluded(numbers, numbers);

        return splitStringWithGivenDelimiters(preprocessedNumbers, possibleDelimiters);
    }

    private String getStringWithoutListOfExtraDelimitersIfIncluded(String numbers, String preprocessedNumbers) {
        if (doesInputIncludeExtraDelimiters(numbers)) {
            preprocessedNumbers = numbers.substring(getIndexOfFirstLineBreak(numbers));
        }
        return preprocessedNumbers;
    }

    private String getListOfDelimiters(String numbers) {
        String possibleDelimiters = LIST_OF_DEFAULT_DELIMITERS;
        if (doesInputIncludeExtraDelimiters(numbers)) {
            final int indexOfFirstLineBreak = getIndexOfFirstLineBreak(numbers);
            possibleDelimiters = numbers.substring(2, indexOfFirstLineBreak);
        }
        return possibleDelimiters;
    }

    private boolean doesInputIncludeExtraDelimiters(String numbers) {
        return numbers.startsWith("//");
    }

    private String[] splitStringWithGivenDelimiters(String preprocessedNumbers, String possibleDelimiters) {
        return preprocessedNumbers.split("[" + possibleDelimiters + "]");
    }

    private int getIndexOfFirstLineBreak(String numbers) {
        return numbers.indexOf("\n");
    }

    private int iterateThroughArrayOfNumbers(String[] split) {
        int total = 0;
        for (String number : split) {
            final int intValue = extractIntValue(number);
            validateIntValue(intValue);
            total += intValue;
        }
        return total;
    }

    private void validateIntValue(int intValue) {
        if (intValue < 0) {
            //wouldn't usually use raw Runtime exception
            throw new RuntimeException("negatives not allowed");
        }
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
