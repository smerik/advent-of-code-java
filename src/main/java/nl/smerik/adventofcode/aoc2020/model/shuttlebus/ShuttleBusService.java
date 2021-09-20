package nl.smerik.adventofcode.aoc2020.model.shuttlebus;

import lombok.Getter;

import java.util.*;

@Getter
public class ShuttleBusService {

    private static final String OUT_OF_SERVICE = "x";

    private final Set<ShuttleBus> buses;

    public ShuttleBusService(final String busIDsInService) {
        buses = new HashSet<>();
        parseBusIDsInService(busIDsInService);
    }

    private void parseBusIDsInService(final String busIDsInService) {
        final String[] busIDs = busIDsInService.split(",");
        for (int i = 0; i < busIDs.length; i++) {
            if (busIDs[i].equals(OUT_OF_SERVICE)) {
                continue;
            }
            buses.add(new ShuttleBus(Integer.parseInt(busIDs[i]), i));
        }
    }

    public ShuttleBus findEarliestBusToTake(final long timestamp) {
        return buses.stream()
                    .min(Comparator.comparing(shuttleBus -> shuttleBus.findEarliestDeparture(timestamp)))
                    .orElseThrow();
    }

    /**
     * Finds the earliest timestamp such that the first bus ID departs at that time and each subsequent listed bus ID
     * departs at that subsequent minute.
     * <p>
     * This implementation is based on the Python
     * <a href="https://www.reddit.com/r/adventofcode/comments/kc4njx/2020_day_13_solutions/gfth69h/?utm_source=reddit&utm_medium=web2x&context=3">solution</a>
     * description of reddit user
     * <a href="https://www.reddit.com/user/imbadatreading/">imbadatreading</a>.
     * <p>
     * Although the implementation can be done neater, it is a working one.
     *
     * @param initialTimestamp the base time stamp to start finding the solution
     * @return the earliest timestamp
     */
    public long findSubsequentBusDeparturesTimestamp(long initialTimestamp) {
        final List<ShuttleBus> busesSortedByDepartureSequence
                = buses.stream()
                       .sorted(Comparator.comparing(ShuttleBus::getSubsequentTimestamp))
                       .toList();

        long timestamp = initialTimestamp;
        long lcm = 1; // least common multiple
        final ListIterator<ShuttleBus> iter = busesSortedByDepartureSequence.listIterator();
        while (iter.hasNext()) {
            final ShuttleBus bus = iter.next();
            lcm *= bus.getId();
            if (iter.hasNext()) {
                final ShuttleBus nextBus = busesSortedByDepartureSequence.get(iter.nextIndex());
                while ((timestamp + nextBus.getSubsequentTimestamp()) % nextBus.getId() != 0) {
                    timestamp += lcm;
                }
            }
        }
        return timestamp;
    }
}
