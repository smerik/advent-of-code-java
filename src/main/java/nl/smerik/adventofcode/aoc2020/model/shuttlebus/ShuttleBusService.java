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

    public long findSubsequentBusDeparturesTimestamp(long initialTimestamp) {
        // TODO: reimplement to get to the correct answer on the puzzle input itself
        // https://www.reddit.com/r/adventofcode/comments/kc4njx/2020_day_13_solutions/ggv5a0s/?utm_source=reddit&utm_medium=web2x&context=3
        //
        final List<ShuttleBus> busesSortedByDepartureSequence
                = buses.stream()
                       .sorted(Comparator.comparing(ShuttleBus::getSubsequentTimestamp))
                       .toList();

        boolean found = false;
        long timestamp = initialTimestamp;
        while (!found) {
            timestamp = busesSortedByDepartureSequence.get(0).findEarliestDeparture(timestamp);

            for (final ShuttleBus bus : busesSortedByDepartureSequence) {
                final int subsequent = bus.getSubsequentTimestamp();
                if (bus.findEarliestDeparture(timestamp) != timestamp + subsequent) {
                    found = false;
                    timestamp++;
                    break;
                } else {
                    found = true;
                }
            }
        }
        return timestamp;
    }
}
