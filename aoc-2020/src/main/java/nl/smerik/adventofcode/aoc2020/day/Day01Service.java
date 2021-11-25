package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.service.ExpenseReportService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day01Service {

    private final ExpenseReportService expenseReportService;

    @Value("classpath:input/day-01.txt")
    private Resource resource;

    public Day01Service(final ExpenseReportService expenseReportService) {
        this.expenseReportService = expenseReportService;
    }

    public Long getSolutionPart1() {
        final List<Integer> report = PuzzleInputParser.parseToInt(resource);
        return expenseReportService.calculateExpenseReport(report);
    }

    public Long getSolutionPart2() {
        final List<Integer> report = PuzzleInputParser.parseToInt(resource);
        return expenseReportService.calculateExpenseReportForThreeNumbers(report);
    }
}
