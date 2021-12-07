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
                Arguments.of(16,  2, 14),
                Arguments.of( 1,  2,  1),
                Arguments.of( 2,  2,  0),
                Arguments.of( 0,  2,  2),
                Arguments.of( 4,  2,  2),
                Arguments.of( 7,  2,  5),
                Arguments.of(14,  2, 12)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("fuelSpent")
    void testCalculateFuelSpent(final int fromPosition, final int toPosition, final int expectedFuel) {
        assertEquals(expectedFuel, CrabSubmarine.calculateFuelSpent(fromPosition, toPosition));
    }

    @Test
    void testCalculateFuelSpentByAlignedPosition() {
        final CrabSubmarine submarine = new CrabSubmarine(CRAB_POSITIONS_EXAMPLE_01);
        final Map<Integer, Integer> result = submarine.calculateFuelSpentByAlignedPosition();
        assertEquals(41, result.get(1));
        assertEquals(37, result.get(2));
        assertEquals(39, result.get(3));
        assertEquals(71, result.get(10));
    }

    @Test
    void testDetermineLowestFuel() {
        final CrabSubmarine submarine = new CrabSubmarine(CRAB_POSITIONS_EXAMPLE_01);
        assertEquals(37, submarine.determineLowestFuel());
    }
}