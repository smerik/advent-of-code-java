package nl.smerik.adventofcode.aoc2019.day;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day10ServiceTest {

    @Autowired
    private Day10Service dayService;

    @Disabled
    @Test
    void getSolutionPart1() throws Exception {
        assertEquals(347, dayService.getSolutionPart1());
    }

    @Disabled
    @Test
    void getSolutionPart2() throws Exception {
        assertEquals(0, dayService.getSolutionPart2());
    }
}
