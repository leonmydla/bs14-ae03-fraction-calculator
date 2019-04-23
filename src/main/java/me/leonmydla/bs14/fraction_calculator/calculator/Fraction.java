package me.leonmydla.bs14.fraction_calculator.calculator;

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
        return numerator + "/" + denominator;
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

    int getDenominator() {
        return denominator;
    }

    void setDenominator(final int denominator) {
        this.denominator = denominator;
    }

    void add(Fraction object) {
        makeFractionsCompatible(this, object);

        numerator += object.numerator;

        shrink();
    }

    void subtract(Fraction object) {
        makeFractionsCompatible(this, object);

        numerator -= object.numerator;

        shrink();
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
        final int commonDivider = MathSupport.findHighestCommonDivider(numerator, denominator);

        numerator /= commonDivider;
        denominator /= commonDivider;
    }

    private void makeFractionsCompatible(Fraction subject, Fraction object) {
        final int newDenominator = subject.denominator * object.denominator;

        subject.numerator *= object.denominator;
        object.numerator *= subject.denominator;

        subject.denominator = newDenominator;
        object.denominator = newDenominator;
    }
}
