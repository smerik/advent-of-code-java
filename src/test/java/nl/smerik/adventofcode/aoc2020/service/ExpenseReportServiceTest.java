package nl.smerik.adventofcode.aoc2020.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseReportServiceTest {

    @Test
    void calculateExpenseReport() {
        final List<Integer> input = List.of(1721, 979, 366, 299, 675, 1456);

        final ExpenseReportService service = new ExpenseReportService();

        assertEquals(514579, service.calculateExpenseReport(input));
    }

    @Test
    void calculateExpenseReportForThreeNumbers() {
        final List<Integer> input = List.of(1721, 979, 366, 299, 675, 1456);

        final ExpenseReportService service = new ExpenseReportService();

        assertEquals(241861950, service.calculateExpenseReportForThreeNumbers(input));
    }
}