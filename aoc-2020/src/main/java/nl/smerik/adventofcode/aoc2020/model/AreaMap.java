package nl.smerik.adventofcode.aoc2020.model;

import lombok.Getter;

import java.util.List;

@Getter
public class AreaMap {

    private final char[][] map;

    public AreaMap(final List<String> input) {
        map = parseInput(input);
    }

    private char[][] parseInput(final List<String> input) {
        final char[][] result = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            result[i] = input.get(i).toCharArray();
        }
        return result;
    }
}
