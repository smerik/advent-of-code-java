package nl.smerik.adventofcode.aoc2021.model.submarine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiagnosticReportTest {

    private static final List<String> REPORT_EXAMPLE_PART_01 = List.of(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
    );

    @Test
    void testFindMostCommonBit() {
        final DiagnosticReport report = new DiagnosticReport(REPORT_EXAMPLE_PART_01);
        // 10110
        assertEquals(1, report.findMostCommonBit(0)); // 1st bit
        assertEquals(0, report.findMostCommonBit(1)); // 2nd bit
        assertEquals(1, report.findMostCommonBit(2)); // 3rd bit
        assertEquals(1, report.findMostCommonBit(3)); // 4th bit
        assertEquals(0, report.findMostCommonBit(4)); // 5th bit
    }

    @Test
    void testFindLeastCommonBit() {
        final DiagnosticReport report = new DiagnosticReport(REPORT_EXAMPLE_PART_01);
        // 01001
        assertEquals(0, report.findLeastCommonBit(0)); // 1st bit
        assertEquals(1, report.findLeastCommonBit(1)); // 2nd bit
        assertEquals(0, report.findLeastCommonBit(2)); // 3rd bit
        assertEquals(0, report.findLeastCommonBit(3)); // 4th bit
        assertEquals(1, report.findLeastCommonBit(4)); // 5th bit
    }

    @Test
    void determineGammaRate() {
        final DiagnosticReport report = new DiagnosticReport(REPORT_EXAMPLE_PART_01);
        assertEquals(22, report.determineGammaRate());
    }

    @Test
    void determineEpsilonRate() {
        final DiagnosticReport report = new DiagnosticReport(REPORT_EXAMPLE_PART_01);
        assertEquals(9, report.determineEpsilonRate());
    }

    @Test
    void calculatePowerConsumption() {
        final DiagnosticReport report = new DiagnosticReport(REPORT_EXAMPLE_PART_01);
        assertEquals(198, report.calculatePowerConsumption());
    }

//    @Test
//    void testMap() {
//        final DiagnosticReport report = new DiagnosticReport(REPORT_EXAMPLE_PART_01);
//        final Map<Integer, List<String>> result = report.filterBy(REPORT_EXAMPLE_PART_01, 0);
//        assertEquals(List.of("00100", "01111", "00111", "00010", "01010"), result.get(0));
//        assertEquals(List.of("11110", "10110", "10111", "10101", "11100", "10000", "11001"), result.get(1));
//    }


    @Test
    void testDetermineOxygenGeneratorRate() {
        final DiagnosticReport report = new DiagnosticReport(REPORT_EXAMPLE_PART_01);
        assertEquals(23, report.determineOxygenGeneratorRate());
    }

    @Test
    void testDetermineCO2ScrubbingRate() {
        final DiagnosticReport report = new DiagnosticReport(REPORT_EXAMPLE_PART_01);
        assertEquals(10, report.determineCO2ScrubbingRate());
    }

    @Test
    void testCalculateLifeSupportRating() {
        final DiagnosticReport report = new DiagnosticReport(REPORT_EXAMPLE_PART_01);
        assertEquals(230, report.calculateLifeSupportRate());
    }
}