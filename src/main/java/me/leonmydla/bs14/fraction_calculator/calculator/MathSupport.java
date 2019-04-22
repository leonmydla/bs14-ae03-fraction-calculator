package me.leonmydla.bs14.fraction_calculator.calculator;

public class MathSupport {

    public static int findLowestCommonMultiple(final int subject, final int object) {
        int s;
        int t = 1;

        for (int x = 1; ; x++) {
            s = subject * x;

            for (int y = 1; t < s; y++) {
                t = object * y;
            }

            if (s == t) {
                break;
            }
        }

        return s;
    }

    public static int findLowestCommonDivider(final int subject, final int object) {
        int divider = subject;

        if (subject < object) {
            divider = object;
        }

        while (true) {
            if (subject % divider == 0 && object % divider == 0) {
                return divider;
            }

            if (divider == 0) {
                return 0;
            }

            divider--;
        }
    }
}
