package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.shuttlebus.ShuttleBusService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day13Service {

    @Value("classpath:input/day-13.txt")
    private Resource resource;

    public Long getSolutionPart1() {
        final List<String> notes = PuzzleInputParser.parseToString(resource);
        final long timestamp = Long.parseLong(notes.get(0));
        final ShuttleBusService busService = new ShuttleBusService(notes.get(1));
        return busService.findEarliestBusToTake(timestamp).calculateSolutionPart01(timestamp);
    }

    public Long getSolutionPart2() {
        final List<String> notes = PuzzleInputParser.parseToString(resource);
        final ShuttleBusService busService = new ShuttleBusService(notes.get(1));
        return busService.findSubsequentBusDeparturesTimestamp(0);
    }
}
