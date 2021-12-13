package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.submarine.camera.ThermalCameraManual;
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
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final ThermalCameraManual manual = new ThermalCameraManual(lines);
        manual.foldByInstructions(1);
        return manual.countDots();
    }
}
