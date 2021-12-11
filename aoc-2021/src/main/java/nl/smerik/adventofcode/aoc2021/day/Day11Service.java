package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.octopus.DumboOctopusCavern;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day11Service {

    @Value("classpath:input/day-11.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        return initCavern().simulateSteps(100);
    }

    public Integer getSolutionPart2() {
        return initCavern().findFirstStepAllOctopusesFlashSimultaneously();
    }

    private DumboOctopusCavern initCavern() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        return new DumboOctopusCavern(lines);
    }
}
