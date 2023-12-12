package nl.smerik.adventofcode.aoc2023.model.maze.pipe;

import java.awt.*;
import java.util.Set;

public record MazeTile(Point point, TileType type) {

    public boolean canConnectTo(final MazeTile tile) {
        return getConnectablePoints().contains(tile.point) && tile.getConnectablePoints().contains(point);
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
