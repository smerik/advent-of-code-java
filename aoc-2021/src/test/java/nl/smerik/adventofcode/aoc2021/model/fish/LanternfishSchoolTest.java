package nl.smerik.adventofcode.aoc2021.model.fish;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LanternfishSchoolTest {

    @Test
    void testInit() {
        final LanternfishSchool school = new LanternfishSchool(List.of(3, 4, 3, 1, 2));
        assertEquals(9, school.getLanternfishCountByTimer().length);
    }

    private static Stream<Arguments> simulateAndAssertArray() {
        return Stream.of(
                // @formatter:off
                Arguments.of(new long[]{0, 1, 1, 2, 1, 0, 0, 0, 0},  0),
                Arguments.of(new long[]{1, 1, 2, 1, 0, 0, 0, 0, 0},  1),
                Arguments.of(new long[]{1, 2, 1, 0, 0, 0, 1, 0, 1},  2)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("simulateAndAssertArray")
    void testSimulateAndAssertArray(final long[] expectedFishCountByTimer, final int simulateDays) {
        final LanternfishSchool school = new LanternfishSchool(List.of(3, 4, 3, 1, 2));
        school.simulate(simulateDays);
        assertArrayEquals(expectedFishCountByTimer, school.getLanternfishCountByTimer());
    }

    private static Stream<Arguments> simulateAndAssertSum() {
        return Stream.of(
                // @formatter:off
                Arguments.of(         26L,  18),
                Arguments.of(       5934L,  80),
                Arguments.of(26984457539L, 256)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("simulateAndAssertSum")
    void testSimulateAndAssertSum(final long expectedFishCount, final int simulateDays) {
        final LanternfishSchool school = new LanternfishSchool(List.of(3, 4, 3, 1, 2));
        assertEquals(expectedFishCount, school.simulate(simulateDays));
    }

    @Test
    void testCountTotalFishes() {
        final LanternfishSchool school = new LanternfishSchool(List.of(3, 4, 3, 1, 2));
        assertEquals(5, school.countTotalFishes());
    }
}