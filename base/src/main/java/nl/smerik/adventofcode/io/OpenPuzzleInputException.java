package nl.smerik.adventofcode.io;

/**
 * Runtime exception thrown when the puzzle input failed to open.
 */
public class OpenPuzzleInputException extends RuntimeException {

    /**
     * Constructs the exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public OpenPuzzleInputException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
