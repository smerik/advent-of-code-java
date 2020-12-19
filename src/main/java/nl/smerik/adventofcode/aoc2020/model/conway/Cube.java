package nl.smerik.adventofcode.aoc2020.model.conway;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import nl.smerik.adventofcode.aoc2020.model.geom.Point3D;

@Getter
@EqualsAndHashCode
@ToString
public class Cube {

    private final Point3D point;

    private boolean active;

    public Cube(final Point3D point, final boolean active) {
        this.point = point;
        this.active = active;
    }

    public Cube(final Point3D point) {
        this(point, false);
    }

    public void toggleActiveState() {
        this.active = !active;
    }
}
