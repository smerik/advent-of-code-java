package nl.smerik.adventofcode.aoc2020.day;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day08ServiceTest {

    @Autowired
    private Day08Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(1859, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(1235, dayService.getSolutionPart2());
    }
}