package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day15ServiceTest {

    @Autowired
    private Day15Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(1111, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(48568, dayService.getSolutionPart2());
    }
}