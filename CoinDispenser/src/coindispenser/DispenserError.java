/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package coindispenser;

/**
 * Exception raised when failing to dispense coins.
 * 
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class DispenserError extends Exception {

    /**
     * Creates a new instance of <code>DispenserError</code> without detail
     * message.
     */
    public DispenserError() {
    }

    /**
     * Constructs an instance of <code>DispenserError</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DispenserError(String msg) {
        super(msg);
    }
}
