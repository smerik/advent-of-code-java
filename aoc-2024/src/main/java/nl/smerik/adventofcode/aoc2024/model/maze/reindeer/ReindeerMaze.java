package nl.smerik.adventofcode.aoc2024.model.maze.reindeer;

import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class ReindeerMaze {

    private static final int COST_MOVE = 1;
    private static final int COST_ROTATE = 1000;

    private final TileType[][] maze;

    public ReindeerMaze(final List<String> lines) {
        maze = parseLines(lines);
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

    public int findLowestScore() {
        final Point startPoint = findFirstTile(TileType.START);
        final Node startNode = new Node(startPoint, Direction.EAST, 0, 0, null);

        final Point endPoint = findFirstTile(TileType.END);
        final Node endNode = new Node(endPoint, null, 0, 0, null);
        final List<Node> path = findShortestPath(startNode, endNode);
        if (path.isEmpty()) {
            throw new IllegalStateException("No path found!");
        }
        return path.get(path.size() - 1).getG();
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

    /**
     * Finds the shortest path between two nodes using the A* search algorithm.
     * <p>
     * <code>f(n) = g(n) + h(n)</code>: Sum of the costs of the path from the start node to the current node <code>n</code>
     * AND the estimated cost from the current node <code>n</code> to the target node
     * <code>g(n)</code>: cost of the path from the start node to the current node n
     * <code>h(n)</code>: estimated cost from the current node to the destination (target node)
     *
     * @param start the start location
     * @param end   the end location
     */
    private List<Node> findShortestPath(final Node start, final Node end) {
        final Queue<Node> open = new PriorityQueue<>(Comparator.comparingInt(Node::calcF));
        open.add(start);

        // visited
        final Set<String> closed = new HashSet<>();

        while (!open.isEmpty()) {
            final Node current = open.poll();
            if (current.getPoint().equals(end.getPoint())) {
                return reconstructPath(current);
            }
            closed.add(encodeState(current));

            for (final Node neighbour : getNeighbours(current)) {
                if (closed.contains(encodeState(neighbour))) {
                    continue;
                }

                int tentativeG = current.getG() + calcCosts(current, neighbour);
                // Add neighbour to open set if it has a lower cost or is new
                if (!open.contains(neighbour) || tentativeG < neighbour.getG()) {
                    neighbour.setG(tentativeG);
                    neighbour.setH(heuristic(neighbour, end));
                    neighbour.setParent(current);

                    if (!open.contains(neighbour)) {
                        open.add(neighbour);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private String encodeState(final Node node) {
        return node.getPoint().x + "," + node.getPoint().y + "," + node.getDirection();
    }

    private List<Node> reconstructPath(final Node node) {
        final List<Node> result = new ArrayList<>();
        Node currentNode = node;
        while (currentNode != null) {
            result.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(result);
        return result;
    }

    private List<Node> getNeighbours(final Node node) {
        final List<Node> result = new ArrayList<>();

        // Move forward in the current direction
        final Direction direction = node.getDirection();
        final Point forwardLocation = new Point(node.getPoint());
        forwardLocation.translate(direction.getDx(), direction.getDy());
        if (isValid(forwardLocation)) {
            result.add(new Node(forwardLocation, direction, 0, 0, null));
        }

        // Rotate clockwise and counterclockwise
        result.add(new Node(node.getPoint(), direction.rotateCounterclockwise(), 0, 0, null));
        result.add(new Node(node.getPoint(), direction.rotateClockwise(), 0, 0, null));

        return result;
    }

    private boolean isValid(final Point point) {
        return point.y >= 0 && point.y < maze.length && point.x >= 0 && point.x < maze[0].length && maze[point.y][point.x] != TileType.WALL;
    }


    private int calcCosts(final Node current, final Node neighbour) {
        return current.getPoint().equals(neighbour.getPoint()) ? COST_ROTATE : COST_MOVE;
    }

    private int heuristic(final Node current, final Node end) {
        return Math.abs(current.getPoint().x - end.getPoint().x) + Math.abs(current.getPoint().y - end.getPoint().y);
    }

    public String render() {
        final StringBuilder sb = new StringBuilder();
        for (final TileType[] row : maze) {
            for (int col = 0; col < maze[0].length; col++) {
                sb.append(row[col]);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
