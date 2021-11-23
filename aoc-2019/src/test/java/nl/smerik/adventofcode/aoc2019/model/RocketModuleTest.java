package nl.smerik.adventofcode.aoc2019.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RocketModuleTest {

    private static Stream<Arguments> calculateRequiredFuelExamples() {
        return Stream.of(
                // @formatter:off
                // Part 1
                Arguments.of(    12, false,     2),
                Arguments.of(    14, false,     2),
                Arguments.of(  1969, false,   654),
                Arguments.of(100756, false, 33583),
                // Part 2
                Arguments.of(    14, true ,     2),
                Arguments.of(  1969, true ,   966),
                Arguments.of(100756, true , 50346)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("calculateRequiredFuelExamples")
    void testCalculateRequiredFuel(final int mass, final boolean includeAddedFuel, final int expectedResult) {
        final RocketModule module = new RocketModule(mass);
        assertEquals(expectedResult, module.calculateRequiredFuel(includeAddedFuel));
    }
}