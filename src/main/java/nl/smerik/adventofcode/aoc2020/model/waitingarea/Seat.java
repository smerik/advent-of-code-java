package nl.smerik.adventofcode.aoc2020.model.waitingarea;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.awt.Point;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Seat {

    @EqualsAndHashCode.Include
    private final Point location;

    private boolean occupied;

    public Seat(final Point location) {
        this.location = location;
        this.occupied = false;
    }

    public void toggleOccupation() {
        occupied = !occupied;
    }
}
