package nl.smerik.adventofcode.aoc2023.model.maze.pipe;

import lombok.Getter;

import java.awt.*;
import java.util.Set;

@Getter
public enum TileType {

    PIPE_VERTICAL('|', '│', '║'),
    PIPE_HORIZONTAL('-', '─', '═'),
    PIPE_BEND_NORTH_EAST('L', '└', '╚'),
    PIPE_BEND_NORTH_WEST('J', '┘', '╝'),
    PIPE_BEND_SOUTH_WEST('7', '┐', '╗'),
    PIPE_BEND_SOUTH_EAST('F', '┌', '╔'),
    GROUND('.', '·', '·'),
    STARTING_POSITION('S', 'S', 'S');

    private final char pipeTile;
    private final char renderToken;
    private final char renderLoopToken;

    TileType(final char pipeTile, final char renderToken, char renderLoopToken) {
        this.pipeTile = pipeTile;
        this.renderToken = renderToken;
        this.renderLoopToken = renderLoopToken;
    }

    public Set<Point> getConnectablePoints(final Point point) {
        return switch (this) {
            case PIPE_VERTICAL -> Set.of(new Point(point.x, point.y - 1), new Point(point.x, point.y + 1));
            case PIPE_HORIZONTAL -> Set.of(new Point(point.x - 1, point.y), new Point(point.x + 1, point.y));
            case PIPE_BEND_NORTH_EAST -> Set.of(new Point(point.x, point.y + 1), new Point(point.x + 1, point.y));
            case PIPE_BEND_NORTH_WEST -> Set.of(new Point(point.x, point.y + 1), new Point(point.x - 1, point.y));
            case PIPE_BEND_SOUTH_WEST -> Set.of(new Point(point.x, point.y - 1), new Point(point.x - 1, point.y));
            case PIPE_BEND_SOUTH_EAST -> Set.of(new Point(point.x, point.y - 1), new Point(point.x + 1, point.y));
            case STARTING_POSITION ->
                    Set.of(new Point(point.x, point.y + 1), new Point(point.x + 1, point.y), new Point(point.x, point.y - 1), new Point(point.x - 1, point.y));
            default -> Set.of();
        };
    }

    public static TileType valueOfTile(final char tile) {
        for (final TileType value : values()) {
            if (value.pipeTile == tile) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown tile '" + tile + "'");
    }
}
