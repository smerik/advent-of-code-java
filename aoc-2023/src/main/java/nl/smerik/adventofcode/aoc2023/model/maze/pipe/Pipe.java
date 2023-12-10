package nl.smerik.adventofcode.aoc2023.model.maze.pipe;

import lombok.Getter;
import lombok.ToString;

import java.awt.*;
import java.util.Set;

@ToString
@Getter
public class Pipe {

    private final Point point;
    private final TileType type;

    public Pipe(final Point point, final TileType type) {
        this.point = point;
        this.type = type;
    }

    public boolean canConnectTo(final Pipe pipe) {
        return getConnectablePoints().contains(pipe.point) && pipe.getConnectablePoints().contains(point);
    }

    public Set<Point> getConnectablePoints() {
        return type.getConnectablePoints(point);
    }

    public char getRenderToken() {
        return type.getRenderToken();
    }

    public char getRenderLoopToken() {
        return type.getRenderLoopToken();
    }
}
