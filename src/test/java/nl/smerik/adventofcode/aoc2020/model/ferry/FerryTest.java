package nl.smerik.adventofcode.aoc2020.model.ferry;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FerryTest {

    @Test
    void navigate() {
        final List<String> instructions = List.of(
                "F10",
                "N3",
                "F7",
                "R90",
                "F11"
        );
        final Ferry ferry = new Ferry(new Point());
        ferry.navigate(instructions);
        assertEquals(new Point(17, -8), ferry.getLocation());
    }
}