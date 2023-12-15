package nl.smerik.adventofcode.aoc2023.model.lenslibrary;

import java.util.Arrays;
import java.util.List;

public class InitializationProcessor {

    private final List<Integer> initializationSteps;

    public InitializationProcessor(final String initializationSequence) {
        initializationSteps = parseSequence(initializationSequence);
    }

    private List<Integer> parseSequence(final String lines) {
        return Arrays.stream(lines.split(",")).map(AsciiStringHelper::hash).toList();
    }

    public int sumInitializationSteps() {
        return initializationSteps.stream().mapToInt(Integer::intValue).sum();
    }
}
