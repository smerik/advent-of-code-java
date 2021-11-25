package nl.smerik.adventofcode.io;

/**
 * Runtime exception thrown when the resource cannot be resolved as URI.
 */
public class UnresolvableURIException extends RuntimeException {

    /**
     * Constructs the exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public UnresolvableURIException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
