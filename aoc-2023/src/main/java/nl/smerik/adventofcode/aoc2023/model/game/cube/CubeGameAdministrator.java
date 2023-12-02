package nl.smerik.adventofcode.aoc2023.model.game.cube;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CubeGameAdministrator {

    private final Set<CubeGame> games;

    public CubeGameAdministrator(final List<String> lines) {
        games = new HashSet<>(lines.size());
        for (String line : lines) {
            games.add(new CubeGame(line));
        }
    }

    public int calcSumOfIDsForPossibleGames(Map<String, Integer> gameCubeSet) {
        return games.stream().filter(game -> game.isGamePossible(gameCubeSet)).mapToInt(CubeGame::getId).sum();
    }

    public int calcSumOfPowerOfFewestNumberOfGames() {
        return games.stream().mapToInt(CubeGame::calcPowerOfFewestNumberOfGames).sum();
    }
}
