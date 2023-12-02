package nl.smerik.adventofcode.aoc2023.model.game.cube;

import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CubeGame {

    @Getter
    private final int id;

    private final Set<Map<String, Integer>> subsets;

    public CubeGame(final String line) {
        final String[] split = line.split(":");
        this.id = parseGameId(split[0]);
        this.subsets = parseSubsets(split[1]);
    }

    private int parseGameId(String line) {
        final String[] split = line.split(" ");
        return Integer.parseInt(split[1]);
    }

    private Set<Map<String, Integer>> parseSubsets(final String line) {
        final String[] subsets = line.split(";");

        final Set<Map<String, Integer>> result = new HashSet<>();
        for (final String subset : subsets) {
            result.add(parseSubset(subset));
        }
        return result;
    }

    private Map<String, Integer> parseSubset(final String line) {
        // TODO: optimize implementation. Its iterating is redundant
        String[] cubeSet = line.split(",");
        final Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < cubeSet.length; i++) {
            result.putAll(parseCubeCount(cubeSet));
        }
        return result;
    }

    private Map<String, Integer> parseCubeCount(final String[] cubeSet) {
        final Map<String, Integer> result = new HashMap<>();
        for (String cubeCount : cubeSet) {
            String[] split = cubeCount.trim().split(" ");
            result.put(split[1], Integer.valueOf(split[0]));

        }
        return result;
    }

    public boolean isGamePossible(final Map<String, Integer> gameCubeSet) {
        for (final Map<String, Integer> subset : subsets) {
            for (Map.Entry<String, Integer> cubeSet : subset.entrySet()) {
                if (cubeSet.getValue() > gameCubeSet.get(cubeSet.getKey())) {
                    return false;
                }
            }
        }
        return true;
    }

    public int calcPowerOfFewestNumberOfGames() {
        return determineFewestNumberOfCubes().values().stream().reduce(1, (a, b) -> a * b);
    }

    public Map<String, Integer> determineFewestNumberOfCubes() {
        final Map<String, Integer> result = new HashMap<>();
        for (Map<String, Integer> subset : subsets) {
            for (Map.Entry<String, Integer> cubeColorCount : subset.entrySet()) {
                if (!result.containsKey(cubeColorCount.getKey())) {
                    result.put(cubeColorCount.getKey(), cubeColorCount.getValue());
                    continue;
                }
                if (cubeColorCount.getValue() > result.get(cubeColorCount.getKey())) {
                    result.put(cubeColorCount.getKey(), cubeColorCount.getValue());
                }
            }
        }
        return result;
    }
}
