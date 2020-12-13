package nl.smerik.adventofcode.aoc2020.model.shuttlebus;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class ShuttleBusService {

    private static final String OUT_OF_SERVICE = "x";

    private final Set<ShuttleBus> buses;

    public ShuttleBusService(final String busIDsInService) {
        buses = new HashSet<>();
        parseBusIDsInService(busIDsInService);
    }

    private void parseBusIDsInService(final String busIDsInService) {
        for (String busID : busIDsInService.split(",")) {
            if (busID.equals(OUT_OF_SERVICE)) {
                continue;
            }
            buses.add(new ShuttleBus(Integer.parseInt(busID)));
        }
    }

    public ShuttleBus findEarliestBusToTake(final int timestamp) {
        return buses.stream()
                    .min(Comparator.comparing(shuttleBus -> shuttleBus.findEarliestDeparture(timestamp)))
                    .orElseThrow();
    }
}
