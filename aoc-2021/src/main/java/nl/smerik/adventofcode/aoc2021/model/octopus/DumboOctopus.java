package nl.smerik.adventofcode.aoc2021.model.octopus;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.awt.Point;

@Getter
@EqualsAndHashCode
public class DumboOctopus {

    private final Point location;

    private int energyLevel;

    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private int lastStep;

    /**
     * Constructs a dumbo octopus with given location and an initial energy level.
     *
     * @param location    the location of the octopus
     * @param energyLevel the initial energy level
     */
    public DumboOctopus(final Point location, final int energyLevel) {
        this.location = location;
        this.energyLevel = energyLevel;
        this.lastStep = 0;
    }

    /**
     * Increases the octopus its energy level.
     * When the energy level reaches a level greater than 9 the octopus flashes brightly for a moment.
     * <p>
     * NOTE: An octopus can only flash at most once per step.
     *
     * @param step the current step
     * @return <code>true when flashed</code>; <code>false</code> otherwise
     */
    public boolean increaseEnergyLevel(final int step) {
        if (energyLevel == 0 && step == lastStep) {
            return false;
        }

        energyLevel++;
        lastStep = step;
        if (energyLevel <= 9) {
            return false;
        }
        energyLevel = 0;
        return true;
    }

    @Override
    public String toString() {
        return "" + energyLevel;
    }
}
