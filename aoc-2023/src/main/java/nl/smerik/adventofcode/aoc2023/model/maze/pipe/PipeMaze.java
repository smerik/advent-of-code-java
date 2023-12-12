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

    public int countTilesEnclosedByLoop() {
        return findTilesEnclosedByLoop().size();
    }

    /**
     * TODO: reimplement this; The code is unmaintainable & has slow performance (~4 sec. for day solution)
     *
     * @return the tile points that are enclosed by the loop
     */
    public Set<Point> findTilesEnclosedByLoop() {
        final Set<Point> result = new HashSet<>();
        final List<Point> singleGiantLoop = findSingleGiantLoop();

        final int minX = this.pipesByPoint.keySet().stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = this.pipesByPoint.keySet().stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = this.pipesByPoint.keySet().stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = this.pipesByPoint.keySet().stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;

        for (int y = maxY; y >= minY; y--) {
            boolean isEnclosed = false;
            TileType lastEnclosingTile = null;
            for (int x = minX; x <= maxX; x++) {
                final Pipe pipe = pipesByPoint.get(new Point(x, y));
                if (singleGiantLoop.contains(pipe.getPoint())) {
                    final TileType type = pipe.getType() == TileType.STARTING_POSITION ? determineTypeForStartingPosition(pipe): pipe.getType();
                    switch (type) {
                        case PIPE_VERTICAL -> {
                            isEnclosed = !isEnclosed;
                            lastEnclosingTile = type;
                        }
                        // L7
                        case PIPE_BEND_NORTH_EAST -> lastEnclosingTile = type;
                        case PIPE_BEND_SOUTH_WEST -> {
                            if (lastEnclosingTile == TileType.PIPE_BEND_NORTH_EAST) {
                                isEnclosed = !isEnclosed;
                                lastEnclosingTile = type;
                            }
                        }
                        // FJ
                        case PIPE_BEND_SOUTH_EAST -> lastEnclosingTile = type;
                        case PIPE_BEND_NORTH_WEST -> {
                            if (lastEnclosingTile == TileType.PIPE_BEND_SOUTH_EAST) {
                                isEnclosed = !isEnclosed;
                                lastEnclosingTile = type;
                            }
                        }
                    }
                } else if (isEnclosed) {
                    result.add(pipe.getPoint());
                }
            }
        }
        return result;
    }

    /**
     * TODO: this can probable implemented a lot better
     *
     * @param pipe the starting pipe
     * @return the pipe type that is below the starting position
     */
    private TileType determineTypeForStartingPosition(final Pipe pipe) {
        if (pipe.getType() != TileType.STARTING_POSITION) {
            throw new IllegalArgumentException("Tile should be of type " + TileType.STARTING_POSITION);
        }
        final List<Point> connectingPipes = pipe.getConnectablePoints().stream().filter(pipesByPoint::containsKey).map(pipesByPoint::get).filter(x -> x.canConnectTo(pipe)).map(Pipe::getPoint).toList();
        if (connectingPipes.size() != 2) {
            throw new IllegalStateException("STARTING_POSITION should only connect to 2 tiles instead of " + connectingPipes.size());
        }
        final Point point1 = connectingPipes.get(0);
        final Point point2 = connectingPipes.get(1);

        if ((point1.x < pipe.getPoint().x && point2.x > pipe.getPoint().x) || (point2.x < pipe.getPoint().x && point1.x > pipe.getPoint().x)) { return TileType.PIPE_HORIZONTAL; }
        if ((point1.y < pipe.getPoint().y && point2.y > pipe.getPoint().y) || (point2.y < pipe.getPoint().y && point1.y > pipe.getPoint().y)) { return TileType.PIPE_VERTICAL; }

        if ((point1.x > pipe.getPoint().x && point2.y > pipe.getPoint().y) || (point2.x > pipe.getPoint().x && point1.y > pipe.getPoint().y)) { return TileType.PIPE_BEND_NORTH_EAST; }
        if ((point1.x < pipe.getPoint().x && point2.y > pipe.getPoint().y) || (point2.x < pipe.getPoint().x && point1.y > pipe.getPoint().y)) { return TileType.PIPE_BEND_NORTH_WEST; }

        if ((point1.x > pipe.getPoint().x && point2.y < pipe.getPoint().y) || (point2.x > pipe.getPoint().x && point1.y < pipe.getPoint().y)) { return TileType.PIPE_BEND_SOUTH_EAST; }
        if ((point1.x < pipe.getPoint().x && point2.y < pipe.getPoint().y) || (point2.x < pipe.getPoint().x && point1.y < pipe.getPoint().y)) { return TileType.PIPE_BEND_SOUTH_WEST; }

        throw new IllegalStateException("This should not be possible");
    }


    public List<Point> findSingleGiantLoop() {
        final List<Point> path = new LinkedList<>();
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
        final List<Point> loopPoints = markLoop ? findSingleGiantLoop() : new LinkedList<>();
        final Set<Point> enclosedPoints = loopPoints.isEmpty() ? Collections.emptySet() : findTilesEnclosedByLoop();

        final StringBuilder builder = new StringBuilder();
        final int minX = this.pipesByPoint.keySet().stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = this.pipesByPoint.keySet().stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = this.pipesByPoint.keySet().stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = this.pipesByPoint.keySet().stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;

        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                final Pipe pipe = pipesByPoint.get(new Point(x, y));
                if (loopPoints.contains(pipe.getPoint())) {
                    builder.append(pipe.getRenderLoopToken());
                } else if (enclosedPoints.contains(pipe.getPoint())) {
                    builder.append(("I"));
                } else {
                    builder.append(pipe.getRenderToken());
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
