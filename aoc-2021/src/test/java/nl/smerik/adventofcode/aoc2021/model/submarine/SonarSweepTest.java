package nl.smerik.adventofcode.aoc2021.model.submarine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SonarSweepTest {

    private final List<Integer> example01Part01 = List.of(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263
    );

    @Test
    void testCountIncreasingDepthMeasurements() {
        final SonarSweep sonarSweep = new SonarSweep(example01Part01);
        assertEquals(7, sonarSweep.countIncreasingDepthMeasurements());
    }

    @Test
    void testCreateSumOfSlidingWindows() {
        final SonarSweep sonarSweep = new SonarSweep(example01Part01);
        assertEquals(List.of(607, 618, 618, 617, 647, 716, 769, 792), sonarSweep.createSumOfSlidingWindows());
    }


    private static Stream<Arguments> slidingWindowsExamplePart02() {
        return Stream.of(
                Arguments.of(List.of(199, 200, 208)), // A
                Arguments.of(List.of(200, 208, 210)), // B
                Arguments.of(List.of(208, 210, 200)), // C
                Arguments.of(List.of(210, 200, 207)), // D
                Arguments.of(List.of(200, 207, 240)), // E
                Arguments.of(List.of(207, 240, 269)), // F
                Arguments.of(List.of(240, 269, 260)), // G
                Arguments.of(List.of(269, 260, 263))  // H
        );
    }

    @ParameterizedTest
    @MethodSource("slidingWindowsExamplePart02")
    void testCreateSlidingWindows(final List<Integer> slidingWindows) {
        final SonarSweep sonarSweep = new SonarSweep(example01Part01);
        assertTrue(sonarSweep.createSlidingWindows().contains(slidingWindows));
    }

    @Test
    void testCountIncreasingDepthSlidingWindowMeasurements() {
        final SonarSweep sonarSweep = new SonarSweep(example01Part01);
        assertEquals(5, sonarSweep.countIncreasingDepthSlidingWindowMeasurements());
    }
}