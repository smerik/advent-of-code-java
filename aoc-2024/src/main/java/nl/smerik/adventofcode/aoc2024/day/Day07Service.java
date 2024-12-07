package nl.smerik.adventofcode.aoc2024.day;

import nl.smerik.adventofcode.aoc2024.model.bridge.BridgeCalibration;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day07Service {

    private final BridgeCalibration calibration;

    public Day07Service(@Value("classpath:input/day-07.txt") Resource resource) {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        calibration = new BridgeCalibration(lines);
    }

    public long getSolutionPart1() {
        return calibration.calculateTotalCalibrationResult();
    }
}
