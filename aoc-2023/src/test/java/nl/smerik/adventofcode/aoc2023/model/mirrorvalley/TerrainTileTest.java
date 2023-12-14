package nl.smerik.adventofcode.aoc2023.model.mirrorvalley;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TerrainTileTest {

    private static final List<String> PATTERN_EXAMPLE_01 = List.of(
            // @formatter:off
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#."
            // @formatter:on
    );

    private static final List<String> PATTERN_EXAMPLE_02 = List.of(
            // @formatter:off
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#"
            // @formatter:on
    );

    // Pattern width > height
    private static final List<String> PATTERN_VERTICAL_LAST = List.of(
            // @formatter:off
            ".#..",
            "..##",
            ".###"
            // @formatter:on
    );

    // Pattern height > width
    private static final List<String> PATTERN_HORIZONTAL_LAST = List.of(
            // @formatter:off
            "..##.###.",
            "##..#.#.#",
            ".#..##..#",
            "##..#####",
            "#.#..#.#.",
            ".###.#.#.",
            "###.#...#",
            "..#.#.#..",
            "...##.#..",
            "...##.#.."
            // @formatter:on
    );

    @Test
    void testFixSmudgeExample01() {
        final TerrainTile tile = new TerrainTile(PATTERN_EXAMPLE_01, true);
        final String expectedPattern =
                // @formatter:off
                "..##..##." + System.lineSeparator() +
                "..#.##.#." + System.lineSeparator() +
                "##......#" + System.lineSeparator() +
                "##......#" + System.lineSeparator() +
                "..#.##.#." + System.lineSeparator() +
                "..##..##." + System.lineSeparator() +
                "#.#.##.#." + System.lineSeparator();
                // @formatter:on
        final String pattern = tile.render();
        assertEquals(expectedPattern, pattern);
    }

    @Test
    void testFixSmudgeExample02() {
        final TerrainTile tile = new TerrainTile(PATTERN_EXAMPLE_02, true);
        // Expected pattern is different from story example
        final String expectedPattern =
                // @formatter:off
                "#....#..#" + System.lineSeparator() +
                "#....#..#" + System.lineSeparator() +
                "..##..###" + System.lineSeparator() +
                "#####.##." + System.lineSeparator() +
                "#####.##." + System.lineSeparator() +
                "..##..###" + System.lineSeparator() +
                "#....#..#" + System.lineSeparator();
                // @formatter:on
        final String pattern = tile.render();
        assertEquals(expectedPattern, pattern);
    }

    @ParameterizedTest
    @CsvSource({
            // @formatter:off
            "., 0, 0",
            "#, 1, 0"
            // @formatter:on
    })
    void testFlipSmudge(final char expectedToken, final int x, final int y) {
        final TerrainTile tile = new TerrainTile(PATTERN_EXAMPLE_01, false);
        tile.flipSmudge(x, y);
        assertEquals(expectedToken, tile.getHorizontalLine(y).charAt(x));
    }

    public static Stream<Arguments> sumPatternNotesSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(  5, PATTERN_EXAMPLE_01, false),
                Arguments.of(300, PATTERN_EXAMPLE_01, true ),
                Arguments.of(400, PATTERN_EXAMPLE_02, false),
                Arguments.of(100, PATTERN_EXAMPLE_02, true )
                // @formatter:on1
        );
    }

    @ParameterizedTest
    @MethodSource("sumPatternNotesSource")
    void testSumPatternNotes(final int expectedResult, final List<String> lines, final boolean fixSmudge) {
        final TerrainTile tile = new TerrainTile(lines, fixSmudge);
        assertEquals(expectedResult, tile.sumPatternNotes());
    }

    public static Stream<Arguments> findVerticalReflectionIndexSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 5, PATTERN_EXAMPLE_01     , false),
                Arguments.of( 0, PATTERN_EXAMPLE_01     , true ), // the old reflection should be ignored after fixing the smudge
                Arguments.of( 0, PATTERN_EXAMPLE_02     , false),
                Arguments.of( 0, PATTERN_EXAMPLE_02     , true ),
                Arguments.of( 3, PATTERN_VERTICAL_LAST  , false),
                Arguments.of( 0, PATTERN_HORIZONTAL_LAST, false)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("findVerticalReflectionIndexSource")
    void testFindVerticalReflectionIndex(final int expectedResult, final List<String> lines, final boolean fixSmudge) {
        final TerrainTile tile = new TerrainTile(lines, fixSmudge);
        assertEquals(expectedResult, tile.findVerticalReflectionIndex());
    }

    public static Stream<Arguments> findHorizontalReflectionIndexSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(0, PATTERN_EXAMPLE_01     , false),
                Arguments.of(3, PATTERN_EXAMPLE_01     , true ),
                Arguments.of(4, PATTERN_EXAMPLE_02     , false),
                Arguments.of(1, PATTERN_EXAMPLE_02     , true ),
                Arguments.of(0, PATTERN_VERTICAL_LAST  , false),
                Arguments.of(9, PATTERN_HORIZONTAL_LAST, false)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("findHorizontalReflectionIndexSource")
    void testFindHorizontalReflectionIndex(final int expectedResult, final List<String> lines, final boolean fixSmudge) {
        final TerrainTile tile = new TerrainTile(lines, fixSmudge);
        assertEquals(expectedResult, tile.findHorizontalReflectionIndex());
    }
}