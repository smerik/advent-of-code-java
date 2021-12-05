package nl.smerik.adventofcode.aoc2021.model.fissure;

import lombok.Data;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class HydrothermalVent {

    private static final Pattern LINE_SEGMENT_PATTERN = Pattern.compile("(?<x1>\\d+),(?<y1>\\d+) -> (?<x2>\\d+),(?<y2>\\d+)");

    private final Map<Point, Integer> coveredByCoordinates;

    public HydrothermalVent(final List<String> input, final boolean processDiagonalLines) {
        coveredByCoordinates = new HashMap<>();
        processLineSegments(input, processDiagonalLines);
    }

    private void processLineSegments(final List<String> lineSegments, final boolean processDiagonalLines) {
        for (final String lineSegment : lineSegments) {
            final Matcher matcher = LINE_SEGMENT_PATTERN.matcher(lineSegment);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Invalid line segment: '" + lineSegment + "'");
            }
            final int x1 = Integer.parseInt(matcher.group("x1"));
            final int y1 = Integer.parseInt(matcher.group("y1"));
            final int x2 = Integer.parseInt(matcher.group("x2"));
            final int y2 = Integer.parseInt(matcher.group("y2"));
            processLineSegmentCoordinates(x1, y1, x2, y2, processDiagonalLines);
        }
    }

    private void processLineSegmentCoordinates(final int x1, final int y1, final int x2, final int y2,
                                               final boolean processDiagonalLines) {

        if (x1 == x2 || y1 == y2) {
            processStraightLine(x1, y1, x2, y2);
        } else if (processDiagonalLines) {
            processDiagonalLine(x1, y1, x2, y2);
        }
    }

    private void processStraightLine(final int x1, final int y1, final int x2, final int y2) {
        final int fromX = Math.min(x1, x2);
        final int toX = Math.max(x1, x2);
        final int fromY = Math.min(y1, y2);
        final int toY = Math.max(y1, y2);
        for (int y = fromY; y <= toY; y++) {
            for (int x = fromX; x <= toX; x++) {
                processLineSegmentsCoordinate(x, y);
            }
        }
    }

    private void processDiagonalLine(final int x1, final int y1, final int x2, final int y2) {
        final int fromX;
        final int toX;
        final int fromY;
        final int toY;
        if (x1 < x2) {
            fromX = x1;
            toX = x2;
            fromY = y1;
            toY = y2;
        } else {
            fromX = x2;
            toX = x1;
            fromY = y2;
            toY = y1;
        }
        if (fromY < toY) {
            for (int x = fromX, y = fromY; x <= toX; x++, y++) {
                processLineSegmentsCoordinate(x, y);
            }
        } else {
            for (int x = fromX, y = fromY; x <= toX; x++, y--) {
                processLineSegmentsCoordinate(x, y);
            }
        }
    }

    private void processLineSegmentsCoordinate(final int x, final int y) {
        final Point point = new Point(x, y);
        final int overlappingCount = coveredByCoordinates.getOrDefault(point, 0);
        coveredByCoordinates.put(point, overlappingCount + 1);
    }

    /**
     * Counts the number of points where at least two lines overlap.
     *
     * @return the number of overlapping points
     */
    public long countOverlappingPoints() {
        return coveredByCoordinates.values().stream().filter(v -> v > 1).count();
    }
}
