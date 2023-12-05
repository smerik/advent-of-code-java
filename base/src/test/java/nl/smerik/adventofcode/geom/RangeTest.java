package nl.smerik.adventofcode.geom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RangeTest {

    private static Stream<Arguments> containsSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(false, 10L, 11L,  9L),
                Arguments.of(true , 10L, 11L, 10L),
                Arguments.of(false, 10L, 11L, 11L),
                //
                Arguments.of(false, 10L, 20L, -1L),
                Arguments.of(false, 10L, 20L,  0L),
                Arguments.of(false, 10L, 20L,  9L),
                Arguments.of(true , 10L, 20L, 10L),
                Arguments.of(true , 10L, 20L, 15L),
                Arguments.of(true , 10L, 20L, 19L),
                Arguments.of(false, 10L, 20L, 20L),
                Arguments.of(false, 10L, 20L, 21L)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("containsSource")
    void testContains(final boolean expectedResult, final Long start, final Long endExclusive, final Long numberToCheck) {
        final Range<Long> range = new Range<>(start, endExclusive);
        assertEquals(expectedResult, range.contains(numberToCheck));
    }

    @Test
    void testToString() {
        final Range<Long> range = new Range<>(10L, 20L);
        assertEquals("Range[start=10, endExclusive=20]", range.toString());
    }
}