package me.leonmydla.bs14.fraction_calculator.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FractionTest {

    private final int      commonNumerator   = 10;
    private final int      commonDenominator = 10;
    private       Fraction fraction;

    @BeforeEach
    void beforeAll() {
        fraction = new Fraction(commonNumerator, commonDenominator);
    }

    @Test
    void shouldHaveCorrectDefaultValues() {
        Fraction fraction = new Fraction();

        checkForExpectedValues(fraction, 0, 1);
    }

    @Test
    void shouldSetCorrectDefaultValues() {
        checkForExpectedValues(commonNumerator, commonDenominator);
    }

    @Test
    void shouldCreateCorrectString() {
        String expectedString = commonNumerator + "/" + commonDenominator;

        assertEquals(expectedString, fraction.toString(), "Should create expected string");
    }

    @Test
    void shouldAddCorrectly() {
        fraction.setNumerator(100);
        fraction.setDenominator(10);

        Fraction fractionToAdd = new Fraction(10, 100);

        fraction.add(fractionToAdd);

        checkForExpectedValues(1010, 100);
    }

    @Test
    void shouldSubtractCorrectly() {
        fraction.setNumerator(100);
        fraction.setDenominator(10);

        Fraction fractionToSubtract = new Fraction(10, 100);

        fraction.subtract(fractionToSubtract);

        checkForExpectedValues(990, 100);
    }

    @Test
    void shouldMultiplyCorrectly() {
        final int expectedNumerator   = fraction.getNumerator() * fraction.getNumerator();
        final int expectedDenominator = fraction.getDenominator() * fraction.getDenominator();

        fraction.multiply(fraction);

        checkForExpectedValues(expectedNumerator, expectedDenominator);
    }

    @Test
    void shouldDivideCorrectly() {
        fraction.setNumerator(1);

        final int expectedNumerator   = fraction.getNumerator() * fraction.getDenominator();
        final int expectedDenominator = fraction.getDenominator() * fraction.getNumerator();

        fraction.divide(fraction);

        checkForExpectedValues(expectedNumerator, expectedDenominator);
    }

    @Test
    void shouldShrinkCorrectly() {
        fraction.shrink();

        checkForExpectedValues(1, 1);

        fraction.setNumerator(10);
        fraction.setDenominator(100);
        fraction.shrink();

        checkForExpectedValues(1, 10);
    }

    private void checkForExpectedValues(final int expectedNumerator, final int expectedDenominator) {
        checkForExpectedValues(fraction, expectedNumerator, expectedDenominator);
    }

    private void checkForExpectedValues(final Fraction fraction, final int expectedNumerator, final int expectedDenominator) {
        assertEquals(expectedNumerator, fraction.getNumerator(), "Should have expected numerator");
        assertEquals(expectedDenominator, fraction.getDenominator(), "Should have expected denominator");
    }
}
