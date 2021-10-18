package nl.smerik.adventofcode.aoc2020.model.jigsaw;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SeaMonsterTest {

    @Test
    void testGetSeaMonsterCoordinates() {
        final SeaMonster monster = new SeaMonster();
        final Set<Point> expectedCoordinates = Set.of(
                new Point(18, 0),
                new Point(0, 1),
                new Point(5, 1),
                new Point(6, 1),
                new Point(11, 1),
                new Point(12, 1),
                new Point(17, 1),
                new Point(18, 1),
                new Point(19, 1),
                new Point(1, 2),
                new Point(4, 2),
                new Point(7, 2),
                new Point(10, 2),
                new Point(13, 2),
                new Point(16, 2)
        );
        assertEquals(expectedCoordinates, monster.getSeaMonsterCoordinates());
    }

    @Test
    void testRotateCW() {
        final SeaMonster monster = new SeaMonster();
        monster.rotateCW();

        final Set<Point> expectedCoordinates = Set.of(
                new Point(1, 0),
                new Point(0, 1),
                new Point(0, 4),
                new Point(1, 5),
                new Point(1, 6),
                new Point(0, 7),
                new Point(0, 10),
                new Point(1, 11),
                new Point(1, 12),
                new Point(0, 13),
                new Point(0, 16),
                new Point(1, 17),
                new Point(1, 18),
                new Point(2, 18),
                new Point(1, 19)
        );
        assertEquals(expectedCoordinates, monster.getSeaMonsterCoordinates());
    }

    @Test
    void testFlipHorizontally() {
        final SeaMonster monster = new SeaMonster();
        monster.flipHorizontally();
        
        final Set<Point> expectedCoordinates = Set.of(
                new Point(1, 0),
                new Point(4, 0),
                new Point(7, 0),
                new Point(10, 0),
                new Point(13, 0),
                new Point(16, 0),
                new Point(0, 1),
                new Point(5, 1),
                new Point(6, 1),
                new Point(11, 1),
                new Point(12, 1),
                new Point(17, 1),
                new Point(18, 1),
                new Point(19, 1),
                new Point(18, 2)
        );
        assertEquals(expectedCoordinates, monster.getSeaMonsterCoordinates());
    }

    @Test
    void testTranslate() {
        final SeaMonster monster = new SeaMonster();
        final Set<Point> originalCoordinates = Set.of(
                new Point(18, 0),
                new Point(0, 1),
                new Point(5, 1),
                new Point(6, 1),
                new Point(11, 1),
                new Point(12, 1),
                new Point(17, 1),
                new Point(18, 1),
                new Point(19, 1),
                new Point(1, 2),
                new Point(4, 2),
                new Point(7, 2),
                new Point(10, 2),
                new Point(13, 2),
                new Point(16, 2)
        );
        final Set<Point> expectedTranslatedCoordinates = Set.of(
                new Point(28, 1),
                new Point(10, 2),
                new Point(15, 2),
                new Point(16, 2),
                new Point(21, 2),
                new Point(22, 2),
                new Point(27, 2),
                new Point(28, 2),
                new Point(29, 2),
                new Point(11, 3),
                new Point(14, 3),
                new Point(17, 3),
                new Point(20, 3),
                new Point(23, 3),
                new Point(26, 3)
        );
        final Set<Point> translatedCoordinates = monster.translate(10, 1);

        assertEquals(originalCoordinates, monster.getSeaMonsterCoordinates());
        assertEquals(expectedTranslatedCoordinates, translatedCoordinates);
    }

    @Test
    void testBuildImage() {
        final SeaMonster monster = new SeaMonster();
        // @formatter:off
        final String expectedImage = "                  # " + System.lineSeparator()
                                   + "#    ##    ##    ###" + System.lineSeparator()
                                   + " #  #  #  #  #  #   ";
        // @formatter:on
        assertEquals(expectedImage, monster.buildImage());
    }
}