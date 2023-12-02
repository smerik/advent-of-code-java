package nl.smerik.adventofcode.aoc2023.day;

import nl.smerik.adventofcode.aoc2023.model.game.cube.CubeGameAdministrator;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Day02Service {

    @Value("classpath:input/day-02.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        final List<String> gameLines = PuzzleInputParser.parseToString(resource);
        final CubeGameAdministrator administrator = new CubeGameAdministrator(gameLines);
        final Map<String, Integer> game = Map.of("red", 12, "green", 13, "blue", 14);
        return administrator.calcSumOfIDsForPossibleGames(game);
    }

    public Integer getSolutionPart2() {
        return null;
    }
}
