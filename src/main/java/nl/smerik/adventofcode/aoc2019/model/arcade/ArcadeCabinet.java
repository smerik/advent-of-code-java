package nl.smerik.adventofcode.aoc2019.model.arcade;

import lombok.Getter;
import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;

import java.awt.Point;
import java.util.List;

@Getter
public class ArcadeCabinet {

    private final IntcodeComputer computer;

    private final Screen screen;

    public ArcadeCabinet(final IntcodeComputer computer) {
        this.computer = computer;
        this.screen = new Screen();
    }

    public void draw() {
        final List<Long> output = this.computer.run();
        for (int i = 0; i < output.size(); i = i + 3) {
            final Point point = new Point(Math.toIntExact(output.get(i)), Math.toIntExact(output.get(i + 1)));
            final Tile.Type type = Tile.Type.valueOfTileTypeId(Math.toIntExact(output.get(i + 2)));
            screen.draw(point, type);
        }
    }
}
