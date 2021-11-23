package nl.smerik.adventofcode.aoc2019.model.spaceship;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.awt.Point;

@Getter
@ToString
@EqualsAndHashCode
public class Panel {

    @EqualsAndHashCode.Include
    private final Point location;

    private Color currentColor;
    private int paintedTimes;

    public Panel(final Point location) {
        this.location = location;
        this.currentColor = Color.BLACK;
    }

    public void paint(final Color color) {
        this.currentColor = color;
        this.paintedTimes++;
    }

    public boolean hasBeenPainted() {
        return paintedTimes > 0;
    }
}
