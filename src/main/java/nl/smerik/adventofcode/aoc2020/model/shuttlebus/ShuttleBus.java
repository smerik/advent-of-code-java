package nl.smerik.adventofcode.aoc2020.model.shuttlebus;

import lombok.Data;

@Data
public class ShuttleBus {

    private final int id;
    private final int subsequentTimestamp;

    public ShuttleBus(final int id, final int subsequentTimestamp) {
        this.id = id;
        this.subsequentTimestamp = subsequentTimestamp;
    }

    public long calculateSolutionPart01(final long timestamp) {
        return id * calculateWaitingTimeForEarliestDeparture(timestamp);
    }

    public long calculateWaitingTimeForEarliestDeparture(final long timestamp) {
        return findEarliestDeparture(timestamp) - timestamp;
    }

    public long findEarliestDeparture(final long timestamp) {
        return id * (long) Math.ceil(timestamp / (double) id);
    }
}
