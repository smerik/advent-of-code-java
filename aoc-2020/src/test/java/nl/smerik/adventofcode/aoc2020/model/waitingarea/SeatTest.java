package nl.smerik.adventofcode.aoc2020.model.waitingarea;

import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeatTest {

    @Test
    void toggleOccupation() {
        final Seat seat = new Seat(new Point());
        assertFalse(seat.isOccupied());

        seat.toggleOccupation();
        assertTrue(seat.isOccupied());
    }
}