package nl.smerik.adventofcode.aoc2024.model.report;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RedNosedReportUtilTest {

    private static Stream<Arguments> provideSourceForIsReportSafe() {
        return Stream.of(
                Arguments.of(true, List.of(7, 6, 4, 2, 1)),
                Arguments.of(false, List.of(1, 2, 7, 8, 9)),
                Arguments.of(false, List.of(9, 7, 6, 2, 1)),
                Arguments.of(false, List.of(1, 3, 2, 4, 5)),
                Arguments.of(false, List.of(8, 6, 4, 4, 1)),
                Arguments.of(true, List.of(1, 3, 6, 7, 9))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForIsReportSafe")
    void isReportSafe(final boolean expectedResult, final List<Integer> levels) {
        assertEquals(expectedResult, RedNosedReportUtil.isReportSafe(levels));
    }
}