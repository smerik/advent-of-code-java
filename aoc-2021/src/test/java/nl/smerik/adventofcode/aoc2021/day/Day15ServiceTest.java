package nl.smerik.adventofcode.aoc2021.day;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day15ServiceTest {

    @Autowired
    private Day15Service dayService;

    @Test
    void getSolutionPart1() {
        Assertions.assertEquals(458, dayService.getSolutionPart1());
    }
}