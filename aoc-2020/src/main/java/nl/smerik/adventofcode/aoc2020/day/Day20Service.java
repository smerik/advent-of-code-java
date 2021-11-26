package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.jigsaw.Jigsaw;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day20Service {

    @Value("classpath:input/day-20.txt")
    private Resource resource;

    public Long getSolutionPart1() {
        return initJigsaw().multiplyCornerTileIDs();
    }

    public Integer getSolutionPart2() {
        return initJigsaw().calculateWaterRoughness();
    }

    private Jigsaw initJigsaw() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return new Jigsaw(input);
    }
}
