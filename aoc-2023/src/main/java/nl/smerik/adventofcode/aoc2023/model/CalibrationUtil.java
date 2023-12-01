package nl.smerik.adventofcode.aoc2023.model;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalibrationUtil {

    private CalibrationUtil() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static final Map<String, String> SPELLED_DIGITS = Map.of(
            // @formatter:off
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
            // @formatter:on
    );

    private static final Pattern DIGIT_LINE_PATTERN = Pattern.compile("(\\d)");
    private static final Pattern DIGIT_SPELLED_LINE_PATTERN = Pattern.compile("(?=(\\d|" + String.join("|", SPELLED_DIGITS.keySet()) + "))");

    /**
     * Calculates the sum of all calibration values per calibration line.
     *
     * @param calibrationLines         the calibration lines to calculate the sum of
     * @param includeSpelledOutLetters true whether to include digits spelled out as letters; false otherwise
     * @return the sum of all calibration values
     */
    public static int sumAllCalibrationValues(final List<String> calibrationLines, boolean includeSpelledOutLetters) {
        return calibrationLines.stream().mapToInt(line -> findCalibrationValue(line, includeSpelledOutLetters)).sum();
    }

    /**
     * Finds the calibration value by combining the first digit and the last digit to form a single two-digit number.
     *
     * @param calibrationLine the line to find the value for
     * @return the calibration value
     */
    public static int findCalibrationValue(final String calibrationLine, boolean includeSpelledOutLetters) {
        final Matcher matcher = includeSpelledOutLetters ? DIGIT_SPELLED_LINE_PATTERN.matcher(calibrationLine) : DIGIT_LINE_PATTERN.matcher(calibrationLine);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid calibration line: '" + calibrationLine + "'");
        }
        final String firstDigit = mapDigitString(matcher.group(1));
        String lastDigit = null;
        while (matcher.find()) {
            lastDigit = matcher.group(1);
        }
        if (lastDigit == null) {
            lastDigit = firstDigit;
        }
        lastDigit = mapDigitString(lastDigit);
        return Integer.parseInt(firstDigit + lastDigit);
    }

    private static String mapDigitString(String digitString) {
        if (SPELLED_DIGITS.containsKey(digitString)) {
            return SPELLED_DIGITS.get(digitString);
        }
        return digitString;
    }
}
