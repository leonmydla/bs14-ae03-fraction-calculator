package me.leonmydla.bs14.fraction_calculator.calculator;

import java.util.ArrayList;
import java.util.List;

public class Fraction {

    private int numerator;
    private int denominator;


    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    public Fraction(final int numerator, final int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public String toString() {
        final boolean shouldShowIntegralAmount = getIntegralAmount() != 0;
        final boolean shouldShowFraction       = getNumeratorWithoutIntegralAmount() != 0;

        if (!shouldShowIntegralAmount && !shouldShowFraction) {
            return "0";
        }

        List<String> strings = new ArrayList<>();

        if (shouldShowIntegralAmount) {
            String integralAmount = String.valueOf(getIntegralAmount());

            strings.add(integralAmount);
        }

        if (shouldShowFraction) {
            strings.add(getNumeratorWithoutIntegralAmount() + "/" + denominator);
        }

        return String.join(" ", strings);
    }

    void writeOut() {
        System.out.println(toString());
    }

    int getNumerator() {
        return numerator;
    }

    void setNumerator(final int numerator) {
        this.numerator = numerator;
    }

    int getNumeratorWithoutIntegralAmount() {
        return numerator % denominator;
    }

    int getDenominator() {
        return denominator;
    }

    void setDenominator(final int denominator) {
        this.denominator = denominator;
    }

    int getIntegralAmount() {
        return numerator / denominator;
    }

    void add(Fraction object) {
        sanitizeAndMakeCompatible(object);

        numerator += object.numerator;

        shrink();
    }

    void subtract(Fraction object) {
        sanitizeAndMakeCompatible(object);

        numerator -= object.numerator;

        shrink();
    }

    private void sanitizeAndMakeCompatible(Fraction object) {
        sanitize();
        object.sanitize();
        makeFractionsCompatible(this, object);
    }

    void multiply(Fraction object) {
        numerator = numerator * object.numerator;
        denominator = denominator * object.denominator;

        shrink();
    }

    void divide(Fraction object) {
        final int newNumerator   = numerator * object.denominator;
        final int newDenominator = denominator * object.numerator;

        numerator = newNumerator;
        denominator = newDenominator;

        shrink();
    }

    void shrink() {
        sanitize();

        final boolean switchNumerator = numerator < 0;

        if (switchNumerator) {
            numerator *= -1;
        }

        final int commonDivider = MathSupport.findHighestCommonDivider(numerator, denominator);

        numerator /= commonDivider;
        denominator /= commonDivider;

        if (switchNumerator) {
            numerator *= -1;
        }
    }

    void sanitize() {
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
    }

    private void makeFractionsCompatible(Fraction subject, Fraction object) {
        final int newDenominator = subject.denominator * object.denominator;

        subject.numerator *= object.denominator;
        object.numerator *= subject.denominator;

        subject.denominator = newDenominator;
        object.denominator = newDenominator;
    }
}
