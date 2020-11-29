package nl.smerik.adventofcode.aoc2019.model.repairdroid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.Point;

@Getter
@EqualsAndHashCode
@ToString
public class Cell {

    @Getter
    public enum Type {
        SPACE(' '),
        WALL('#'),
        OXYGEN('O');

        private final char renderToken;

        Type(final char renderToken) {
            this.renderToken = renderToken;
        }
    }

    @EqualsAndHashCode.Include
    private final Point point;

    @Setter
    private Type type;

    public Cell(final Point point, final Type type) {
        this.point = point;
        this.type = type;
    }
}
