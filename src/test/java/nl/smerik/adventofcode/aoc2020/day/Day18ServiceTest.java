package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day18ServiceTest {

    @Autowired
    private Day18Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals(14006719520523L, dayService.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(545115449981968L, dayService.getSolutionPart2());
    }
}