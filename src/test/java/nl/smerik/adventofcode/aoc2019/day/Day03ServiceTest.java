package nl.smerik.adventofcode.aoc2019.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day03ServiceTest {

    @Autowired
    private Day03Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(2050, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(21666, dayService.getSolutionPart2());
    }
}
