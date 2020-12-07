package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day07ServiceTest {

    @Autowired
    private Day07Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(296, dayService.getSolutionPart1());
    }
}
