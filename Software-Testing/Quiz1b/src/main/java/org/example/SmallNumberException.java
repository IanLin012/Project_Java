package org.example;

/**
 *num小於 0
 */

public class SmallNumberException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     *Extends from Exception
     */
    public SmallNumberException(final String message) {
        super(message);
    }
}
