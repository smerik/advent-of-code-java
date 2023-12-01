package nl.smerik.adventofcode.aoc2023.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalibrationUtil {

    private CalibrationUtil() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static final Pattern DIGIT_LINE_PATTERN = Pattern.compile("\\d");

    /**
     * Calculates the sum of all calibration values per calibration line.
     *
     * @param calibrationLines the calibration lines to calculate the sum of
     * @return the sum of all calibration values
     */
    public static int sumAllCalibrationValues(final List<String> calibrationLines) {
        return calibrationLines.stream().mapToInt(CalibrationUtil::findCalibrationValue).sum();
    }

    /**
     * Finds the calibration value by combining the first digit and the last digit to form a single two-digit number.
     *
     * @param calibrationLine the line to find the value for
     * @return the calibration value
     */
    public static int findCalibrationValue(final String calibrationLine) {
        final Matcher matcher = DIGIT_LINE_PATTERN.matcher(calibrationLine);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid calibration line: '" + calibrationLine + "'");
        }
        final String firstDigit = matcher.group();
        String lastDigit = null;
        while (matcher.find()) {
            lastDigit = matcher.group();
        }
        if (lastDigit == null) {
            lastDigit = firstDigit;
        }
        return Integer.parseInt(firstDigit + lastDigit);
    }
}
