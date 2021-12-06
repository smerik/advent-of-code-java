package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.fish.LanternfishSchool;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Day06Service {

    @Value("classpath:input/day-06.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        final List<Integer> ages = Arrays.stream(input.get(0).split(",")).mapToInt(Integer::valueOf).boxed().toList();
        return new LanternfishSchool(ages).simulate(80);
    }
}
