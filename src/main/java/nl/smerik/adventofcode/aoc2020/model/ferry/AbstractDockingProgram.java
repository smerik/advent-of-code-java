package nl.smerik.adventofcode.aoc2020.model.ferry;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractDockingProgram {

    private static final Pattern MASK_PATTERN = Pattern.compile("mask = (?<mask>[X01]{36})");
    private static final Pattern MEMORY_PATTERN = Pattern.compile("mem\\[(?<address>\\d+)\\] = (?<value>\\d+)");

    @Getter
    protected final Map<Long, Long> memory;

    protected AbstractDockingProgram() {
        memory = new HashMap<>();
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
                        Long.parseLong(memoryMatcher.group("address")),
                        Long.parseLong(memoryMatcher.group("value"))
                );
                continue;
            }

            throw new IllegalArgumentException("Unknown program line '" + line + "'");
        }
    }

    public long sumMemory() {
        return memory.values().stream().mapToLong(Long::longValue).sum();
    }

    public abstract void updateBitmask(String bitmask);

    public abstract void writeToMemory(long address, long value);
}
