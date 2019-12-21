package nl.smerik.adventofcode.aoc2019.model.universe;

import lombok.Data;

@Data
public class SpaceObject {

    private final String id;

    private SpaceObject orbitedSpaceObject;

    public SpaceObject(final String id) {
        this.id = id;
    }
}
