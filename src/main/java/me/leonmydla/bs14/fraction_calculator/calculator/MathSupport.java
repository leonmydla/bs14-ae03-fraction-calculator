package me.leonmydla.bs14.fraction_calculator.calculator;

public class MathSupport {

    public static int findHighestCommonDivider(final int subject, final int object) {
        final int minDivider = 1;
        final int maxDivider;

        if (subject <= object) {
            maxDivider = subject;
        } else {
            maxDivider = object;
        }

        for (int divider = maxDivider; divider > minDivider; divider--) {
            if(isDividable(subject, divider) && isDividable(object, divider)) {
                return divider;
            }
        }

        return minDivider;
    }

    private static boolean isDividable(final int subject, final int divider) {
        return subject % divider == 0;
    }
}
