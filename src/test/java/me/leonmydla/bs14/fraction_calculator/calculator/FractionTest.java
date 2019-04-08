package me.leonmydla.bs14.fraction_calculator.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FractionTest {

    @Test
    void shouldHaveCorrectDefaultValues() {
        Fraction fraction = new Fraction();

        assertEquals(0, fraction.getNumerator());
        assertEquals(1, fraction.getDenominator());
    }

    @Test
    void shouldSetCorrectDefaultValues() {
        Fraction fraction = new Fraction(1, 0);

        assertEquals(1, fraction.getNumerator());
        assertEquals(0, fraction.getDenominator());
    }
}
