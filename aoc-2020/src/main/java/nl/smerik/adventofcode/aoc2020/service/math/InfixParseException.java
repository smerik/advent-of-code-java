package nl.smerik.adventofcode.aoc2020.service.math;

public class InfixParseException extends RuntimeException {

    public InfixParseException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
