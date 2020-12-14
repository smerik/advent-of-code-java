package nl.smerik.adventofcode.aoc2020.model.ferry;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DockingProgram {

    private static final Pattern MASK_PATTERN = Pattern.compile("mask = (?<mask>[X01]{36})");
    private static final Pattern MEMORY_PATTERN = Pattern.compile("mem\\[(?<address>\\d+)\\] = (?<value>\\d+)");

    @Getter
    private final Map<Integer, Long> memory;

    private Long andMask;
    private Long orMask;

    public DockingProgram() {
        memory = new HashMap<>();
        andMask = 0L;
        orMask = 0L;
    }

    public void run(final List<String> program) {
        for (final String line : program) {
            final Matcher maskMatcher = MASK_PATTERN.matcher(line);
            if (maskMatcher.find()) {
                updateBitmask(maskMatcher.group("mask"));
                continue;
            }

            final Matcher memoryMatcher = MEMORY_PATTERN.matcher(line);
            if (memoryMatcher.find()) {
                writeToMemory(
                        Integer.parseInt(memoryMatcher.group("address")),
                        Long.parseLong(memoryMatcher.group("value"))
                );
                continue;
            }

            throw new IllegalArgumentException("Unknown program line '" + line + "'");
        }
    }

    public void updateBitmask(final String bitmask) {
        andMask = Long.parseLong(bitmask.replace('X', '1'), 2);
        orMask = Long.parseLong(bitmask.replace('X', '0'), 2);
    }

    public void writeToMemory(final int address, final long value) {
        memory.put(address, applyBitmask(value));
    }

    private Long applyBitmask(final long value) {
        return value & andMask | orMask;
    }

    public long sumMemory() {
        return memory.values().stream().mapToLong(Long::longValue).sum();
    }
}
