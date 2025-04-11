package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void PrimeNumbers() throws BigNumberException, SmallNumberException {
        Main main = new Main();
        assertTrue(main.isPrime(2), "2 應該為質數");
        assertTrue(main.isPrime(3), "3 應該為質數");
        assertTrue(main.isPrime(5), "5 應該為質數");
        assertTrue(main.isPrime(7), "7 應該為質數");
    }

    @Test
    public void NonPrimeNumbers() throws BigNumberException, SmallNumberException {
        Main main = new Main();
        // 測試非質數
        assertFalse(main.isPrime(1), "1 不是質數");
        assertFalse(main.isPrime(4), "4 不是質數");
        assertFalse(main.isPrime(6), "6 不是質數");
        assertFalse(main.isPrime(9), "9 不是質數");
    }

    @Test
    public void BigNumber() {
        Main main = new Main();
        Exception exception = assertThrows(BigNumberException.class, () -> {
            main.isPrime(101);
        });
        assertEquals("數值太大: 101", exception.getMessage());
    }

    @Test
    public void SmallNumber() {
        Main main = new Main();
        Exception exception = assertThrows(SmallNumberException.class, () -> {
            main.isPrime(-5);
        });
        assertEquals("數值太小: -5", exception.getMessage());
    }
}
