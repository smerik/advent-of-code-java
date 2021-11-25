package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.joltage.JoltageAdapterArray;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day10Service {

    @Value("classpath:input/day-10.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<Integer> input = PuzzleInputParser.parseToInt(resource);
        final JoltageAdapterArray joltageAdapterArray = new JoltageAdapterArray(input);
        return joltageAdapterArray.countDifferencesForJolt(1) * joltageAdapterArray.countDifferencesForJolt(3);
    }

    public Long getSolutionPart2() {
        final List<Integer> input = PuzzleInputParser.parseToInt(resource);
        final JoltageAdapterArray joltageAdapterArray = new JoltageAdapterArray(input);
        return joltageAdapterArray.countTotalNumberOfArrangements();
    }
}
