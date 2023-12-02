package nl.smerik.adventofcode.aoc2023.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day02ServiceTest {

    @Autowired
    private Day02Service day02Service;

    @Test
    void getSolutionPart1() {
        assertEquals(1853, day02Service.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(0, day02Service.getSolutionPart2());
    }
}