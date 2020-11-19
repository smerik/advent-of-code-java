package nl.smerik.adventofcode.aoc2019.model.arcade;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.awt.Point;

@Getter
@ToString
@EqualsAndHashCode
public class Tile {

    private final Point point;

    private final Type type;

    public Tile(final Point point, final Type type) {
        this.point = point;
        this.type = type;
    }

    public enum Type {
        EMPTY(0),
        WALL(1),
        BLOCK(2),
        HORIZONTAL_PADDLE(3),
        BALL(4);

        private final int id;

        Type(final int id) {
            this.id = id;
        }

        public static Type valueOfTileTypeId(final int id) {
            for (final Type type : values()) {
                if (type.id == id) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown tile type id " + id);
        }
    }
}
