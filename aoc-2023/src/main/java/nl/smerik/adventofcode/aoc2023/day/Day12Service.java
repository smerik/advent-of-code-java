package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.hotsprings.HotSpringsMaintenance;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day12Service {

    private final HotSpringsMaintenance maintenance;

    public Day12Service(@Value("classpath:input/day-12.txt") final Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        maintenance = new HotSpringsMaintenance(lines);
    }

    public Integer getSolutionPart1() {
        return maintenance.sumPossibleArrangements();
    }

    public Integer getSolutionPart2() {
        return null;
    }
}
