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
        // Find start pipe and make it current
        Pipe currentPipe = pipesByPoint.values().stream().filter(pipe1 -> pipe1.getType() == TileType.STARTING_POSITION).findAny().orElseThrow();
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

    public String render(final boolean markLoop) {
        final List<Point> loopPoints = markLoop ? findSingleGiantLoop() : Collections.emptyList();

        final StringBuilder builder = new StringBuilder();
        final int minX = this.pipesByPoint.keySet().stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = this.pipesByPoint.keySet().stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = this.pipesByPoint.keySet().stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = this.pipesByPoint.keySet().stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;

        for (int y = maxY ; y >= minY; y--) {
            for (int x = minX ; x <= maxX; x++) {
                final Pipe pipe = pipesByPoint.get(new Point(x, y));
                builder.append(loopPoints.contains(pipe.getPoint()) ? pipe.getRenderLoopToken() : pipe.getRenderToken());
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
