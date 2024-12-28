package nl.smerik.adventofcode.aoc2024.model.maze.reindeer;

import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.stream.Collectors;

public class ReindeerMaze {

    private static final int COST_MOVE = 1;
    private static final int COST_ROTATE = 1000;

    private final TileType[][] maze;
    private final Point startPoint;
    private final Point endPoint;

    public ReindeerMaze(final List<String> lines) {
        maze = parseLines(lines);
        startPoint = findFirstTile(TileType.START);
        endPoint = findFirstTile(TileType.END);
    }

    private TileType[][] parseLines(final List<String> lines) {
        final TileType[][] result = new TileType[lines.size()][lines.get(0).length()];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[0].length; col++) {
                result[row][col] = TileType.valueOfToken(lines.get(row).charAt(col));
            }
        }
        return result;
    }

    private Point findFirstTile(final TileType type) {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if (maze[row][col] == type) {
                    return new Point(col, row);
                }
            }
        }
        throw new IllegalStateException("Tile type " + type + " not found!");
    }

    public int findLowestScore() {
        return findShortestPaths().stream().findFirst().orElseThrow().cost();
    }

    public int countBestPathTiles() {
        return findShortestPaths().stream().map(Node::path).flatMap(List::stream).collect(Collectors.toSet()).size();
    }

    /**
     * Finds the shortest path(s) between two nodes using the Dijkstra algorithm.
     */
    private Set<Node> findShortestPaths() {
        final Set<Node> results = new HashSet<>();

        final Queue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(Node::cost));
        final Node startNode = new Node(startPoint, Direction.EAST, Collections.emptyList(), 0);
        frontier.add(startNode);

        final Map<String, Integer> visitedCostByPointAndDirection = new HashMap<>();
        int best = Integer.MAX_VALUE;

        while (!frontier.isEmpty()) {
            final Node current = frontier.poll();
            final int currentCost = current.cost();
            String key = encodeState(current);
            if (visitedCostByPointAndDirection.containsKey(key)) {
                if (currentCost > visitedCostByPointAndDirection.get(key)) {
                    continue;
                }
            } else {
                visitedCostByPointAndDirection.put(key, currentCost);
            }

            if (current.point().equals(endPoint) && currentCost <= best) {
                results.add(current);
                best = currentCost;
            }

            frontier.addAll(getNeighbours(current));
        }
        return results;
    }

    private String encodeState(final Node node) {
        return node.point().x + "," + node.point().y + "," + node.direction();
    }

    private List<Node> getNeighbours(final Node node) {
        final List<Node> result = new ArrayList<>();

        // Move forward in the current direction
        final Direction direction = node.direction();
        final Point forwardLocation = new Point(node.point());
        forwardLocation.translate(direction.getDx(), direction.getDy());
        if (isValid(forwardLocation)) {
            result.add(new Node(forwardLocation, direction, node.path(), node.cost() + COST_MOVE));
        }

        // Rotate clockwise and counterclockwise
        final int rotateCost = node.cost() + COST_ROTATE;
        result.add(new Node(node.point(), direction.rotateCounterclockwise(), node.path(), rotateCost));
        result.add(new Node(node.point(), direction.rotateClockwise(), node.path(), rotateCost));

        return result;
    }

    private boolean isValid(final Point point) {
        return point.y >= 0 && point.y < maze.length && point.x >= 0 && point.x < maze[0].length && maze[point.y][point.x] != TileType.WALL;
    }
}
