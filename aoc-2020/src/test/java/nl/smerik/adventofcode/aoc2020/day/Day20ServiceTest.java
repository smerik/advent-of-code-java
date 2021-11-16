package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day20ServiceTest {

    @Autowired
    private Day20Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(18411576553343L, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(2002, dayService.getSolutionPart2());
    }
}