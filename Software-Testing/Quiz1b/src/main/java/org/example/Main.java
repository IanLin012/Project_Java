package org.example;

/**
 *isPrime的功能
 */

public class Main {
    /* default */ Main(){
        // This constructor is intentionally empty. Nothing special is needed here.
    }

    /**
     *如果 num 大於 100 就拋出例外 BigNumberException; 小於 0 就拋出 SmallNumberException
     */
    public boolean isPrime(final int num) throws BigNumberException, SmallNumberException {
        final int bigNumber = 100;
        if (num > bigNumber) {
            throw new BigNumberException("數值太大: " + num);
        }
        if (num < 0) {
            throw new SmallNumberException("數值太小: " + num);
        }

        boolean bool = true;
        final int minPrime = 2;
        if (num < minPrime) {
            bool = false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                bool = false;
            }
        }
        return bool;
    }
}