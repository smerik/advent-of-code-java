package nl.smerik.adventofcode.aoc2024.model.farm;

import lombok.Getter;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Garden {

    private final Map<Integer, Region> regionsById;

    public Garden(final List<String> lines) {
        regionsById = new HashMap<>();
        final char[][] gardenMap = parseLines(lines);
        mapRegions(gardenMap);
    }

    private char[][] parseLines(final List<String> lines) {
        final char[][] result = new char[lines.size()][lines.get(0).length()];
        for (int row = 0; row < lines.size(); row++) {
            result[row] = lines.get(row).toCharArray();
        }
        return result;
    }

    private void mapRegions(final char[][] gardenMap) {
        final boolean[][] visited = new boolean[gardenMap.length][gardenMap[0].length];
        int id = 1;
        for (int row = 0; row < gardenMap.length; row++) {
            for (int col = 0; col < gardenMap[row].length; col++) {
                if (!visited[row][col]) {
                    mapRegion(id, gardenMap[row][col], visited, gardenMap, row, col);
                    id++;
                }
            }
        }
    }

    private void mapRegion(final int id,
                           final char plantType,
                           final boolean[][] visited, final char[][] gardenMap,
                           final int row,
                           final int col) {
        if (isOutOfBounds(gardenMap, row, col)) {
            return;
        }
        if (visited[row][col]) {
            return;
        }
        if (gardenMap[row][col] != plantType) {
            return;
        }

        visited[row][col] = true;
        final int perimeter = determinePerimeter(plantType, gardenMap, row, col);
        if (regionsById.containsKey(id)) {
            regionsById.get(id).addPlot(perimeter);
        } else {
            regionsById.put(id, new Region(id, plantType, perimeter));
        }

        for (final Direction value : Direction.values()) {
            mapRegion(id, plantType, visited, gardenMap, row + value.moveY, col + value.moveX);
        }
    }

    private static boolean isOutOfBounds(char[][] gardenMap, int row, int col) {
        return row < 0 || row >= gardenMap.length || col < 0 || col >= gardenMap[0].length;
    }

    private int determinePerimeter(final char plantType, final char[][] gardenMap, final int row, final int col) {
        int result = 0;
        for (final Direction value : Direction.values()) {
            final Point location = new Point(col, row);
            location.translate(value.moveX, value.moveY);
            if (isOutOfBounds(gardenMap, location.y, location.x) || gardenMap[location.y][location.x] != plantType) {
                result++;
            }
        }
        return result;
    }

    public int calculateTotalFencingPrice() {
        return regionsById.values().stream().map(Region::calculateFencingPrice).mapToInt(Integer::intValue).sum();
    }

    @Getter
    private enum Direction {
        NORTH(0, -1),
        SOUTH(0, 1),
        WEST(-1, 0),
        EAST(1, 0);

        private final int moveX;
        private final int moveY;

        Direction(final int moveX, final int moveY) {
            this.moveX = moveX;
            this.moveY = moveY;
        }
    }
}
