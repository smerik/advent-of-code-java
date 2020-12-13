package nl.smerik.adventofcode.aoc2020.model.shuttlebus;

import lombok.Data;

@Data
public class ShuttleBus {

    private final int id;

    public ShuttleBus(final int id) {
        this.id = id;
    }

    public int calculateSolutionPart01(final int timestamp) {
        return id * calculateWaitingTimeForEarliestDeparture(timestamp);
    }

    public int calculateWaitingTimeForEarliestDeparture(final int timestamp) {
        return findEarliestDeparture(timestamp) - timestamp;
    }

    public int findEarliestDeparture(final int timestamp) {
        return id * (int) Math.ceil(timestamp / (double) id);
    }
}
