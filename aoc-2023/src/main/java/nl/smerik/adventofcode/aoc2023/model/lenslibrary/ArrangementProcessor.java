package nl.smerik.adventofcode.aoc2023.model.lenslibrary;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class ArrangementProcessor {

    private static final Pattern STEP_PATTERN = Pattern.compile("(?<label>[a-z]+)(?<operator>[-=])(?<focalLength>\\d)?");

    private final Map<Integer, LensBox> lensBoxesByAsciiHash;

    public ArrangementProcessor(final String initializationSequence) {
        lensBoxesByAsciiHash = new HashMap<>();
        parseSequence(initializationSequence);
    }

    private void parseSequence(final String sequence) {
        final String[] steps = sequence.split(",");
        parseSteps(steps);
    }

    private void parseSteps(final String[] steps) {
        for (final String step : steps) {
            final Matcher matcher = STEP_PATTERN.matcher(step);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Incorrect initialization step '" + step + "'");
            }
            final String label = matcher.group("label");
            final String operator = matcher.group("operator");
            final int asciiHash = AsciiStringHelper.hash(label);
            final LensBox box = lensBoxesByAsciiHash.computeIfAbsent(asciiHash, k -> new LensBox(asciiHash));
            switch (operator) {
                case "-" -> box.remove(label);
                case "=" -> box.add(label, Integer.parseInt(matcher.group("focalLength")));
                default -> throw new IllegalArgumentException("Unimplemented operator '" + operator + "'");
            }
        }
    }

    public int sumFocusingPower() {
        return lensBoxesByAsciiHash.values().stream().map(LensBox::sumFocusingPower).mapToInt(Integer::intValue).sum();
    }
}