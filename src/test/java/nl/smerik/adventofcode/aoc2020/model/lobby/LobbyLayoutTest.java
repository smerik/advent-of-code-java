package nl.smerik.adventofcode.aoc2020.model.lobby;

import org.junit.jupiter.api.Test;

import java.util.List;

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
}