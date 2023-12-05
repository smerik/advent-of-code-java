package nl.smerik.adventofcode.aoc2023.model.almanac;

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
}