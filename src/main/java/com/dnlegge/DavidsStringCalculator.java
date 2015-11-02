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
            preprocessedNumbers = numbers.substring(getIndexOfFirstLineBreak(numbers) + 1);
        }
        return preprocessedNumbers;
    }

    private String getListOfDelimiters(String numbers) {
        if (doesInputIncludeExtraDelimiters(numbers)) {
            return getStringOfPossibleDelimiters(numbers);
        }
        return "[" + LIST_OF_DEFAULT_DELIMITERS + "]";
    }

    private String getStringOfPossibleDelimiters(String numbers) {
        String firstLine = getFirstLine(numbers);
        if (!hasSquareBrackets(firstLine)) {
            //Append extra delimiters to default ones.
            //This isn't specified in the spec, but I feel would be expected
            //Can be removed if incorrect
            return "[" + LIST_OF_DEFAULT_DELIMITERS + escapeSpecialRegexChars(firstLine) + "]";
        }
        return handleArbitraryLengthDelimiters(firstLine);
    }

    private boolean hasSquareBrackets(String firstLine) {
        return firstLine.contains("[") && firstLine.contains("]");
    }

    private String handleArbitraryLengthDelimiters(String firstLine) {
        final String[] delimiters = firstLine.split("[\\[]");
        final StringBuilder toReturn = new StringBuilder();
        for (String split : delimiters) {
            if (!split.isEmpty()) {
                appendOrIfNotFirstClause(toReturn);
                toReturn.append("(");
                toReturn.append(getCleanStringForRegex(split));
                toReturn.append(")");

            }
        }
        return toReturn.toString();
    }

    private String getCleanStringForRegex(String split) {
        return escapeSpecialRegexChars(removeTrailingSquareBracket(split));
    }

    private void appendOrIfNotFirstClause(StringBuilder toReturn) {
        if (toReturn.length() != 0) {
            toReturn.append("|");
        }
    }

    private String removeTrailingSquareBracket(String split) {
        return split.substring(0, split.indexOf("]"));
    }

    private String escapeSpecialRegexChars(String str) {
        return str.replaceAll("\\*", "\\\\*");
    }

    private String getFirstLine(String numbers) {
        final int indexOfFirstLineBreak = getIndexOfFirstLineBreak(numbers);
        return numbers.substring(2, indexOfFirstLineBreak);
    }

    private boolean doesInputIncludeExtraDelimiters(String numbers) {
        return numbers.startsWith("//");
    }

    private String[] splitStringWithGivenDelimiters(String preprocessedNumbers, String possibleDelimiters) {
        return preprocessedNumbers.split(possibleDelimiters);
    }

    private int getIndexOfFirstLineBreak(String numbers) {
        return numbers.indexOf("\n");
    }

    private int iterateThroughArrayOfNumbers(String[] split) {
        int total = 0;
        StringBuilder errorString = new StringBuilder();
        for (String number : split) {
            final int intValue = extractIntValueIf1000OrLess(number);
            errorString.append(validateIntValue(intValue));
            total += intValue;
        }
        if (errorString.length() == 0) {
            return total;
        }
        throw new RuntimeException("negatives not allowed:" + errorString.toString());
    }

    private String validateIntValue(int intValue) {
        if (intValue < 0) {
            //wouldn't usually use raw Runtime exception
            return " " + Integer.toString(intValue);
        }
        return "";
    }

    private int extractIntValueIf1000OrLess(String number) {
        final int intValue = parseNumber(sanitiseInput(number));
        if (intValue > 1000) {
            return 0;
        }
        return intValue;
    }

    private String sanitiseInput(String number) {
        return number.trim();
    }

    private int parseNumber(String number) {
        return Integer.parseInt(number);
    }
}
