package nl.smerik.adventofcode.aoc2020.model.lobby;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the class {@link LobbyLayout}.
 */
class LobbyLayoutTest {

    private static final List<String> EXAMPLE_01 = List.of(
            "sesenwnenenewseeswwswswwnenewsewsw",
            "neeenesenwnwwswnenewnwwsewnenwseswesw",
            "seswneswswsenwwnwse",
            "nwnwneseeswswnenewneswwnewseswneseene",
            "swweswneswnenwsewnwneneseenw",
            "eesenwseswswnenwswnwnwsewwnwsene",
            "sewnenenenesenwsewnenwwwse",
            "wenwwweseeeweswwwnwwe",
            "wsweesenenewnwwnwsenewsenwwsesesenwne",
            "neeswseenwwswnwswswnw",
            "nenwswwsewswnenenewsenwsenwnesesenew",
            "enewnwewneswsewnwswenweswnenwsenwsw",
            "sweneswneswneneenwnewenewwneswswnese",
            "swwesenesewenwneswnwwneseswwne",
            "enesenwswwswneneswsenwnewswseenwsese",
            "wnwnesenesenenwwnenwsewesewsesesew",
            "nenewswnwewswnenesenwnesewesw",
            "eneswnwswnwsenenwnwnwwseeswneewsenese",
            "neswnwewnwnwseenwseesewsenwsweewe",
            "wseweeenwnesenwwwswnew"
    );

    @Test
    void testSolutionPart01() {
        final LobbyLayout lobby = new LobbyLayout(EXAMPLE_01);
        assertEquals(10, lobby.getTilesByPosition().values().stream().filter(t -> t.getFlipCount() == 1).count());
        assertEquals(5, lobby.getTilesByPosition().values().stream().filter(t -> t.getFlipCount() == 2).count());
        assertEquals(10, lobby.countTilesWithColorUp(TileColor.BLACK));
    }


    private static Stream<Arguments> examplePart02() {
        return Stream.of(
                // @formatter:off
                Arguments.of(  1,   15),
                Arguments.of(  2,   12),
                Arguments.of(  3,   25),
                Arguments.of(  4,   14),
                Arguments.of(  5,   23),
                Arguments.of(  6,   28),
                Arguments.of(  7,   41),
                Arguments.of(  8,   37),
                Arguments.of(  9,   49),
                Arguments.of( 10,   37),
                Arguments.of( 20,  132),
                Arguments.of( 30,  259),
                Arguments.of( 40,  406),
                Arguments.of( 50,  566),
                Arguments.of( 60,  788),
                Arguments.of( 70, 1106),
                Arguments.of( 80, 1373),
                Arguments.of( 90, 1844),
                Arguments.of(100, 2208)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("examplePart02")
    void testFlipTiles(final int days, final int expectedBlackTiles) {
        final LobbyLayout lobby = new LobbyLayout(EXAMPLE_01);
        lobby.flipTiles(days);
        assertEquals(expectedBlackTiles, lobby.countTilesWithColorUp(TileColor.BLACK));
    }
}