package nl.smerik.adventofcode.aoc2019.model.spaceship;

import lombok.Getter;

import java.awt.Point;
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
}
