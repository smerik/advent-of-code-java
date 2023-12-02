package nl.smerik.adventofcode.aoc2023.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalibrationUtilTest {

    private final List<String> example01Part01 = List.of(
            // @formatter:off
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
            // @formatter:on
    );

    private final List<String> example02Part02 = List.of(
            // @formatter:off
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
            // @formatter:on
    );

    @Test
    void testCalcSumOfAllCalibrationValues() {
        assertEquals(142, CalibrationUtil.sumAllCalibrationValues(example01Part01, false));
        assertEquals(281, CalibrationUtil.sumAllCalibrationValues(example02Part02, true));
    }

    @Test
    void testFindCalibrationValue() {
        // Example 01 - Part 01
        assertEquals(12, CalibrationUtil.findCalibrationValue(example01Part01.get(0), false));
        assertEquals(38, CalibrationUtil.findCalibrationValue(example01Part01.get(1), false));
        assertEquals(15, CalibrationUtil.findCalibrationValue(example01Part01.get(2), false));
        assertEquals(77, CalibrationUtil.findCalibrationValue(example01Part01.get(3), false));
        // Example 02 - Part 02
        assertEquals(29, CalibrationUtil.findCalibrationValue(example02Part02.get(0), true));
        assertEquals(83, CalibrationUtil.findCalibrationValue(example02Part02.get(1), true));
        assertEquals(13, CalibrationUtil.findCalibrationValue(example02Part02.get(2), true));
        assertEquals(24, CalibrationUtil.findCalibrationValue(example02Part02.get(3), true));
        assertEquals(42, CalibrationUtil.findCalibrationValue(example02Part02.get(4), true));
        assertEquals(14, CalibrationUtil.findCalibrationValue(example02Part02.get(5), true));
        assertEquals(76, CalibrationUtil.findCalibrationValue(example02Part02.get(6), true));
        // Tricky lines
        assertEquals(82, CalibrationUtil.findCalibrationValue("eightwo", true));
        assertEquals(83, CalibrationUtil.findCalibrationValue("eighthree", true));
        assertEquals(18, CalibrationUtil.findCalibrationValue("oneight", true));
        assertEquals(38, CalibrationUtil.findCalibrationValue("threeight", true));
        assertEquals(58, CalibrationUtil.findCalibrationValue("fiveight", true));
        assertEquals(98, CalibrationUtil.findCalibrationValue("nineight", true));
        assertEquals(79, CalibrationUtil.findCalibrationValue("sevenine", true));
    }
}