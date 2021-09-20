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
     * Although my implementation can be done neater, it is a working one.
     * <p>
     * This implementation is based on the Python
     * <a href="https://www.reddit.com/r/adventofcode/comments/kc4njx/2020_day_13_solutions/gfth69h/?utm_source=reddit&utm_medium=web2x&context=3">solution</a>
     * description of reddit user
     * <a href="https://www.reddit.com/user/imbadatreading/">imbadatreading</a>.
     * <p>
     * His thought process was that he needed to find a number such that it was going to be divisible by the first bus,
     * and then divisible by the second bus PLUS its index, and so on.
     * So in the sample <code>7,13,x,x,59,x,31,19</code> if you take the first two buses,
     * we need a number divisible by <code>7</code> that when you add <code>1</code> to it
     * (the index of bus <code>13</code>) the number becomes divisible by <code>13</code>.
     * This happens at <code>77</code>, because <code>77 + 1 = 78</code> which is divisible by <code>13</code>.
     * The next number we need to find is a number that is divisible by
     * <code>7</code>, <code>13 + 1</code> and <code>59 + 4</code>.
     * This continues for all the buses.
     * <p>
     * Obviously the final number is going to be huge, so you need a way to generate candidates,
     * and this happens with the realisation that the buses are all prime numbers:
     * you can take their product and keep adding it until it meets your conditions.
     * For example the least common multiple (lcm) of <code>7</code> and <code>13</code> is <code>91</code>,
     * so we keep adding <code>91</code> to <code>77</code> until we find a number that aligns with
     * <code>7, 13+1, 59+4</code>.
     * Then the LCM becomes <code>7 * 13 * 59</code>, and this continues until you've found a number for all your buses.
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
