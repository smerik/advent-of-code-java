package nl.smerik.adventofcode.aoc2024.model.stone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PlutonianPebblesTest {

    private static final String STONES_EXAMPLE_01 = "0 1 10 99 999";
    private static final String STONES_EXAMPLE_02 = "125 17";

    public static Stream<Arguments> provideSourceForBlink() {
        return Stream.of(
                //@formatter:on
                Arguments.of(    7, STONES_EXAMPLE_01,  1),
                Arguments.of    (3, STONES_EXAMPLE_02,  1),
                Arguments.of    (4, STONES_EXAMPLE_02,  2),
                Arguments.of(    5, STONES_EXAMPLE_02,  3),
                Arguments.of(    9, STONES_EXAMPLE_02,  4),
                Arguments.of(   13, STONES_EXAMPLE_02,  5),
                Arguments.of(   22, STONES_EXAMPLE_02,  6),
                Arguments.of(55312, STONES_EXAMPLE_02, 25)
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForBlink")
    void testBlink(final long expected, final String arrangement, final int blinkCount) {
        final PlutonianPebbles pebbles = new PlutonianPebbles(Collections.singletonList(arrangement));
        pebbles.blink(blinkCount);
        assertEquals(expected, pebbles.countStones());
    }

    @Test
    void testCountStones() {
        final PlutonianPebbles pebbles = new PlutonianPebbles(Collections.singletonList("0 0 1 1 2"));
        assertEquals(5, pebbles.countStones());
    }
}