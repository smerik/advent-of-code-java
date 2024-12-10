package nl.smerik.adventofcode.aoc2024.model.hiking;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TopographicMapTest {

    @Value("classpath:input/day-10/example-01.txt")
    private Resource example01Resource;

    @Value("classpath:input/day-10/example-02.txt")
    private Resource example02Resource;


    @Test
    void testSumTrailheadsScore() {
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01Resource);
        final TopographicMap mapExample01 = new TopographicMap(linesExample01);
        assertEquals(1, mapExample01.sumTrailheadsScore());

        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02Resource);
        final TopographicMap mapExample02 = new TopographicMap(linesExample02);
        assertEquals(36, mapExample02.sumTrailheadsScore());
    }


    private static Stream<Arguments> provideSourceForCalculateTrailheadsScore() {
        return Stream.of(
                Arguments.of(5, new Point(2, 0)),
                Arguments.of(6, new Point(4, 0)),
                Arguments.of(5, new Point(4, 2)),
                Arguments.of(3, new Point(6, 4)),
                Arguments.of(1, new Point(2, 5)),
                Arguments.of(3, new Point(5, 5)),
                Arguments.of(5, new Point(0, 6)),
                Arguments.of(3, new Point(6, 6)),
                Arguments.of(5, new Point(1, 7))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForCalculateTrailheadsScore")
    void testCalculateTrailheadsScore(final int expectedResult, final Point startPosition) {
        final List<String> lines = PuzzleInputParser.parseToString(example02Resource);
        final TopographicMap map = new TopographicMap(lines);
        assertEquals(expectedResult, map.calculateTrailheadsScore(startPosition));
    }
}