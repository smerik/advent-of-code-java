package nl.smerik.adventofcode.aoc2021.model.submarine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrabSubmarineTest {

    public static final List<Integer> CRAB_POSITIONS_EXAMPLE_01 = List.of(16, 1, 2, 0, 4, 2, 7, 1, 2, 14);

    private static Stream<Arguments> fuelSpent() {
        return Stream.of(
                // @formatter:off
                // Example - Part 01
                Arguments.of(16,  2, true , 14),
                Arguments.of( 1,  2, true ,  1),
                Arguments.of( 2,  2, true ,  0),
                Arguments.of( 0,  2, true ,  2),
                Arguments.of( 4,  2, true ,  2),
                Arguments.of( 7,  2, true ,  5),
                Arguments.of(14,  2, true , 12),
                // Example - Part 02
                Arguments.of(16,  5, false, 66),
                Arguments.of( 1,  5, false, 10),
                Arguments.of( 2,  5, false,  6),
                Arguments.of( 0,  5, false, 15),
                Arguments.of( 4,  5, false,  1),
                Arguments.of( 2,  5, false,  6),
                Arguments.of( 7,  5, false,  3),
                Arguments.of(14,  5, false, 45)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("fuelSpent")
    void testCalculateFuelSpent(final int fromPosition, final int toPosition, final boolean burnFuelAtConstantRate, final int expectedFuel) {
        assertEquals(expectedFuel, CrabSubmarine.calculateFuelSpent(fromPosition, toPosition, burnFuelAtConstantRate));
    }

    @Test
    void testCalculateFuelSpentByAlignedPosition() {
        final CrabSubmarine submarine = new CrabSubmarine(CRAB_POSITIONS_EXAMPLE_01);
        // Example - Part 01
        final Map<Integer, Integer> resultPart1 = submarine.calculateFuelSpentByAlignedPosition(true);
        assertEquals(41, resultPart1.get(1));
        assertEquals(37, resultPart1.get(2));
        assertEquals(39, resultPart1.get(3));
        assertEquals(71, resultPart1.get(10));
        // Example - Part 02
        final Map<Integer, Integer> resultPart2 = submarine.calculateFuelSpentByAlignedPosition(false);
        assertEquals(168, resultPart2.get(5));
        assertEquals(206, resultPart2.get(2));
    }

    @Test
    void testDetermineLowestFuel() {
        final CrabSubmarine submarine = new CrabSubmarine(CRAB_POSITIONS_EXAMPLE_01);
        assertEquals(37, submarine.determineLowestFuel(true));
        assertEquals(168, submarine.determineLowestFuel(false));
    }
}