package nl.smerik.adventofcode.aoc2020.model.waitingarea;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WaitingAreaTest {

    private static final List<String> layoutPart01Example01 = List.of(
            "L.LL.LL.LL",
            "LLLLLLL.LL",
            "L.L.L..L..",
            "LLLL.LL.LL",
            "L.LL.LL.LL",
            "L.LLLLL.LL",
            "..L.L.....",
            "LLLLLLLLLL",
            "L.LLLLLL.L",
            "L.LLLLL.LL"
    );

    @Test
    void simulate() {
        final WaitingArea area = new WaitingArea(layoutPart01Example01);
        area.simulate();
        assertEquals(37, area.getOccupiedSeats().size());
    }

    @Test
    void countSurroundedOccupiedSeats() {
        final WaitingArea area = new WaitingArea(layoutPart01Example01);
        assertEquals(0, area.countSurroundedOccupiedSeats(new Seat(new Point(0, 0))));
        assertEquals(0, area.countSurroundedOccupiedSeats(new Seat(new Point(2, 0))));
        assertEquals(0, area.countSurroundedOccupiedSeats(new Seat(new Point(3, 0))));
        assertEquals(0, area.countSurroundedOccupiedSeats(new Seat(new Point(1, 1))));

        area.simulate();
        assertEquals(1, area.countSurroundedOccupiedSeats(new Seat(new Point(0, 0))));
        assertEquals(0, area.countSurroundedOccupiedSeats(new Seat(new Point(2, 0))));
        assertEquals(2, area.countSurroundedOccupiedSeats(new Seat(new Point(3, 0))));
        assertEquals(4, area.countSurroundedOccupiedSeats(new Seat(new Point(1, 1))));
    }

    @Test
    void getAdjacentSeats() {
        final WaitingArea area = new WaitingArea(layoutPart01Example01);

        final Set<Seat> expectedSeats = Set.of(
                new Seat(new Point(3, 1)),
                new Seat(new Point(4, 1)),
                new Seat(new Point(5, 1)),

                new Seat(new Point(3, 3)),
                new Seat(new Point(5, 3))
        );
        assertEquals(expectedSeats, area.getAdjacentSeats(new Seat(new Point(4, 2))));
    }

    @Test
    void getOccupiedSeats() {
        final WaitingArea area = new WaitingArea(layoutPart01Example01);

        assertTrue(area.getOccupiedSeats().isEmpty());

        area.simulate();
        final Set<Seat> occupiedSeats = area.getOccupiedSeats();
        assertEquals(37, occupiedSeats.size());
        final Set<Seat> expectedResult = Set.of(
                new Seat(new Point(0, 0)),
                new Seat(new Point(2, 0)),
                new Seat(new Point(6, 0)),
                new Seat(new Point(8, 0)),
                new Seat(new Point(9, 0)),

                new Seat(new Point(0, 1)),
                new Seat(new Point(4, 1)),
                new Seat(new Point(9, 1)),

                new Seat(new Point(2, 2)),
                new Seat(new Point(7, 2)),

                new Seat(new Point(0, 3)),
                new Seat(new Point(2, 3)),
                new Seat(new Point(3, 3)),
                new Seat(new Point(5, 3)),
                new Seat(new Point(6, 3)),
                new Seat(new Point(9, 3)),

                new Seat(new Point(0, 4)),
                new Seat(new Point(2, 4)),

                new Seat(new Point(0, 5)),
                new Seat(new Point(2, 5)),
                new Seat(new Point(4, 5)),
                new Seat(new Point(6, 5)),
                new Seat(new Point(8, 5)),
                new Seat(new Point(9, 5)),

                new Seat(new Point(0, 7)),
                new Seat(new Point(2, 7)),
                new Seat(new Point(4, 7)),
                new Seat(new Point(5, 7)),
                new Seat(new Point(7, 7)),
                new Seat(new Point(9, 7)),

                new Seat(new Point(0, 8)),

                new Seat(new Point(0, 9)),
                new Seat(new Point(2, 9)),
                new Seat(new Point(4, 9)),
                new Seat(new Point(6, 9)),
                new Seat(new Point(8, 9)),
                new Seat(new Point(9, 9))
        );
        assertEquals(expectedResult, occupiedSeats);
    }
}