package nl.smerik.adventofcode.aoc2019.model.arcade;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArcadeCabinetTest {

    @Mock
    private IntcodeComputer intcodeComputer;

    @Test
    void Day13Part01Test01() {
        final ArcadeCabinet arcadeCabinet = new ArcadeCabinet(intcodeComputer);
        Mockito.when(intcodeComputer.run()).thenReturn(List.of(1L, 2L, 3L, 6L, 5L, 4L));

        arcadeCabinet.start();

        final Map<Point, Tile> tiles = arcadeCabinet.getScreen().getTiles();
        assertEquals(2, tiles.size());
        assertEquals(Tile.Type.HORIZONTAL_PADDLE, tiles.get(new Point(1, 2)).getType());
        assertEquals(Tile.Type.BALL, tiles.get(new Point(6, 5)).getType());
    }
}