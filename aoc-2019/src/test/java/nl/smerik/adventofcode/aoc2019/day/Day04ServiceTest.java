package nl.smerik.adventofcode.aoc2019.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Day04ServiceTest {

    @Autowired
    private Day04Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(1675, dayService.getSolutionPart1(172930, 683082));
    }

    @Test
    void getSolutionPart2() {
        assertEquals(1142, dayService.getSolutionPart2(172930, 683082));
    }
}
