package nl.smerik.adventofcode.aoc2023.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalibrationUtilTest {

    private final List<String> example01Part01 = List.of(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
    );

    @Test
    void testCalcSumOfAllCalibrationValues() {
        assertEquals(142, CalibrationUtil.sumAllCalibrationValues(example01Part01));
    }

    @Test
    void testFindCalibrationValue() {
        assertEquals(12, CalibrationUtil.findCalibrationValue(example01Part01.get(0)));
        assertEquals(38, CalibrationUtil.findCalibrationValue(example01Part01.get(1)));
        assertEquals(15, CalibrationUtil.findCalibrationValue(example01Part01.get(2)));
        assertEquals(77, CalibrationUtil.findCalibrationValue(example01Part01.get(3)));
    }
}