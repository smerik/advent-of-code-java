package nl.smerik.adventofcode.aoc2019.model.arcade;

import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

class ScreenTest {

    @Test
    void countTilesByType() {
        final Screen screen = new Screen();
        screen.draw(new Point(1, 1), Tile.Type.BALL);

        screen.draw(new Point(2, 1), Tile.Type.HORIZONTAL_PADDLE);
        screen.draw(new Point(2, 2), Tile.Type.HORIZONTAL_PADDLE);

        screen.draw(new Point(3, 1), Tile.Type.BLOCK);
        screen.draw(new Point(3, 2), Tile.Type.BLOCK);
        screen.draw(new Point(3, 3), Tile.Type.BLOCK);

        screen.draw(new Point(4, 1), Tile.Type.WALL);
        screen.draw(new Point(4, 2), Tile.Type.WALL);
        screen.draw(new Point(4, 3), Tile.Type.WALL);
        screen.draw(new Point(4, 4), Tile.Type.WALL);

        assertEquals(0, screen.countTilesByType(Tile.Type.EMPTY));
        assertEquals(1, screen.countTilesByType(Tile.Type.BALL));
        assertEquals(2, screen.countTilesByType(Tile.Type.HORIZONTAL_PADDLE));
        assertEquals(3, screen.countTilesByType(Tile.Type.BLOCK));
        assertEquals(4, screen.countTilesByType(Tile.Type.WALL));
    }
}