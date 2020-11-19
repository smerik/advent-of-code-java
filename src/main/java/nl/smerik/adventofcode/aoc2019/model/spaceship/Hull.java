package nl.smerik.adventofcode.aoc2019.model.spaceship;

import lombok.Getter;

import java.awt.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Hull {

    private final Map<Point, Panel> panels;

    public Hull() {
        this.panels = new HashMap<>();
    }

    public Panel getPanel(final Point point) {
        return this.panels.computeIfAbsent(point, Panel::new);
    }

    public Long countPaintedPanels() {
        return panels.values().stream().filter(Panel::hasBeenPainted).count();
    }

    public String render() {
        StringBuilder builder = new StringBuilder();
        final int minX = this.panels.keySet().stream().min(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int maxX = this.panels.keySet().stream().max(Comparator.comparing(Point::getX)).orElseThrow().x;
        final int minY = this.panels.keySet().stream().min(Comparator.comparing(Point::getY)).orElseThrow().y;
        final int maxY = this.panels.keySet().stream().max(Comparator.comparing(Point::getY)).orElseThrow().y;

        for (int y = maxY ; y >= minY; y--) {
            for (int x = minX ; x <= maxX; x++) {
                builder.append(getPanel(new Point(x, y)).getCurrentColor().getRenderToken());
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
