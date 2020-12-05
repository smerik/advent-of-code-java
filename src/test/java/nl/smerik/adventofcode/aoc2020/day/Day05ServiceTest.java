package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day05ServiceTest {

    @Autowired
    private Day05Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(996, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(671, dayService.getSolutionPart2());
    }
}
