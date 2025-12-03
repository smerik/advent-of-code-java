package nl.smerik.adventofcode.aoc2025.day;

import nl.smerik.adventofcode.aoc2025.model.lobby.EmergencyPower;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day03Service {

    private final EmergencyPower power;

    public Day03Service(@Value("classpath:input/day-03.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        power = new EmergencyPower(lines);
    }

    public int getSolutionPart1() {
        return power.calcTotalOutputJoltage();
    }
}
