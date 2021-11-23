package nl.smerik.adventofcode.aoc2019.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day09ServiceTest {

    @Autowired
    private Day09Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(3454977209L, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(50120L, dayService.getSolutionPart2());

    }
}
