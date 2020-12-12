package nl.smerik.adventofcode.aoc2020.model.waitingarea;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WaitingArea {

    private final Map<Point, Seat> seats;

    public WaitingArea(final List<String> seatLayout) {
        seats = new HashMap<>();
        for (int y = 0; y < seatLayout.size(); y++) {
            parseRow(y, seatLayout.get(y));
        }
    }

    private void parseRow(final int y, final String row) {
        for (int index = row.indexOf('L'); index >= 0; index = row.indexOf('L', index + 1)) {
            final Point location = new Point(index, y);
            final Seat seat = new Seat(location);
            seats.put(location, seat);
        }
    }

    public void simulate() {
        Set<Seat> seatsToToggleOccupation;
        do {
            seatsToToggleOccupation = seats.values()
                                          .stream()
                                          .filter(this::isEligibleForOccupationChange).collect(Collectors.toSet());
            seatsToToggleOccupation.forEach(Seat::toggleOccupation);
        } while (!seatsToToggleOccupation.isEmpty());
    }

    private boolean isEligibleForOccupationChange(final Seat seat) {
        final long occupiedSeats = countSurroundedOccupiedSeats(seat);
        if (seat.isOccupied()) {
            return occupiedSeats >= 4L;
        }
        return occupiedSeats == 0L;
    }

    public long countSurroundedOccupiedSeats(final Seat seat) {
        return getAdjacentSeats(seat).stream().filter(Seat::isOccupied).count();
    }

    public Set<Seat> getAdjacentSeats(final Seat seat) {
        final Set<Seat> result = new HashSet<>();
        final Set<Point> adjacentLocations = getAdjacentPoints(seat.getLocation());
        for (final Point adjacentLocation : adjacentLocations) {
            if (seats.containsKey(adjacentLocation)) {
                result.add(seats.get(adjacentLocation));
            }
        }
        return result;
    }

    private Set<Point> getAdjacentPoints(final Point point) {
        final Set<Point> result = new HashSet<>();
        result.add(new Point(point.x - 1, point.y + 1));
        result.add(new Point(point.x, point.y + 1));
        result.add(new Point(point.x + 1, point.y + 1));
        result.add(new Point(point.x - 1, point.y));
        result.add(new Point(point.x + 1, point.y));
        result.add(new Point(point.x - 1, point.y - 1));
        result.add(new Point(point.x, point.y - 1));
        result.add(new Point(point.x + 1, point.y - 1));
        return result;
    }

    public Set<Seat> getOccupiedSeats() {
        return seats.values().stream().filter(Seat::isOccupied).collect(Collectors.toSet());
    }
}
