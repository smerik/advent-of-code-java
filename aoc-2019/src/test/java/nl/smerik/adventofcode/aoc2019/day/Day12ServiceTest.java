package nl.smerik.adventofcode.aoc2019.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day12ServiceTest {

    @Autowired
    private Day12Service dayService;

    @Test
    void getSolutionPart1() throws Exception {
        assertEquals(8362, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() throws Exception {
        assertEquals(478373365921244L, dayService.getSolutionPart2());
    }
}
