package nl.smerik.adventofcode.aoc2019.model.vacuumrobot;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;
import java.util.Set;

@Getter
@EqualsAndHashCode
@ToString
public class SpaceCell {

    @EqualsAndHashCode.Include
    private final Point point;

    private final AsciiType type;

    public SpaceCell(final Point point, final AsciiType type) {
        this.point = point;
        this.type = type;
    }

    public boolean isScaffold() {
        switch (type) {
            case SCAFFOLD:
            case VACUUM_ROBOT_FACING_UP:
            case VACUUM_ROBOT_FACING_DOWN:
            case VACUUM_ROBOT_FACING_LEFT:
            case VACUUM_ROBOT_FACING_RIGHT:
                return true;
            default:
                return false;
        }
    }

    public int getAlignmentParameter() {
        return point.x * point.y;
    }

    public Set<Point> getSurroundedPoints() {
        final Point bottom = new Point(point.x, point.y - 1);
        final Point up = new Point(point.x, point.y + 1);
        final Point left = new Point(point.x - 1, point.y);
        final Point right = new Point(point.x + 1, point.y);
        return Set.of(up, left, bottom, right);
    }
}
