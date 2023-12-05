package nl.smerik.adventofcode.aoc2023.model.almanac;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AlmanacRangeTest {

    private static final AlmanacRange RANGE = new AlmanacRange(50, 98, 2);

    private static Stream<Arguments> containsSourceSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(false, RANGE,  97),
                Arguments.of(true , RANGE,  98),
                Arguments.of(true , RANGE,  99),
                Arguments.of(false, RANGE, 100)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("containsSourceSource")
    void testContainsSource(final boolean expectedResult, final AlmanacRange range, final long sourceNumber) {
        assertEquals(expectedResult, range.containsSource(sourceNumber));
    }

    private static Stream<Arguments> containsDestinationSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(false, RANGE, 49),
                Arguments.of(true , RANGE, 50),
                Arguments.of(true , RANGE, 51),
                Arguments.of(false, RANGE, 52)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("containsDestinationSource")
    void testContainsDestination(final boolean expectedResult, final AlmanacRange range, final long destinationNumber) {
        assertEquals(expectedResult, range.containsDestination(destinationNumber));
    }

    private static Stream<Arguments> mapSourceToDestinationSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 50, RANGE,  98),
                Arguments.of( 51, RANGE,  99)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("mapSourceToDestinationSource")
    void testMapSourceToDestination(final long expectedDestinationNumber, final AlmanacRange range, final long sourceNumber) {
        assertEquals(expectedDestinationNumber, range.mapSourceToDestination(sourceNumber));
    }

    @Test
    void testMapSourceToDestinationThrowsException() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RANGE.mapSourceToDestination(0);
        });
        final String expectedMessagePart = "Number 0 not within Range";
        assertTrue(exception.getMessage().contains(expectedMessagePart),
                "Expected message '" + expectedMessagePart + "' not part of '" + exception.getMessage() + "'");
    }

    private static Stream<Arguments> mapDestinationToSourceSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 98, RANGE,  50),
                Arguments.of( 99, RANGE,  51)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("mapDestinationToSourceSource")
    void testMapDestinationToSource(final long expectedSourceNumber, final AlmanacRange range, final long destinationNumber) {
        assertEquals(expectedSourceNumber, range.mapDestinationToSource(destinationNumber));
    }

    @Test
    void testMapDestinationToSourceThrowsException() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RANGE.mapDestinationToSource(0);
        });
        final String expectedMessagePart = "Number 0 not within Range";
        assertTrue(exception.getMessage().contains(expectedMessagePart),
                "Expected message '" + expectedMessagePart + "' not part of '" + exception.getMessage() + "'");
    }
}