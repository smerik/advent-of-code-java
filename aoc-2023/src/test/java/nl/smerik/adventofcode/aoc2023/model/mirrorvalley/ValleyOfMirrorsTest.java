package nl.smerik.adventofcode.aoc2023.model.mirrorvalley;

import org.junit.jupiter.api.Test;

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

    @Test
    void testSumAllPatternNotes() {
        final ValleyOfMirrors valley = new ValleyOfMirrors(INPUT_EXAMPLE_01);
        assertEquals(405, valley.sumAllPatternNotes());
    }
}