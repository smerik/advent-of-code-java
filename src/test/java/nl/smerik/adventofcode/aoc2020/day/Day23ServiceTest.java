package nl.smerik.adventofcode.aoc2020.day;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Day23ServiceTest {

    @Autowired
    private Day23Service dayService;

    @Test
    void getSolutionPart1() {
        assertEquals("47598263", dayService.getSolutionPart1());
    }
}