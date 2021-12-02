package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.submarine.Submarine;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day02Service {

    @Value("classpath:input/day-02.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> commands = PuzzleInputParser.parseToString(resource);
        final Submarine submarine = new Submarine();
        submarine.move(commands);
        return submarine.calculateSolutionDay2();
    }

    public Integer getSolutionPart2() {
        final List<String> commands = PuzzleInputParser.parseToString(resource);
        final Submarine submarine = new Submarine();
        submarine.moveByAim(commands);
        return submarine.calculateSolutionDay2();
    }
}
