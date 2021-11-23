package nl.smerik.adventofcode.aoc2019.model.vacuumrobot;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class AftScaffoldControlInformationInterface {

    private final VacuumRobot vacuumRobot;
    private final Map<Point, SpaceCell> area;

    public AftScaffoldControlInformationInterface(final VacuumRobot vacuumRobot) {
        this.vacuumRobot = vacuumRobot;
        this.area = refreshMap();
    }

    private Map<Point, SpaceCell> refreshMap() {
        final List<AsciiType> characters = vacuumRobot.showCameraOutput().stream()
                .map(Long::intValue)
                .map(AsciiType::valueOfLabel)
                .collect(Collectors.toList());

        final Map<Point, SpaceCell> result = new HashMap<>();

        int x = 0;
        int y = 0;
        for (final AsciiType type : characters) {
            if (type == AsciiType.NEW_LINE) {
                x = 0;
                y++;
                continue;
            }
            final SpaceCell cell = new SpaceCell(new Point(x, y), type);
            result.put(cell.getPoint(), cell);
            x++;
        }
        return result;
    }

    public void drawArea() {
        final StringBuilder builder = new StringBuilder(System.lineSeparator());
        final int minX = area.keySet().stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = area.keySet().stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = area.keySet().stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = area.keySet().stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;

        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                final SpaceCell cell = area.get(new Point(x, y));
                if (cell == null) {
                    builder.append('?');
                } else {
                    builder.append((char) cell.getType().getCode());
                }
            }
            builder.append(System.lineSeparator());
        }
        LOG.info("Area:{}", builder);
    }

    public int sumAlignmentParameterOfScaffoldIntersections() {
        return area.values().stream()
                            .filter(SpaceCell::isScaffold)
                            .filter(this::isScaffoldIntersection)
                            .mapToInt(SpaceCell::getAlignmentParameter)
                            .sum();
    }

    private boolean isScaffoldIntersection(final SpaceCell spaceCell) {
        for (final Point point : spaceCell.getSurroundedPoints()) {
            if (area.get(point) == null) {
                return false;
            }
            if (!area.get(point).isScaffold()) {
                return false;
            }
        }
        return true;
    }
}
