package nl.smerik.adventofcode.aoc2019.model.repairdroid;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.awt.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
public class Section {

    private final RepairDroid droid;
    private final Map<Point, Cell> area;
    private final Graph<Cell, DefaultEdge> hallway;

    public Section(final RepairDroid droid) {
        this.droid = droid;
        this.area = new HashMap<>();
        this.hallway = new DefaultUndirectedGraph<>(DefaultEdge.class);
        exploreArea();
        drawArea();
    }

    private void exploreArea() {
        Cell previousCell = null;
        boolean explored = false;
        while (!explored) {
            final Cell cell = droid.moveForward();
            area.put(cell.getPoint(), cell);

            if (cell.getType() == Cell.Type.WALL) {
                droid.rotateRight();
            } else {
                droid.rotateLeft();
                hallway.addVertex(cell);
                if (previousCell != null) {
                    hallway.addEdge(previousCell, cell);
                }
                previousCell = cell;
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
        LOG.info("Area:{}", builder);
    }

    public Cell getOxygenCell() {
        return area.values()
                   .stream()
                   .filter(cell -> cell.getType() == Cell.Type.OXYGEN)
                   .findAny()
                   .orElseThrow();
    }
}
