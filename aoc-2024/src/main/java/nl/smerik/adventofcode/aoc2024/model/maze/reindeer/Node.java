package nl.smerik.adventofcode.aoc2024.model.maze.reindeer;

import lombok.Data;

import java.awt.*;

@Data
public class Node {

    /**
     * The location of the node.
     */
    private final Point point;
    /**
     * The direction of the node. Will be used during cost calculation since rotating results in additional costs.
     */
    private final Direction direction;
    /**
     * The cost to get from the start position to the current position.
     */
    private int g;
    /**
     * The heuristic: a smart guess of the estimated cost to get from the current position to the goal position
     */
    private int h;
    /**
     * The parent node.
     */
    private Node parent;

    public Node(final Point point,
                final Direction direction,
                final int g,
                final int h,
                final Node parent) {
        this.point = point;
        this.direction = direction;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    /**
     * Calculates the total estimated cost.
     *
     * @return the estimated cost
     */
    int calcF() {
        return g + h;
    }
}
