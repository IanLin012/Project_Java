package org.example;
/**
 *輸入的日期不對
 */
public class DateException extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     *Extends from Exception
     */
    public DateException(final String message) {
        super(message);
    }
}
