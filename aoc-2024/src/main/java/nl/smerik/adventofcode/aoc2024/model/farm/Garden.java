package nl.smerik.adventofcode.aoc2024.model.farm;

import lombok.Getter;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Garden {

    private static final int PERIMETER_INDEX = 0;
    private static final int SIDES_INDEX = 1;

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
                           final boolean[][] visited,
                           final char[][] gardenMap,
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
        final int[] perimeterAndSides = determinePerimeterAndSides(plantType, gardenMap, row, col);
        if (regionsById.containsKey(id)) {
            regionsById.get(id).addPlot(perimeterAndSides[PERIMETER_INDEX], perimeterAndSides[SIDES_INDEX]);
        } else {
            regionsById.put(id, new Region(id, plantType, perimeterAndSides[PERIMETER_INDEX], perimeterAndSides[SIDES_INDEX]));
        }

        for (final Direction direction : Direction.values()) {
            mapRegion(id, plantType, visited, gardenMap, row + direction.moveY, col + direction.moveX);
        }
    }

    private static boolean isOutOfBounds(char[][] gardenMap, int row, int col) {
        return row < 0 || row >= gardenMap.length || col < 0 || col >= gardenMap[0].length;
    }

    private int[] determinePerimeterAndSides(final char plantType, final char[][] gardenMap, final int row, final int col) {
        final int[] result = {0, 0};
        for (final Direction direction : Direction.values()) {
            final Point sideLocation = new Point(col, row);
            sideLocation.translate(direction.moveX, direction.moveY);
            if (isOutOfBounds(gardenMap, sideLocation.y, sideLocation.x) || gardenMap[sideLocation.y][sideLocation.x] != plantType) {
                result[PERIMETER_INDEX]++;
            }
            if (isCorner(gardenMap, row, col, direction)) {
                result[SIDES_INDEX]++;
            }
        }
        return result;
    }

    private boolean isCorner(final char[][] gardenMap, final int row, final int col, final Direction direction) {
        final Point topPlot = new Point(col, row);
        topPlot.translate(direction.moveX, direction.moveY);
        final Point leftPlot = new Point(col, row);
        final Point leftTopPlot = new Point(col, row);
        switch (direction) {
            case NORTH:
                leftPlot.translate(Direction.WEST.moveX, Direction.WEST.moveY);
                leftTopPlot.translate(Direction.WEST.moveX, Direction.NORTH.moveY);
                break;
            case EAST:
                leftPlot.translate(Direction.NORTH.moveX, Direction.NORTH.moveY);
                leftTopPlot.translate(Direction.EAST.moveX, Direction.NORTH.moveY);
                break;
            case SOUTH:
                leftPlot.translate(Direction.EAST.moveX, Direction.EAST.moveY);
                leftTopPlot.translate(Direction.EAST.moveX, Direction.SOUTH.moveY);
                break;
            case WEST:
                leftPlot.translate(Direction.SOUTH.moveX, Direction.SOUTH.moveY);
                leftTopPlot.translate(Direction.WEST.moveX, Direction.SOUTH.moveY);
                break;
        }

        final char currentType = gardenMap[row][col];
        final char topType = getPlantType(gardenMap, topPlot);
        final char leftTopType = getPlantType(gardenMap, leftTopPlot);
        final char leftType = getPlantType(gardenMap, leftPlot);
        final boolean isOutwardCorner = topType != currentType && leftType != currentType;
        final boolean isInwardCorner = topType == currentType && leftType == currentType && leftTopType != currentType;
        return isOutwardCorner || isInwardCorner;
    }

    private char getPlantType(final char[][] gardenMap, final Point location) {
        return isOutOfBounds(gardenMap, location.y, location.x) ? Character.MIN_VALUE : gardenMap[location.y][location.x];
    }

    public int calculateTotalFencingPrice() {
        return regionsById.values().stream().map(Region::calculateFencingPrice).mapToInt(Integer::intValue).sum();
    }

    public int calculateTotalFencingPriceOnDiscount() {
        return regionsById.values().stream().map(Region::calculateFencingPriceOnDiscount).mapToInt(Integer::intValue).sum();
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
