package nl.smerik.adventofcode.aoc2024.model.stone;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PlutonianPebblesTest {

    private static final String STONES_EXAMPLE_01 = "0 1 10 99 999";
    private static final String STONES_EXAMPLE_02 = "125 17";

    public static Stream<Arguments> provideSourceForBlink() {
        return Stream.of(
                //@formatter:off
                Arguments.of("1 2024 1 0 9 9 2021976"                                              , STONES_EXAMPLE_01, 1),
                Arguments.of("253000 1 7"                                                          , STONES_EXAMPLE_02, 1),
                Arguments.of("253 0 2024 14168"                                                    , STONES_EXAMPLE_02, 2),
                Arguments.of("512072 1 20 24 28676032"                                             , STONES_EXAMPLE_02, 3),
                Arguments.of("512 72 2024 2 0 2 4 2867 6032"                                       , STONES_EXAMPLE_02, 4),
                Arguments.of("1036288 7 2 20 24 4048 1 4048 8096 28 67 60 32"                      , STONES_EXAMPLE_02, 5),
                Arguments.of("2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2", STONES_EXAMPLE_02, 6)
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForBlink")
    void testBlink(final String expected, final String arrangement, final int blinkCount) {
        final PlutonianPebbles pebbles = new PlutonianPebbles(Collections.singletonList(arrangement));
        pebbles.blink(blinkCount);
        assertEquals(expected, pebbles.toString());
    }

    @Test
    void testCountStones() {
        final PlutonianPebbles pebbles = new PlutonianPebbles(Collections.singletonList(STONES_EXAMPLE_01));
        assertEquals(5, pebbles.countStones());
    }

    @Test
    void testGetStones() {
        final PlutonianPebbles pebbles = new PlutonianPebbles(Collections.singletonList(STONES_EXAMPLE_01));
        assertEquals(List.of(0L, 1L, 10L, 99L, 999L), pebbles.getStones());
    }

    @Test
    void testToString() {
        final PlutonianPebbles pebbles = new PlutonianPebbles(Collections.singletonList(STONES_EXAMPLE_01));
        assertEquals(STONES_EXAMPLE_01, pebbles.toString());
    }
}