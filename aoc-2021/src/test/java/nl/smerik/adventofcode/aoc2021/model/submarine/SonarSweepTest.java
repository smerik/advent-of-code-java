package nl.smerik.adventofcode.aoc2021.model.submarine;

import org.junit.jupiter.api.Test;

import java.util.List;

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
}