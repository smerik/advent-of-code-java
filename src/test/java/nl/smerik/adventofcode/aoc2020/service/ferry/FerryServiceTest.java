package nl.smerik.adventofcode.aoc2020.service.ferry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.Point;
import java.util.List;

@SpringBootTest
class FerryServiceTest {

    @Autowired
    private FerryService ferryService;

    @Test
    void calculateManhattanDistance() {
        final List<String> instructions = List.of(
                "F10",
                "N3",
                "F7",
                "R90",
                "F11"
        );
        Assertions.assertEquals(25, ferryService.calculateManhattanDistance(new Point(), instructions));
    }
}
