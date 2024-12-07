package nl.smerik.adventofcode.aoc2024.model.lab;

import java.awt.*;
import java.util.List;

public class ManufacturingLab {

    private final char[][] grid;
    private final Point startLocation;

    public ManufacturingLab(final List<String> lines) {
        this.grid = parseLines(lines);
        this.startLocation = findStartLocation(this.grid);
    }

    private char[][] parseLines(final List<String> lines) {
        final char[][] result = new char[lines.size()][lines.get(0).length()];
        for (int row = 0; row < lines.size(); row++) {
            result[row] = lines.get(row).toCharArray();
        }
        return result;
    }

    private Point findStartLocation(final char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '^') {
                    return new Point(col, row);
                }
            }
        }
        throw new IllegalStateException("Guard not found!");
    }

    public long countDistinctPositionsVisited() {
        final Guard guard = new Guard(grid, startLocation);
        return guard.countDistinctPositionsVisited();
    }

    public int countLoopPositions() {
        int result = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '.') {
                    grid[row][col] = '#';
                    try {
                        new Guard(grid, startLocation).countDistinctPositionsVisited();
                    } catch (IllegalStateException e) {
                        result++;
                    }
                    grid[row][col] = '.';
                }
            }
        }
        return result;
    }
}
