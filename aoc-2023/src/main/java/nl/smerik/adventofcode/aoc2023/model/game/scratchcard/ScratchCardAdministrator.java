package nl.smerik.adventofcode.aoc2023.model.game.scratchcard;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScratchCardAdministrator {

    private final Map<Integer, ScratchCardGame> gamesByCardNumber;

    public ScratchCardAdministrator(final List<String> lines) {
        gamesByCardNumber = parseLines(lines);
    }

    private Map<Integer, ScratchCardGame> parseLines(List<String> lines) {
        return lines.stream().map(ScratchCardGame::new).collect(Collectors.toMap(ScratchCardGame::getCardNumber, Function.identity()));
    }

    public int sumPoints() {
        return gamesByCardNumber.values().stream().map(ScratchCardGame::determinePoints).mapToInt(Integer::intValue).sum();
    }
}
