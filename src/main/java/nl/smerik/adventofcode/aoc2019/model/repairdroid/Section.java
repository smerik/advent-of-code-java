package nl.smerik.adventofcode.aoc2019.model.repairdroid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Section {

    private static final Logger LOGGER = LoggerFactory.getLogger(Section.class);

    private final RepairDroid droid;
    private final Map<Point, Cell> area;

    public Section(final RepairDroid droid) {
        this.droid = droid;
        this.area = new HashMap<>();
        exploreArea();
        drawArea();
    }

    private void exploreArea() {
        boolean explored = false;
        while (!explored) {
            final Cell cell = droid.moveForward();
            area.put(cell.getPoint(), cell);

            if (cell.getType() == Cell.Type.WALL) {
                droid.rotateRight();
            } else {
                droid.rotateLeft();
            }
            if (cell.getPoint().equals(new Point())) {
                explored = true;
            }
        }
    }

    private void drawArea() {
        final StringBuilder builder = new StringBuilder(System.lineSeparator());
        final int minX = area.keySet().stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = area.keySet().stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = area.keySet().stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = area.keySet().stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;

        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                final Cell cell = area.get(new Point(x, y));
                if (cell == null) {
                    builder.append('?');
                } else {
                    builder.append(cell.getType().getRenderToken());
                }
            }
            builder.append(System.lineSeparator());
        }
        LOGGER.info("Area:{}", builder);
    }
}
