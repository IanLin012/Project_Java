package org.example;

/**
 *num 大於 100
 */

public class BigNumberException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     *Extends from Exception
     */
    public BigNumberException(final String message) {
        super(message);
    }
}

