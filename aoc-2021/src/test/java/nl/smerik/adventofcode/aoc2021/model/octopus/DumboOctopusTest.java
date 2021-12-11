package nl.smerik.adventofcode.aoc2021.model.octopus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.Point;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DumboOctopusTest {

    private static Stream<Arguments> increaseEnergyLevel() {
        return Stream.of(
                //@formatter:off
                Arguments.of(0, false, 1, false, 2),
                Arguments.of(8, false, 9, true , 0),
                Arguments.of(9, true , 0, false, 0)
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("increaseEnergyLevel")
    void testIncreaseEnergyLevel(final int energyLevel,
                                 final boolean expectedFlashed1stTime,
                                 final int expectedEnergyLevel1stTime,
                                 final boolean expectedFlashed2ndTime,
                                 final int expectedEnergyLevel2ndTime) {
        final DumboOctopus octopus = new DumboOctopus(new Point(), energyLevel);
        // increase 1st time
        assertEquals(expectedFlashed1stTime, octopus.increaseEnergyLevel(1));
        assertEquals(expectedEnergyLevel1stTime, octopus.getEnergyLevel());
        // increase 2nd time
        assertEquals(expectedFlashed2ndTime, octopus.increaseEnergyLevel(1));
        assertEquals(expectedEnergyLevel2ndTime, octopus.getEnergyLevel());
    }

    @Test
    void testToString() {
        assertEquals("3", new DumboOctopus(new Point(), 3).toString());
    }
}