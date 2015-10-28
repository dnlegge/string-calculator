package com.dnlegge;

public class DavidsStringCalculator implements StringCalculator {

    @Override
    public int add(String numbers) {
        if (!numbers.isEmpty()) {
            return Integer.parseInt(numbers);
        }
        return 0;
    }
}
