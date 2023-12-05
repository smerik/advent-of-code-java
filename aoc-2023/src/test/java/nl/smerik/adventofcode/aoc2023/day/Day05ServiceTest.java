package nl.smerik.adventofcode.aoc2023.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day05ServiceTest {

    @Autowired
    private Day05Service day05Service;

    @Test
    void getSolutionPart1() {
        assertEquals(251346198, day05Service.getSolutionPart1());
    }

    @Test
    void getSolutionPart2() {
        assertEquals(0, day05Service.getSolutionPart2());
    }
}