package me.leonmydla.bs14.fraction_calculator.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

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
    void shouldReturnIntegralAmountAsString() {
        assertEquals(
            "1",
            fraction.toString(),
            "A Fraction which only represents an integral amount should only be represented the integral amount when toString is used"
        );
    }

    @Test
    void shouldReturnZeroWhenFractionResultsInZero() {
        fraction.setNumerator(0);

        assertEquals(
            "0",
            fraction.toString(),
            "A Fraction which only represents an integral amount should only be represented the integral amount when toString is used"
        );
    }

    @Test
    void shouldReturnFractionWhenNoIntegralAmountIsAvailable() {
        fraction.setNumerator(9);

        assertEquals(
            "9/10",
            fraction.toString(),
            "A fraction which doesn't include an integral amount should only be represented by the fraction as a string"
        );
    }

    @Test
    void shouldReturnIntegralAmountAndFractionOverhang() {
        fraction.setNumerator(19);

        assertEquals(
            "1 9/10",
            fraction.toString(),
            "A fraction which includes an integral amount and has a fraction left should be represented by the integral amount and the left fraction"
        );
    }

    @Test
    void getCorrectNumeratorWithoutIntegralAmount() {
        fraction.setNumerator(19);

        assertEquals(9, fraction.getNumeratorWithoutIntegralAmount());
    }

    @Test
    void getCorrectIntegralAmount() {
        fraction.setNumerator(11);

        assertEquals(1, fraction.getIntegralAmount());

        fraction.setNumerator(19);

        assertEquals(1, fraction.getIntegralAmount());
    }

    @Test
    void shouldAddCorrectly() {
        setFractionAndCheckAction(fraction::add, 100, 10, 10, 100, 101, 10);

        setFractionAndCheckAction(fraction::add, 100, 10, -10, 100, 99, 10);
        setFractionAndCheckAction(fraction::add, 100, 10, 10, -100, 99, 10);
        setFractionAndCheckAction(fraction::add, 100, 10, -10, -100, 101, 10);

        setFractionAndCheckAction(fraction::add, -10, 100, 100, 10, 99, 10);
        setFractionAndCheckAction(fraction::add, 10, -100, 100, 10, 99, 10);
        setFractionAndCheckAction(fraction::add, -10, -100, 100, 10, 101, 10);
    }

    @Test
    void shouldSubtractCorrectly() {
        setFractionAndCheckAction(fraction::subtract, 100, 10, 10, 100, 99, 10);

        setFractionAndCheckAction(fraction::subtract, 100, 10, -10, 100, 101, 10);
        setFractionAndCheckAction(fraction::subtract, 100, 10, 10, -100, 101, 10);
        setFractionAndCheckAction(fraction::subtract, 100, 10, -10, -100, 99, 10);

        setFractionAndCheckAction(fraction::subtract, -10, 100, 100, 10, -101, 10);
        setFractionAndCheckAction(fraction::subtract, 10, -100, 100, 10, -101, 10);
        setFractionAndCheckAction(fraction::subtract, -10, -100, 100, 10, -99, 10);
    }

    @Test
    void shouldMultiplyCorrectly() {
        setFractionAndCheckAction(fraction::multiply, 100, 10, 10, 100, 1, 1);

        setFractionAndCheckAction(fraction::multiply, 100, 10, -10, 100, -1, 1);
        setFractionAndCheckAction(fraction::multiply, 100, 10, 10, -100, -1, 1);
        setFractionAndCheckAction(fraction::multiply, 100, 10, -10, -100, 1, 1);

        setFractionAndCheckAction(fraction::multiply, -10, 100, 100, 10, -1, 1);
        setFractionAndCheckAction(fraction::multiply, 10, -100, 100, 10, -1, 1);
        setFractionAndCheckAction(fraction::multiply, -10, -100, 100, 10, 1, 1);
    }

    @Test
    void shouldDivideCorrectly() {
        setFractionAndCheckAction(fraction::divide, 100, 10, 10, 100, 100, 1);

        setFractionAndCheckAction(fraction::divide, 100, 10, -10, 100, -100, 1);
        setFractionAndCheckAction(fraction::divide, 100, 10, 10, -100, -100, 1);
        setFractionAndCheckAction(fraction::divide, 100, 10, -10, -100, 100, 1);

        setFractionAndCheckAction(fraction::divide, -10, 100, 100, 10, -1, 100);
        setFractionAndCheckAction(fraction::divide, 10, -100, 100, 10, -1, 100);
        setFractionAndCheckAction(fraction::divide, -10, -100, 100, 10, 1, 100);
    }

    private void setFractionAndCheckAction(
        final Consumer<Fraction> action,
        final int aNumerator,
        final int aDenominator,
        final int bNumerator,
        final int bDenominator,
        final int expectedNumerator,
        final int expectedDenominator
    ) {
        fraction.setNumerator(aNumerator);
        fraction.setDenominator(aDenominator);

        Fraction fractionToAdd = new Fraction(bNumerator, bDenominator);

        action.accept(fractionToAdd);

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

    @Test
    void shouldSanitizeCorrectly() {
        setFractionAndCheckSanitation(10, 10, 10, 10);
        setFractionAndCheckSanitation(-10, 10, -10, 10);
        setFractionAndCheckSanitation(10, -10, -10, 10);
        setFractionAndCheckSanitation(-10, -10, 10, 10);
    }

    private void setFractionAndCheckSanitation(
        final int numerator,
        final int denominator,
        final int expectedNumerator,
        final int expectedDenominator
    ) {
        fraction = new Fraction(numerator, denominator);

        fraction.sanitize();

        checkForExpectedValues(expectedNumerator, expectedDenominator);
    }

    private void checkForExpectedValues(final int expectedNumerator, final int expectedDenominator) {
        checkForExpectedValues(fraction, expectedNumerator, expectedDenominator);
    }

    private void checkForExpectedValues(final Fraction fraction, final int expectedNumerator, final int expectedDenominator) {
        assertEquals(expectedNumerator, fraction.getNumerator(), "Should have expected numerator");
        assertEquals(expectedDenominator, fraction.getDenominator(), "Should have expected denominator");
    }
}
