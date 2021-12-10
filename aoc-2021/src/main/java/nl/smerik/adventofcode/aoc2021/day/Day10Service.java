package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.submarine.navigation.NavigationSubsystemUtil;
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
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return NavigationSubsystemUtil.calculateTotalSyntaxErrorScore(input);
    }

    public Long getSolutionPart2() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return NavigationSubsystemUtil.determineMedianOfCompletionScores(input);
    }
}
