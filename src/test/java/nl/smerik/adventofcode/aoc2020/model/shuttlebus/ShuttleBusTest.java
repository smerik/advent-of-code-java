package nl.smerik.adventofcode.aoc2020.model.shuttlebus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShuttleBusTest {

    private static Stream<Arguments> provideSourceForFindEarliestDeparture() {
        return Stream.of(
                // @formatter:off
                // Day 13 - Part 01 - Example 01
                // Bus 5
                Arguments.of( 5,  0,  0),
                Arguments.of( 5,  1,  5),
                Arguments.of( 5,  6, 10),
                Arguments.of( 5, 11, 15),
                // Bus 11
                Arguments.of(11,  0,  0),
                Arguments.of(11,  1, 11),
                Arguments.of(11, 12, 22),
                Arguments.of(11, 23, 33),

                // Day 13 - Part 01 - Example 02
                // Bus 7
                Arguments.of( 7, 929, 931),
                Arguments.of( 7, 932, 938),
                Arguments.of( 7, 939, 945),
                // Bus 13
                Arguments.of(13, 929, 936),
                Arguments.of(13, 937, 949),
                // Bus 59
                Arguments.of(59, 929, 944),
                // Bus 31
                Arguments.of(31, 929, 930),
                // Bus 19
                Arguments.of(19, 929, 931)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForFindEarliestDeparture")
    void findNextDeparture(final int busID, final int timestamp, final int expectedDeparture) {
        final ShuttleBus bus = new ShuttleBus(busID);
        assertEquals(expectedDeparture, bus.findEarliestDeparture(timestamp));
    }

    @Test
    void calculateWaitingTimeForEarliestDeparture() {
        final ShuttleBus bus = new ShuttleBus(59);
        assertEquals(5, bus.calculateWaitingTimeForEarliestDeparture(939));
    }

    @Test
    void calculateSolutionPart01() {
        final ShuttleBus bus = new ShuttleBus(59);
        assertEquals(295, bus.calculateSolutionPart01(939));
    }
}