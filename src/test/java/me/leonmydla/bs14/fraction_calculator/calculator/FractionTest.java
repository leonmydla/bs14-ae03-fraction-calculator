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

        assertEquals(0, fraction.getNumerator(), "Should have correct default Numerator");
        assertEquals(1, fraction.getDenominator(), "Should have correct default Denominator");
    }

    @Test
    void shouldSetCorrectDefaultValues() {
        assertEquals(commonNumerator, fraction.getNumerator(), "Should have expected Numerator");
        assertEquals(commonDenominator, fraction.getDenominator(), "Should have expected Denominator");
    }

    @Test
    void shouldCreateCorrectString() {
        String expectedString = commonNumerator + "/" + commonDenominator;

        assertEquals(expectedString, fraction.toString(), "Should create expected string");
    }

    @Test
    void shouldMultiplyCorrectly() {
        int expectedNumerator   = fraction.getNumerator() * fraction.getNumerator();
        int expectedDenominator = fraction.getDenominator() * fraction.getDenominator();

        fraction.multiply(fraction);

        assertEquals(expectedNumerator, fraction.getNumerator());
        assertEquals(expectedDenominator, fraction.getDenominator());
    }

    @Test
    void shouldDivideCorrectly() {
        fraction.setNumerator(1);
        int expectedNumerator   = fraction.getNumerator() * fraction.getDenominator();
        int expectedDenominator = fraction.getDenominator() * fraction.getNumerator();

        fraction.divide(fraction);

        System.out.println(fraction);
        assertEquals(expectedNumerator, fraction.getNumerator());
        assertEquals(expectedDenominator, fraction.getDenominator());
    }
}
