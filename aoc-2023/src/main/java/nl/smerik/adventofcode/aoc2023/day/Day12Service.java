package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.hotsprings.HotSpringsMaintenance;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day12Service {

    private final List<String> lines;

    public Day12Service(@Value("classpath:input/day-12.txt") final Resource resource) {
        lines = PuzzleInputParser.parseToString(resource);
    }

    public Long getSolutionPart1() {
        final HotSpringsMaintenance maintenance = new HotSpringsMaintenance(lines, 1);
        return maintenance.sumPossibleArrangements();
    }

    public Long getSolutionPart2() {
        final HotSpringsMaintenance maintenance = new HotSpringsMaintenance(lines, 5);
        return maintenance.sumPossibleArrangements();
    }
}
