package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class LoanCalculatorTest {
    @ParameterizedTest(name = "years={0}, amount={1}, young={2}, married={3} => expected{4}")
    @CsvSource({
        "5, 400, false, false, 5",
        "15, 600, false, true, 3.9",
        "25, 2000, true, true, 2"
    })
    void weakTest(int loanYears, double loanAmount, boolean isYouth, boolean isMarried, double expected) {
        assertEquals(expected, LoanCalculator.calculateInterestRate(loanYears, loanAmount*10000, isYouth, isMarried));
    }

    @ParameterizedTest(name = "years={0}, amount={1}, young={2}, married={3} => expected{4}")
    @CsvSource({
        "5, 400, false, false, 5",
        "5, 600, true, true, 3.4",
        "15, 400, true, true, 3",
        "15, 600, false, false, 4.4",
        "25, 400, false, true, 3.5",
        "25, 2000, true, false, 2"
    })
    void allPairTest(int loanYears, double loanAmount, boolean isYouth, boolean isMarried, double expected) {
        assertEquals(expected, LoanCalculator.calculateInterestRate(loanYears, loanAmount*10000, isYouth, isMarried));
    }

    @ParameterizedTest(name = "years={0}, amount={1}, young={2}, married={3} => expected{4}")
    @CsvSource({
        "5, 400, true, true, 3.5",
        "5, 600, true, true, 3.4",
        "5, 400, true, false, 4",
        "5, 600, true, false, 3.9",
        "5, 400, false, true, 4.5",
        "5, 600, false, true, 4.4",
        "5, 400, false, false, 5",
        "5, 600, false, false, 4.9",
        "15, 400, true, true, 3",
        "15, 600, true, true, 2.9",
        "15, 400, true, false, 3.5",
        "15, 600, true, false, 3.4",
        "15, 400, false, true, 4",
        "15, 600, false, true, 3.9",
        "15, 400, false, false, 4.5",
        "15, 600, false, false, 4.4",
        "25, 400, true, true, 2.5",
        "25, 2000, true, true, 2",
        "25, 400, true, false, 3",
        "25, 600, true, false, 2.9",
        "25, 400, false, true, 3.5",
        "25, 600, false, true, 3.4",
        "25, 400, false, false, 4",
        "25, 600, false, false, 3.9"
    })
    void strongTest(int loanYears, double loanAmount, boolean isYouth, boolean isMarried, double expected) {
        assertEquals(expected, LoanCalculator.calculateInterestRate(loanYears, loanAmount*10000, isYouth, isMarried));
    }
}
