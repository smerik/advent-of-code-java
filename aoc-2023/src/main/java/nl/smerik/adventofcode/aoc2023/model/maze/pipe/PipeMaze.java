package nl.smerik.adventofcode.aoc2023.model.maze.pipe;

import java.awt.*;
import java.util.List;
import java.util.*;

public class PipeMaze {

    private final Map<Point, Pipe> pipesByPoint;

    /**
     * DISCLAIMER: I tried to solve the maze using the JGraphT library.
     * It's not the most beautiful implementation, but it works...
     *
     * @param lines the puzzle input
     */
    public PipeMaze(final List<String> lines) {
        pipesByPoint = parseLines(lines);
    }

    private Map<Point, Pipe> parseLines(final List<String> lines) {
        final Map<Point, Pipe> result = new HashMap<>();
        int y = lines.size();
        for (final String line : lines) {
            y--;
            final char[] tiles = line.toCharArray();
            for (int x = 0; x < tiles.length; x++) {
                final TileType tileType = TileType.valueOfTile(tiles[x]);
                if (TileType.GROUND == tileType) {
                    // We don't need to store ground positions
                    continue;
                }
                final Point point = new Point(x, y);
                result.put(point, new Pipe(point, tileType));
            }
        }
        return result;
    }

    public int determineStepCountFromStartToFarthestPoint() {
        final List<Point> loop = findSingleGiantLoop();
        return loop.size() / 2;
    }

    public List<Point> findSingleGiantLoop() {
        final List<Point> path = new ArrayList<>();
        final Pipe startPipe = pipesByPoint.values().stream().filter(pipe -> pipe.getType() == TileType.STARTING_POSITION).findAny().orElseThrow();
        Pipe currentPipe = startPipe;
        while (!path.contains(currentPipe.getPoint())) {
            path.add(currentPipe.getPoint());
            Optional<Pipe> nextPossiblePipe = currentPipe.getConnectablePoints().stream().filter(pipesByPoint::containsKey).map(pipesByPoint::get).filter(currentPipe::canConnectTo).filter(pipe -> !path.contains(pipe.getPoint())).findFirst();
            if (nextPossiblePipe.isEmpty()) {
                break;
            }
            currentPipe = nextPossiblePipe.get();
        }
        return path;
    }
}
