package nl.smerik.adventofcode.aoc2024.model.stone;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlutonianPebbles {

    private List<Long> stones;

    public PlutonianPebbles(final List<String> lines) {
        stones = parseLines(lines);
    }

    private List<Long> parseLines(final List<String> lines) {
        return Arrays.stream(lines.get(0).split(" ")).mapToLong(Long::parseLong).boxed().toList();
    }

    public void blink(final int count) {
        for (int i = 0; i < count; i++) {
            blink();
        }
    }

    private void blink() {
        final List<Long> result = new ArrayList<>();
        for (final long stone : stones) {
            if (stone == 0L) {
                result.add(1L);
                continue;
            }
            final String stoneString = String.valueOf(stone);
            if (stoneString.length() % 2 == 0) {
                final int length = stoneString.length() / 2;
                final long leftStone = Long.parseLong(stoneString.substring(0, length));
                final long rightStone = Long.parseLong(stoneString.substring(length));
                result.add(leftStone);
                result.add(rightStone);
                continue;
            }
            result.add(stone * 2024L);
        }
        this.stones = result;
    }

    public int countStones() {
        return stones.size();
    }

    @Override
    public String toString() {
        return stones.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }
}
