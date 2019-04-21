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

    public void writeOut() {
        System.out.println(toString());
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(final int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(final int denominator) {
        this.denominator = denominator;
    }

    public Fraction multiply(Fraction object) {
        numerator = numerator * object.numerator;
        denominator = denominator * object.denominator;

        return this;
    }

    public Fraction divide(Fraction object) {
        final int newNumerator = numerator * object.denominator;
        final int newDenominator = denominator * object.numerator;

        setNumerator(newNumerator);
        setDenominator(newDenominator);

        return this;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }
}
