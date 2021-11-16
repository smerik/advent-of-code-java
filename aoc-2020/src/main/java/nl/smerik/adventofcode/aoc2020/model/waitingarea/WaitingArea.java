package nl.smerik.adventofcode.aoc2020.model.waitingarea;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class WaitingArea {

    private final Map<Point, Seat> seats;

    private final int minX;
    private final int maxX;

    private final int minY;
    private final int maxY;

    public WaitingArea(final List<String> seatLayout) {
        seats = new HashMap<>();
        minX = 0;
        maxX = seatLayout.get(0).length() - 1;
        minY = 0;
        maxY = seatLayout.size() - 1;
        for (int y = minY; y <= maxY; y++) {
            parseRow(y, seatLayout.get(y));
        }
    }

    private void parseRow(final int y, final String row) {
        for (int index = row.indexOf('L'); index >= 0; index = row.indexOf('L', index + 1)) {
            final Point location = new Point(index, y);
            final Seat seat = new Seat(location);
            seats.put(location, seat);
        }
        for (int index = row.indexOf('#'); index >= 0; index = row.indexOf('#', index + 1)) {
            final Point location = new Point(index, y);
            final Seat seat = new Seat(location);
            seat.toggleOccupation();
            seats.put(location, seat);
        }
    }

    public void simulatePart01() {
        Set<Seat> seatsToToggleOccupation;
        do {
            seatsToToggleOccupation = seats.values()
                                           .stream()
                                           .filter(this::isEligibleForOccupationChangePart01)
                                           .collect(Collectors.toSet());
            seatsToToggleOccupation.forEach(Seat::toggleOccupation);
        } while (!seatsToToggleOccupation.isEmpty());
    }

    private boolean isEligibleForOccupationChangePart01(final Seat seat) {
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

    public void simulatePart02() {
        Set<Seat> seatsToToggleOccupation;
        do {
            seatsToToggleOccupation = seats.values()
                                           .stream()
                                           .filter(this::isEligibleForOccupationChangePart02)
                                           .collect(Collectors.toSet());
            seatsToToggleOccupation.forEach(Seat::toggleOccupation);
        } while (!seatsToToggleOccupation.isEmpty());
    }

    private boolean isEligibleForOccupationChangePart02(final Seat seat) {
        final long occupiedSeats = countVisibleOccupiedSeats(seat);
        if (seat.isOccupied()) {
            return occupiedSeats >= 5L;
        }
        return occupiedSeats == 0L;
    }

    public long countVisibleOccupiedSeats(final Seat seat) {
        return findVisibleSeats(seat).stream().filter(Seat::isOccupied).count();
    }

    public Set<Seat> findVisibleSeats(final Seat seat) {
        final Set<Optional<Seat>> possibleSeats = new HashSet<>();
        possibleSeats.add(findVisibleSeat(seat, 1, 0));
        possibleSeats.add(findVisibleSeat(seat, 1, 1));
        possibleSeats.add(findVisibleSeat(seat, 0, 1));
        possibleSeats.add(findVisibleSeat(seat, -1, 1));
        possibleSeats.add(findVisibleSeat(seat, -1, 0));
        possibleSeats.add(findVisibleSeat(seat, -1, -1));
        possibleSeats.add(findVisibleSeat(seat, 0, -1));
        possibleSeats.add(findVisibleSeat(seat, 1, -1));
        return possibleSeats.stream()
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toSet());
    }

    private Optional<Seat> findVisibleSeat(final Seat seat, final int directionX, final int directionY) {
        final Point location = new Point(seat.getLocation());
        while (true) {
            location.translate(directionX, directionY);
            if (location.x < minX || location.x > maxX || location.y < minY || location.y > maxY) {
                return Optional.empty();
            }
            if (seats.containsKey(location)) {
                return Optional.of(seats.get(location));
            }
        }
    }

    public Set<Seat> getOccupiedSeats() {
        return seats.values().stream().filter(Seat::isOccupied).collect(Collectors.toSet());
    }
}
