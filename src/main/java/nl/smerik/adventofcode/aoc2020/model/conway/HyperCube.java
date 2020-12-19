package nl.smerik.adventofcode.aoc2020.model.conway;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import nl.smerik.adventofcode.aoc2020.model.geom.Point4D;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class HyperCube {

    @EqualsAndHashCode.Include
    private final Point4D point;

    private boolean active;

    public HyperCube(final Point4D point) {
        this(point, false);
    }

    public HyperCube(final Point4D point, final boolean active) {
        this.point = point;
        this.active = active;
    }

    public void toggleActiveState() {
        this.active = !active;
    }
}
