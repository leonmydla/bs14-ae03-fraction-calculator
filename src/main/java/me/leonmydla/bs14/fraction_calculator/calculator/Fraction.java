package me.leonmydla.bs14.fraction_calculator.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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

    public void add(Fraction object) {
        makeFractionsCompatible(this, object);

        numerator += object.numerator;

        shrink();
    }

    public void subtract(Fraction object) {
        makeFractionsCompatible(this, object);

        numerator -= object.numerator;

        shrink();
    }

    public void multiply(Fraction object) {
        numerator = numerator * object.numerator;
        denominator = denominator * object.denominator;

        shrink();
    }

    public void divide(Fraction object) {
        final int newNumerator   = numerator * object.denominator;
        final int newDenominator = denominator * object.numerator;

        numerator = newNumerator;
        denominator = newDenominator;

        shrink();
    }

    public void shrink() {
        if (numerator == denominator) {
            numerator = 1;
            denominator = 1;
            return;
        }

        int newDenominator = MathSupport.findLowestCommonMultiple(numerator, denominator);

        setDenominatorAndAdjustNumerator(this, newDenominator);

        newDenominator = MathSupport.findLowestCommonDivider(numerator, denominator);

        if (newDenominator > 0) {
            setDenominatorAndAdjustNumerator(this, newDenominator);
        }
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    private void makeFractionsCompatible(Fraction subject, Fraction object) {
        subject.shrink();
        object.shrink();

        final int newDenominator = MathSupport.findLowestCommonMultiple(subject.denominator, object.denominator);

        setDenominatorAndAdjustNumerator(subject, newDenominator);
        setDenominatorAndAdjustNumerator(object, newDenominator);
    }

    private static void setDenominatorAndAdjustNumerator(Fraction fraction, final int denominator) {
        final BigDecimal newDenominator = new BigDecimal(denominator);
        final BigDecimal newNumerator = newDenominator.divide(new BigDecimal(fraction.denominator))
                                                      .multiply(new BigDecimal(fraction.numerator));

        if (newNumerator.equals(newNumerator.setScale(0, RoundingMode.FLOOR))) {
            throw new CalculationException("Can't adjust numerator. Result is not an " + Integer.class);
        }

        fraction.numerator = Math.round(Math.ceil(newNumerator));
        fraction.denominator = Math.round(newDenominator);
    }
}
