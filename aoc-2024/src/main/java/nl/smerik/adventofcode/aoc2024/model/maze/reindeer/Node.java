package nl.smerik.adventofcode.aoc2024.model.maze.reindeer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @param point     The location of the node.
 * @param direction The direction of the node. Will be used during cost calculation since rotating results in additional costs.
 * @param path      The path from the start position to the node.
 * @param cost      the cost to get from the start position to the node.
 */
public record Node(Point point, Direction direction, List<Point> path, int cost) {

    public Node {
        final ArrayList<Point> newPath = new ArrayList<>(path);
        newPath.add(point);
        path = newPath;
    }
}
