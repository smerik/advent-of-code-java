package nl.smerik.adventofcode.aoc2019.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day17ServiceTest {

    @Autowired
    private Day17Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(4044, dayService.getSolutionPart1());
    }
}