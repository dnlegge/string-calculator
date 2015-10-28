package com.dnlegge;

public class DavidsStringCalculator implements StringCalculator {

    @Override
    public int add(String numbers) {
        if (!numbers.isEmpty()) {
            return parseNumbers(numbers);
        }
        return 0;
    }

    private int parseNumbers(String numbers) {
        return Integer.parseInt(numbers);
    }
}
