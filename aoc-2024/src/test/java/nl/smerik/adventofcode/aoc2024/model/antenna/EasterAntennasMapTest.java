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

    @Value("classpath:input/day-08/example-05.txt")
    private Resource example05Resource;

    @Test
    void testCountUniqueAntinodeLocations() {
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01Resource);
        assertEquals(14, new EasterAntennasMap(linesExample01).countUniqueAntinodeLocations(false));
        assertEquals(34, new EasterAntennasMap(linesExample01).countUniqueAntinodeLocations(true));

        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02Resource);
        assertEquals(2, new EasterAntennasMap(linesExample02).countUniqueAntinodeLocations(false));

        final List<String> linesExample03 = PuzzleInputParser.parseToString(example03Resource);
        assertEquals(4, new EasterAntennasMap(linesExample03).countUniqueAntinodeLocations(false));

        final List<String> linesExample04 = PuzzleInputParser.parseToString(example04Resource);
        assertEquals(4, new EasterAntennasMap(linesExample04).countUniqueAntinodeLocations(false));

        final List<String> linesExample05 = PuzzleInputParser.parseToString(example05Resource);
        assertEquals(9, new EasterAntennasMap(linesExample05).countUniqueAntinodeLocations(true));
    }
}