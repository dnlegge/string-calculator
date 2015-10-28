package com.dnlegge;

public class DavidsStringCalculator implements StringCalculator {

    @Override
    public int add(String numbers) {
        if (!numbers.isEmpty()) {
            final String[] split = numbers.split(",");
            int total = 0;
            for (String number : split) {
                total += parseNumbers(number.trim());
            }
            return total;
        }
        return 0;
    }

    private int parseNumbers(String numbers) {
        return Integer.parseInt(numbers);
    }
}
