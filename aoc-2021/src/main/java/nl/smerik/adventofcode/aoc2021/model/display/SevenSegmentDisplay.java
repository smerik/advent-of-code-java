package nl.smerik.adventofcode.aoc2021.model.display;

import java.util.*;

public class SevenSegmentDisplay {

    private static final Map<Set<Character>, Integer> DIGITS_BY_SEGMENTS = Map.of(
            Set.of('a', 'b', 'c', 'e', 'f', 'g'), 0,
            Set.of('c', 'f'), 1,
            Set.of('a', 'c', 'd', 'e', 'g'), 2,
            Set.of('a', 'c', 'd', 'f', 'g'), 3,
            Set.of('b', 'c', 'd', 'f'), 4,
            Set.of('a', 'b', 'd', 'f', 'g'), 5,
            Set.of('a', 'b', 'd', 'e', 'f', 'g'), 6,
            Set.of('a', 'c', 'f'), 7,
            Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g'), 8,
            Set.of('a', 'b', 'c', 'd', 'f', 'g'), 9
    );

    private final Map<Set<Character>, Integer> digitsByRandomSegments;

    public SevenSegmentDisplay(final List<String> uniqueSignalPatterns) {
        this.digitsByRandomSegments = determineRandomSegmentsByDigit(uniqueSignalPatterns);
    }


    private Map<Set<Character>, Integer> determineRandomSegmentsByDigit(final List<String> patterns) {
        final Map<Set<Character>, Integer> result = new HashMap<>();
        for (final String pattern : patterns) {
            result.putAll(determineSegmentsByPatternLength(pattern));
        }
        return result;
    }

    private Map<Set<Character>, Integer> determineSegmentsByPatternLength(final String pattern) {
        for (final Map.Entry<Set<Character>, Integer> entry : DIGITS_BY_SEGMENTS.entrySet()) {
            if (entry.getKey().size() == pattern.length()) {
                final Set<Character> randomSegments = parsePattern(pattern);
                return Map.of(randomSegments, entry.getValue());
            }
        }
        return new HashMap<>();
    }

    /**
     * Parses given four digit output value into a list if integers that would be rendered.
     *
     * @param fourDigitOutputValue the
     * @return the rendered digits
     */
    public List<Integer> parsePatterns(final List<String> fourDigitOutputValue) {
        final List<Integer> result = new ArrayList<>();
        for (final String digitPattern : fourDigitOutputValue) {
            result.add(this.digitsByRandomSegments.get(parsePattern(digitPattern)));
        }
        return result;
    }

    private Set<Character> parsePattern(final String pattern) {
        final Set<Character> segments = new HashSet<>();
        for (final Character character : pattern.toCharArray()) {
            segments.add(character);
        }
        return segments;
    }
}
