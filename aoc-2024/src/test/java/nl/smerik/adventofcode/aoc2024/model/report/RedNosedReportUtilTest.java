package nl.smerik.adventofcode.aoc2024.model.report;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RedNosedReportUtilTest {

    private static Stream<Arguments> provideSourceForIsReportSafe() {
        // @formatter:off
        return Stream.of(
                // Without the Problem Dampener
                Arguments.of(true , List.of(7, 6, 4, 2, 1), false),
                Arguments.of(false, List.of(1, 2, 7, 8, 9), false),
                Arguments.of(false, List.of(9, 7, 6, 2, 1), false),
                Arguments.of(false, List.of(1, 3, 2, 4, 5), false),
                Arguments.of(false, List.of(8, 6, 4, 4, 1), false),
                Arguments.of(true , List.of(1, 3, 6, 7, 9), false),
                // With the Problem Dampener
                Arguments.of(true , List.of(7, 6, 4, 2, 1), true ),
                Arguments.of(false, List.of(1, 2, 7, 8, 9), true ),
                Arguments.of(false, List.of(9, 7, 6, 2, 1), true ),
                Arguments.of(true , List.of(1, 3, 2, 4, 5), true ),
                Arguments.of(true , List.of(8, 6, 4, 4, 1), true ),
                Arguments.of(true , List.of(1, 3, 6, 7, 9), true ),
                // Additional edge cases
                Arguments.of(true , List.of(48, 46, 47, 49, 51, 54, 56    ), true),
                Arguments.of(true , List.of( 1,  1,  2,  3,  4,  5        ), true),
                Arguments.of(true , List.of( 1,  2,  3,  4,  5,  5        ), true),
                Arguments.of(true , List.of( 5,  1,  2,  3,  4,  5        ), true),
                Arguments.of(true , List.of( 1,  4,  3,  2,  1            ), true),
                Arguments.of(true , List.of( 1,  6,  7,  8,  9            ), true),
                Arguments.of(true , List.of( 1,  2,  3,  4,  3            ), true),
                Arguments.of(true , List.of( 9,  8,  7,  6,  7            ), true),
                Arguments.of(true , List.of( 7, 10,  8, 10, 11            ), true),
                Arguments.of(true , List.of(29, 28, 27, 25, 26, 25, 22, 20), true),
                Arguments.of(true , List.of(52, 51, 52, 49, 47, 45        ), true)
        );
        // @formatter:on
    }

    @ParameterizedTest
    @MethodSource("provideSourceForIsReportSafe")
    void isReportSafe(final boolean expectedResult, final List<Integer> levels, final boolean applyProblemDampener) {
        assertEquals(expectedResult, RedNosedReportUtil.isReportSafe(levels, applyProblemDampener));
    }
}