package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.CalibrationUtil;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day01Service {

    @Value("classpath:input/day-01.txt")
    private Resource resource;


    public Integer getSolutionPart1() {
        List<String> calibrationLines = PuzzleInputParser.parseToString(resource);
        return CalibrationUtil.sumAllCalibrationValues(calibrationLines);
    }

    public Object getSolutionPart2() {
        return null;
    }
}
