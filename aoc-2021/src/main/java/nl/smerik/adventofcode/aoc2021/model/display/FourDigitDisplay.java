package nl.smerik.adventofcode.aoc2021.model.display;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Getter
public class FourDigitDisplay {

    private final List<Integer> digits;

    public FourDigitDisplay(final String note) {
        final String[] patterns = note.split(" \\| ");
        final List<String> uniqueSignalPatterns = parsePatterns(patterns[0]);
        final List<String> fourDigitOutputValue = parsePatterns(patterns[1]);

        final SevenSegmentDisplay segmentDisplay = new SevenSegmentDisplay(uniqueSignalPatterns);
        digits = segmentDisplay.parsePatterns(fourDigitOutputValue);
    }

    private List<String> parsePatterns(final String pattern) {
        return Arrays.stream(pattern.split(" ")).toList();
    }

    public int countDigits(final Set<Integer> digitsToCount) {
        int result = 0;
        for (final Integer digit : this.digits) {
            if (digitsToCount.contains(digit)) {
                result++;
            }
        }
        return result;
    }
}
