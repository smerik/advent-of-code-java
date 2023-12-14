package nl.smerik.adventofcode.aoc2023.model.mirrorvalley;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValleyOfMirrorsTest {

    private static final List<String> INPUT_EXAMPLE_01 = List.of(
            // @formatter:off
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#.",
            "",
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#",
            ""
            // @formatter:on
    );

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "405, false",
            "400, true "
            // @formatter:off
    })
    void testSumAllPatternNotes(final int expectedResult, final boolean fixSmudge) {
        final ValleyOfMirrors valley = new ValleyOfMirrors(INPUT_EXAMPLE_01, fixSmudge);
        assertEquals(expectedResult, valley.sumAllPatternNotes());
    }
}