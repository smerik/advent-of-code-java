package nl.smerik.adventofcode.aoc2024.model.stone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlutonianPebbles {

    private Map<Long, Long> stoneCountByNumber;

    public PlutonianPebbles(final List<String> lines) {
        stoneCountByNumber = parseLines(lines);
    }

    private Map<Long, Long> parseLines(final List<String> lines) {
        final Map<Long, Long> countByNumber = new HashMap<>();
        for (final String number : lines.get(0).split(" ")) {
            final Long stone = Long.parseLong(number);
            insertOrUpdate(countByNumber, stone, 1L);
        }
        return countByNumber;
    }

    public void blink(final int count) {
        for (int i = 0; i < count; i++) {
            blink();
        }
    }

    private void blink() {
        final Map<Long, Long> result = new HashMap<>();
        for (final Map.Entry<Long, Long> entry : stoneCountByNumber.entrySet()) {
            if (entry.getKey() == 0L) {
                insertOrUpdate(result, 1L, entry.getValue());
                continue;
            }
            final String stoneString = String.valueOf(entry.getKey());
            if (stoneString.length() % 2 == 0) {
                final int length = stoneString.length() / 2;
                final long leftStone = Long.parseLong(stoneString.substring(0, length));
                final long rightStone = Long.parseLong(stoneString.substring(length));
                insertOrUpdate(result, leftStone, entry.getValue());
                insertOrUpdate(result, rightStone, entry.getValue());
                continue;
            }
            insertOrUpdate(result, entry.getKey() * 2024L, entry.getValue());
        }
        stoneCountByNumber = result;
    }

    private void insertOrUpdate(final Map<Long, Long> result, final Long stone, final Long count) {
        result.compute(stone, (k, v) -> (v == null) ? count : v + count);
    }

    public long countStones() {
        long result = 0;
        for (final Long count : stoneCountByNumber.values()) {
            result += count;
        }
        return result;
    }
}
