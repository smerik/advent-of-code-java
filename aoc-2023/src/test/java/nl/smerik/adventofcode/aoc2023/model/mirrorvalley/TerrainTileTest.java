package nl.smerik.adventofcode.aoc2023.model.mirrorvalley;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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

    public static Stream<Arguments> sumPatternNotesSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(  5, PATTERN_EXAMPLE_01),
                Arguments.of(400, PATTERN_EXAMPLE_02)
                // @formatter:on1
        );
    }

    @ParameterizedTest
    @MethodSource("sumPatternNotesSource")
    void testSumPatternNotes(final int expectedResult, final List<String> lines) {
        final TerrainTile tile = new TerrainTile(lines);
        assertEquals(expectedResult, tile.sumPatternNotes());
    }

    public static Stream<Arguments> findVerticalReflectionIndexSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 5, PATTERN_EXAMPLE_01),
                Arguments.of( 0, PATTERN_EXAMPLE_02),
                Arguments.of( 3, PATTERN_VERTICAL_LAST),
                Arguments.of( 0, PATTERN_HORIZONTAL_LAST)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("findVerticalReflectionIndexSource")
    void testFindVerticalReflectionIndex(final int expectedResult, final List<String> lines) {
        final TerrainTile tile = new TerrainTile(lines);
        assertEquals(expectedResult, tile.findVerticalReflectionIndex());
    }

    public static Stream<Arguments> findHorizontalReflectionIndexSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(0, PATTERN_EXAMPLE_01),
                Arguments.of(4, PATTERN_EXAMPLE_02),
                Arguments.of( 0, PATTERN_VERTICAL_LAST),
                Arguments.of( 9, PATTERN_HORIZONTAL_LAST)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("findHorizontalReflectionIndexSource")
    void testFindHorizontalReflectionIndex(final int expectedResult, final List<String> lines) {
        final TerrainTile tile = new TerrainTile(lines);
        assertEquals(expectedResult, tile.findHorizontalReflectionIndex());
    }
}