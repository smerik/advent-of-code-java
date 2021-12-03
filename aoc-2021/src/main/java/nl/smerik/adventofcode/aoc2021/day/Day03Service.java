package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.submarine.DiagnosticReport;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day03Service {

    @Value("classpath:input/day-03.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> binaryNumbers = PuzzleInputParser.parseToString(resource);
        return new DiagnosticReport(binaryNumbers).calculatePowerConsumption();
    }

    public Integer getSolutionPart2() {
        final List<String> binaryNumbers = PuzzleInputParser.parseToString(resource);
        return new DiagnosticReport(binaryNumbers).calculateLifeSupportRate();
    }
}
