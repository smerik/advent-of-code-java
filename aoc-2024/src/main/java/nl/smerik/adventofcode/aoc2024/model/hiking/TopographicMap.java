package nl.smerik.adventofcode.aoc2024.model.hiking;

import lombok.Getter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TopographicMap {

    private static final int MAX_HEIGHT = 9;

    private final int[][] heightMap;

    public TopographicMap(final List<String> lines) {
        this.heightMap = parseLines(lines);
    }

    private int[][] parseLines(final List<String> lines) {
        final int[][] result = new int[lines.size()][];
        for (int row = 0; row < lines.size(); row++) {
            result[row] = lines.get(row).chars().map(Character::getNumericValue).toArray();
        }
        return result;
    }

    public int sumTrailheadsScore() {
        int result = 0;
        for (int row = 0; row < heightMap.length; row++) {
            for (int col = 0; col < heightMap[0].length; col++) {
                if (heightMap[row][col] == 0) {
                    result += calculateTrailheadsScore(new Point(col, row));
                }
            }
        }
        return result;
    }

    public int calculateTrailheadsScore(final Point startPosition) {
        return new HashSet<>(findTrailheadsPerTrail(startPosition)).size();
    }

    public int sumTrailheadsRating() {
        int result = 0;
        for (int row = 0; row < heightMap.length; row++) {
            for (int col = 0; col < heightMap[0].length; col++) {
                if (heightMap[row][col] == 0) {
                    result += calculateTrailheadsRating(new Point(col, row));
                }
            }
        }
        return result;
    }

    public int calculateTrailheadsRating(final Point startPosition) {
        return findTrailheadsPerTrail(startPosition).size();
    }

    public List<Point> findTrailheadsPerTrail(final Point startPosition) {
        int currentHeight = heightMap[startPosition.y][startPosition.x];
        if (currentHeight == MAX_HEIGHT) {
            return Collections.singletonList(startPosition);
        }

        final List<Point> result = new ArrayList<>();
        for (final Direction direction : Direction.values()) {
            final Point nextPosition = new Point(startPosition);
            nextPosition.translate(direction.moveX, direction.moveY);
            if (nextPosition.y < 0 || nextPosition.y >= heightMap.length || nextPosition.x < 0 || nextPosition.x >= heightMap[0].length) {
                continue;
            }
            if (heightMap[nextPosition.y][nextPosition.x] == currentHeight + 1) {
                result.addAll(findTrailheadsPerTrail(nextPosition));
            }
        }
        return result;
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