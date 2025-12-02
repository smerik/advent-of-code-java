package nl.smerik.adventofcode.aoc2025.model.safe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DialTest {

    private static Stream<Arguments> examplePart01() {
        return Stream.of(
                // @formatter:off
                Arguments.of(50, Direction.L, 68, 82),
                Arguments.of(82, Direction.L, 30, 52),
                Arguments.of(52, Direction.R, 48,  0),
                Arguments.of( 0, Direction.L,  5, 95),
                Arguments.of(95, Direction.R, 60, 55),
                Arguments.of(55, Direction.L, 55,  0),
                Arguments.of( 0, Direction.L,  1, 99),
                Arguments.of(99, Direction.L, 99,  0),
                Arguments.of( 0, Direction.R, 14, 14),
                Arguments.of(14, Direction.L, 82, 32)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("examplePart01")
    void testRotate(final int startPointerAt, final Direction direction, final int distance, final int expectedResult) {
        // Given
        final Dial dial = new Dial(startPointerAt);
        // When & Then
        assertEquals(expectedResult, dial.rotate(new Rotation(direction, distance)));
    }

    private static Stream<Arguments> examplePart02() {
        return Stream.of(
                // @formatter:off
                Arguments.of(50, Direction.L,   68,  1),
                Arguments.of(82, Direction.L,   30,  0),
                Arguments.of(52, Direction.R,   48,  1),
                Arguments.of( 0, Direction.L,    5,  0),
                Arguments.of(95, Direction.R,   60,  1),
                Arguments.of(55, Direction.L,   55,  1),
                Arguments.of( 0, Direction.L,    1,  0),
                Arguments.of(99, Direction.L,   99,  1),
                Arguments.of( 0, Direction.R,   14,  0),
                Arguments.of(14, Direction.L,   82,  1),
                // Be careful
                Arguments.of(50, Direction.R, 1000, 10)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("examplePart02")
    void testRotatePart02(final int startPointerAt, final Direction direction, final int distance, final int expectedResult) {
        // Given
        final Dial dial = new Dial(startPointerAt);
        // When
        dial.rotate(new Rotation(direction, distance));
        // Then
        assertEquals(expectedResult, dial.getPointedAtZeroCount());
    }
}