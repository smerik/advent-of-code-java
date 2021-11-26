package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.lobby.LobbyLayout;
import nl.smerik.adventofcode.aoc2020.model.lobby.TileColor;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day24Service {

    @Value("classpath:input/day-24.txt")
    private Resource resource;

    public Long getSolutionPart1() {
        return initLobbyLayout().countTilesWithColorUp(TileColor.BLACK);
    }

    public Long getSolutionPart2() {
        final LobbyLayout lobby = initLobbyLayout();
        lobby.flipTiles(100);
        return lobby.countTilesWithColorUp(TileColor.BLACK);
    }

    private LobbyLayout initLobbyLayout() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return new LobbyLayout(input);
    }
}
