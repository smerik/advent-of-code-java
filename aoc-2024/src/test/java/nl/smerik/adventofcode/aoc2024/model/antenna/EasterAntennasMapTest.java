package nl.smerik.adventofcode.aoc2024.model.antenna;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EasterAntennasMapTest {

    @Value("classpath:input/day-08/example-01.txt")
    private Resource example01Resource;

    @Value("classpath:input/day-08/example-02.txt")
    private Resource example02Resource;

    @Value("classpath:input/day-08/example-03.txt")
    private Resource example03Resource;

    @Value("classpath:input/day-08/example-04.txt")
    private Resource example04Resource;

    @Test
    void countUniqueAntinodeLocations() {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
//        final List<String> lines = PuzzleInputParser.parseToString(example02Resource);
//        final List<String> lines = PuzzleInputParser.parseToString(example03Resource);
//        final List<String> lines = PuzzleInputParser.parseToString(example04Resource);
        assertEquals(14, new EasterAntennasMap(lines).countUniqueAntinodeLocations());
    }
}