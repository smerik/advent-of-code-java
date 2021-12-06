package nl.smerik.adventofcode.aoc2021.model.fish;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LanternfishSchoolTest {

    private static Stream<Arguments> simulate() {
        return Stream.of(
                // @formatter:off
                Arguments.of(  26, 18),
                Arguments.of(5934, 80)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("simulate")
    void testSimulate(final int expectedFishCount, final int simulateDays) {
        final LanternfishSchool school = new LanternfishSchool(List.of(3, 4, 3, 1, 2));
        assertEquals(expectedFishCount, school.simulate(simulateDays));
    }
}