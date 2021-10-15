package nl.smerik.adventofcode.aoc2020.model.jigsaw;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TileTest {

    private static final List<String> TILE_2311_LINES = List.of(
            "Tile 2311:",
            "..##.#..#.",
            "##..#.....",
            "#...##..#.",
            "####.#...#",
            "##.##.###.",
            "##...#.###",
            ".#.#.#..##",
            "..#....#..",
            "###...#.#.",
            "..###..###"
    );

    private static final List<String> TILE_1951_LINES = List.of(
            "Tile 1951:",
            "#.##...##.",
            "#.####...#",
            ".....#..##",
            "#...######",
            ".##.#....#",
            ".###.#####",
            "###.##.##.",
            ".###....#.",
            "..#.#..#.#",
            "#...##.#.."
    );

    private static final List<String> TILE_1171_LINES = List.of(
            "Tile 1171:",
            "####...##.",
            "#..##.#..#",
            "##.#..#.#.",
            ".###.####.",
            "..###.####",
            ".##....##.",
            ".#...####.",
            "#.##.####.",
            "####..#...",
            ".....##...");

    private static final List<String> TILE_1427_LINES = List.of(
            "Tile 1427:",
            "###.##.#..",
            ".#..#.##..",
            ".#.##.#..#",
            "#.#.#.##.#",
            "....#...##",
            "...##..##.",
            "...#.#####",
            ".#.####.#.",
            "..#..###.#",
            "..##.#..#."
    );

    private static final List<String> TILE_1489_LINES = List.of(
            "Tile 1489:",
            "##.#.#....",
            "..##...#..",
            ".##..##...",
            "..#...#...",
            "#####...#.",
            "#..#.#.#.#",
            "...#.#.#..",
            "##.#...##.",
            "..##.##.##",
            "###.##.#..");

    private static final List<String> TILE_2473_LINES = List.of(
            "Tile 2473:",
            "#....####.",
            "#..#.##...",
            "#.##..#...",
            "######.#.#",
            ".#...#.#.#",
            ".#########",
            ".###.#..#.",
            "########.#",
            "##...##.#.",
            "..###.#.#.");

    private static final List<String> TILE_2971_LINES = List.of(
            "Tile 2971:",
            "..#.#....#",
            "#...###...",
            "#.#.###...",
            "##.##..#..",
            ".#####..##",
            ".#..####.#",
            "#..#.#..#.",
            "..####.###",
            "..#.#.###.",
            "...#.#.#.#");

    private static final List<String> TILE_2729_LINES = List.of(
            "Tile 2729:",
            "...#.#.#.#",
            "####.#....",
            "..#.#.....",
            "....#..#.#",
            ".##..##.#.",
            ".#.####...",
            "####.#.#..",
            "##.####...",
            "##..#.##..",
            "#.##...##.");

    private static final List<String> TILE_3079_LINES = List.of(
            "Tile 3079:",
            "#.#.#####.",
            ".#..######",
            "..#.......",
            "######....",
            "####.#..#.",
            ".#...#.##.",
            "#.#####.##",
            "..#.###...",
            "..#.......",
            "..#.###...");


    private Tile tile2311;
    private Tile tile1951;
    private Tile tile1171;
    private Tile tile1427;
    private Tile tile1489;
    private Tile tile2473;
    private Tile tile2971;
    private Tile tile2729;
    private Tile tile3079;
    private Set<Tile> tiles;

    @BeforeEach
    void beforeEach() {
        tile2311 = new Tile(TILE_2311_LINES);
        tile1951 = new Tile(TILE_1951_LINES);
        tile1171 = new Tile(TILE_1171_LINES);
        tile1427 = new Tile(TILE_1427_LINES);
        tile1489 = new Tile(TILE_1489_LINES);
        tile2473 = new Tile(TILE_2473_LINES);
        tile2971 = new Tile(TILE_2971_LINES);
        tile2729 = new Tile(TILE_2729_LINES);
        tile3079 = new Tile(TILE_3079_LINES);
        tiles = Set.of(tile2311, tile1951, tile1171, tile1427, tile1489, tile2473, tile2971, tile2729, tile3079);
    }

    @Test
    void testNoTileId() {
        final List<String> lines = List.of("Tile:");
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> new Tile(lines));
        assertEquals("Invalid line for tile id 'Tile:'", exception.getMessage());
    }

    @Test
    void testId() {
        assertEquals(1427, tile1427.getId());
    }

    @Test
    void testAddAdjacent() {
        // Sanity check
        assertTrue(tile1951.getAdjacentTiles().isEmpty());
        assertTrue(tile2311.getAdjacentTiles().isEmpty());

        tile1951.addAdjacent(tile2311);

        assertEquals(1, tile1951.getAdjacentTiles().size());
        assertEquals(1, tile2311.getAdjacentTiles().size());
        assertTrue(tile1951.getAdjacentTiles().contains(tile2311));
        assertTrue(tile2311.getAdjacentTiles().contains(tile1951));
    }

    @Test
    void testIsCorner() {
        linkTilesByExample01();
        assertTrue(tile1951.isCorner());
        assertFalse(tile2311.isCorner());
        assertTrue(tile3079.isCorner());
        assertFalse(tile2729.isCorner());
        assertFalse(tile1427.isCorner());
        assertFalse(tile2473.isCorner());
        assertTrue(tile2971.isCorner());
        assertFalse(tile1489.isCorner());
        assertTrue(tile1171.isCorner());
    }

    private void linkTilesByExample01() {
        tile1951.addAdjacent(tile2311);
        tile1951.addAdjacent(tile2729);

        tile3079.addAdjacent(tile2311);
        tile3079.addAdjacent(tile2473);

        tile2971.addAdjacent(tile2729);
        tile2971.addAdjacent(tile1489);

        tile1171.addAdjacent(tile2473);
        tile1171.addAdjacent(tile1489);

        tile1427.addAdjacent(tile2311);
        tile1427.addAdjacent(tile2729);
        tile1427.addAdjacent(tile2473);
        tile1427.addAdjacent(tile1489);
    }

    private static Stream<Arguments> provideSourceForTestIsAdjacent() {
        return Stream.of(
                // @formatter:off
                Arguments.of(false, TILE_1951_LINES, TILE_1951_LINES),
                Arguments.of(true , TILE_1951_LINES, TILE_2311_LINES),
                Arguments.of(false, TILE_1951_LINES, TILE_3079_LINES),
                Arguments.of(true , TILE_1951_LINES, TILE_2729_LINES),
                Arguments.of(false, TILE_1951_LINES, TILE_1427_LINES),
                Arguments.of(false, TILE_1951_LINES, TILE_2473_LINES),
                Arguments.of(false, TILE_1951_LINES, TILE_2971_LINES),
                Arguments.of(false, TILE_1951_LINES, TILE_1489_LINES),
                Arguments.of(false, TILE_1951_LINES, TILE_1171_LINES),

                Arguments.of(true , TILE_2311_LINES, TILE_1951_LINES),
                Arguments.of(false, TILE_2311_LINES, TILE_2311_LINES),
                Arguments.of(true , TILE_2311_LINES, TILE_3079_LINES),
                Arguments.of(false, TILE_2311_LINES, TILE_2729_LINES),
                Arguments.of(true , TILE_2311_LINES, TILE_1427_LINES),
                Arguments.of(false, TILE_2311_LINES, TILE_2473_LINES),
                Arguments.of(false, TILE_2311_LINES, TILE_2971_LINES),
                Arguments.of(false, TILE_2311_LINES, TILE_1489_LINES),
                Arguments.of(false, TILE_2311_LINES, TILE_1171_LINES),

                Arguments.of(false, TILE_3079_LINES, TILE_1951_LINES),
                Arguments.of(true , TILE_3079_LINES, TILE_2311_LINES),
                Arguments.of(false, TILE_3079_LINES, TILE_3079_LINES),
                Arguments.of(false, TILE_3079_LINES, TILE_2729_LINES),
                Arguments.of(false, TILE_3079_LINES, TILE_1427_LINES),
                Arguments.of(true , TILE_3079_LINES, TILE_2473_LINES),
                Arguments.of(false, TILE_3079_LINES, TILE_2971_LINES),
                Arguments.of(false, TILE_3079_LINES, TILE_1489_LINES),
                Arguments.of(false, TILE_3079_LINES, TILE_1171_LINES),

                Arguments.of(true , TILE_2729_LINES, TILE_1951_LINES),
                Arguments.of(false, TILE_2729_LINES, TILE_2311_LINES),
                Arguments.of(false, TILE_2729_LINES, TILE_3079_LINES),
                Arguments.of(false, TILE_2729_LINES, TILE_2729_LINES),
                Arguments.of(true , TILE_2729_LINES, TILE_1427_LINES),
                Arguments.of(false, TILE_2729_LINES, TILE_2473_LINES),
                Arguments.of(true , TILE_2729_LINES, TILE_2971_LINES),
                Arguments.of(false, TILE_2729_LINES, TILE_1489_LINES),
                Arguments.of(false, TILE_2729_LINES, TILE_1171_LINES),

                Arguments.of(false, TILE_1427_LINES, TILE_1951_LINES),
                Arguments.of(true , TILE_1427_LINES, TILE_2311_LINES),
                Arguments.of(false, TILE_1427_LINES, TILE_3079_LINES),
                Arguments.of(true , TILE_1427_LINES, TILE_2729_LINES),
                Arguments.of(false, TILE_1427_LINES, TILE_1427_LINES),
                Arguments.of(true , TILE_1427_LINES, TILE_2473_LINES),
                Arguments.of(false, TILE_1427_LINES, TILE_2971_LINES),
                Arguments.of(true , TILE_1427_LINES, TILE_1489_LINES),
                Arguments.of(false, TILE_1427_LINES, TILE_1171_LINES),

                Arguments.of(false, TILE_2473_LINES, TILE_1951_LINES),
                Arguments.of(false, TILE_2473_LINES, TILE_2311_LINES),
                Arguments.of(true , TILE_2473_LINES, TILE_3079_LINES),
                Arguments.of(false, TILE_2473_LINES, TILE_2729_LINES),
                Arguments.of(true , TILE_2473_LINES, TILE_1427_LINES),
                Arguments.of(false, TILE_2473_LINES, TILE_2473_LINES),
                Arguments.of(false, TILE_2473_LINES, TILE_2971_LINES),
                Arguments.of(false, TILE_2473_LINES, TILE_1489_LINES),
                Arguments.of(true , TILE_2473_LINES, TILE_1171_LINES),

                Arguments.of(false, TILE_2971_LINES, TILE_1951_LINES),
                Arguments.of(false, TILE_2971_LINES, TILE_2311_LINES),
                Arguments.of(false, TILE_2971_LINES, TILE_3079_LINES),
                Arguments.of(true , TILE_2971_LINES, TILE_2729_LINES),
                Arguments.of(false, TILE_2971_LINES, TILE_1427_LINES),
                Arguments.of(false, TILE_2971_LINES, TILE_2473_LINES),
                Arguments.of(false, TILE_2971_LINES, TILE_2971_LINES),
                Arguments.of(true , TILE_2971_LINES, TILE_1489_LINES),
                Arguments.of(false, TILE_2971_LINES, TILE_1171_LINES),

                Arguments.of(false, TILE_1489_LINES, TILE_1951_LINES),
                Arguments.of(false, TILE_1489_LINES, TILE_2311_LINES),
                Arguments.of(false, TILE_1489_LINES, TILE_3079_LINES),
                Arguments.of(false, TILE_1489_LINES, TILE_2729_LINES),
                Arguments.of(true , TILE_1489_LINES, TILE_1427_LINES),
                Arguments.of(false, TILE_1489_LINES, TILE_2473_LINES),
                Arguments.of(true , TILE_1489_LINES, TILE_2971_LINES),
                Arguments.of(false, TILE_1489_LINES, TILE_1489_LINES),
                Arguments.of(true , TILE_1489_LINES, TILE_1171_LINES),

                Arguments.of(false, TILE_1171_LINES, TILE_1951_LINES),
                Arguments.of(false, TILE_1171_LINES, TILE_2311_LINES),
                Arguments.of(false, TILE_1171_LINES, TILE_3079_LINES),
                Arguments.of(false, TILE_1171_LINES, TILE_2729_LINES),
                Arguments.of(false, TILE_1171_LINES, TILE_1427_LINES),
                Arguments.of(true , TILE_1171_LINES, TILE_2473_LINES),
                Arguments.of(false, TILE_1171_LINES, TILE_2971_LINES),
                Arguments.of(true , TILE_1171_LINES, TILE_1489_LINES),
                Arguments.of(false, TILE_1171_LINES, TILE_1171_LINES)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForTestIsAdjacent")
    void testIsAdjacent(final boolean expectedMatch, final List<String> tileLines, final List<String> tileToMatchLines) {
        final Tile tile = new Tile(tileLines);
        final Tile tileToMatch = new Tile(tileToMatchLines);
        assertEquals(expectedMatch, tile.isAdjacent(tileToMatch),
                "tile #" + tile.getId() + " should match to tile #" + tileToMatch.getId() + "? " + expectedMatch);
    }

    @Test
    void testRotateCCW() {
        tile1427.rotateCCW();
        assertEquals("..###.#.#.", new String(tile1427.getContent()[0]));
        assertEquals("....####.#", new String(tile1427.getContent()[1]));
        assertEquals("##.#.##.#.", new String(tile1427.getContent()[2]));
        assertEquals(".###..###.", new String(tile1427.getContent()[3]));
        assertEquals("#.....####", new String(tile1427.getContent()[4]));
        assertEquals("######.#..", new String(tile1427.getContent()[5]));
        assertEquals("..#..###.#", new String(tile1427.getContent()[6]));
        assertEquals("#..#....##", new String(tile1427.getContent()[7]));
        assertEquals("###....#..", new String(tile1427.getContent()[8]));
        assertEquals("#..#......", new String(tile1427.getContent()[9]));
    }

    @Test
    void testFlipHorizontally() {
        tile1427.flipHorizontally();
        assertEquals("..##.#..#.", new String(tile1427.getContent()[0]));
        assertEquals("..#..###.#", new String(tile1427.getContent()[1]));
        assertEquals(".#.####.#.", new String(tile1427.getContent()[2]));
        assertEquals("...#.#####", new String(tile1427.getContent()[3]));
        assertEquals("...##..##.", new String(tile1427.getContent()[4]));
        assertEquals("....#...##", new String(tile1427.getContent()[5]));
        assertEquals("#.#.#.##.#", new String(tile1427.getContent()[6]));
        assertEquals(".#.##.#..#", new String(tile1427.getContent()[7]));
        assertEquals(".#..#.##..", new String(tile1427.getContent()[8]));
        assertEquals("###.##.#..", new String(tile1427.getContent()[9]));
    }

    @Test
    void testMatchesTop() {
        // Arrange tiles as shown in example 01
        tile2311.flipHorizontally();
        tile1427.flipHorizontally();

        assertFalse(tile2311.matchesTop(tile1427));
        assertTrue(tile1427.matchesTop(tile2311));
    }

    @Test
    void testMatchesBottom() {
        // Arrange tiles as shown in example 01
        tile2311.flipHorizontally();
        tile1427.flipHorizontally();

        assertTrue(tile2311.matchesBottom(tile1427));
        assertFalse(tile1427.matchesBottom(tile2311));
    }

    @Test
    void testMatchesLeft() {
        // Arrange tiles as shown in example 01
        tile2473.flipHorizontally();
        tile2473.rotateCCW();
        tile1427.flipHorizontally();

        assertTrue(tile2473.matchesLeft(tile1427));
        assertFalse(tile1427.matchesLeft(tile2473));
    }

    @Test
    void testMatchesRight() {
        // Arrange tiles as shown in example 01
        tile2473.flipHorizontally();
        tile2473.rotateCCW();
        tile1427.flipHorizontally();

        assertFalse(tile2473.matchesRight(tile1427));
        assertTrue(tile1427.matchesRight(tile2473));
    }

    /**
     * Make sure that arranging is working for each given tile.
     *
     * @param tileId the initial tile to start arranging from
     */
    @ParameterizedTest
    @ValueSource(ints = {2311, 1951, 1171, 1427, 1489, 2473, 2971, 2729, 3079})
    void testArrangeAdjacentTiles(final int tileId) {
        linkTilesByExample01();
        final Tile tile = tiles.stream().filter(t -> t.getId() == tileId).findAny().orElseThrow();
        tile.setPosition(new Point());

        tile.arrangeAdjacentTiles(new HashSet<>());

        tiles.forEach(tileToAssert -> assertTrue(tileToAssert.isArranged(),
                "The following tile should be arranged:" + tileToAssert));
        assertEquals(20899048083289L, multiplyCornerTileIDs());
    }

    private Long multiplyCornerTileIDs() {
        return tiles.stream()
                    .filter(Tile::isCorner)
                    .flatMapToLong(tile -> LongStream.of(tile.getId()))
                    .reduce(1, (a, b) -> a * b);
    }

    @Test
    void testIsArranged() {
        assertFalse(tile1951.isArranged());

        tile1951.setPosition(new Point());
        assertTrue(tile1951.isArranged());
    }
}