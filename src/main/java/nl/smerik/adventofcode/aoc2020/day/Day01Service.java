package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.service.ExpenseReportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class Day01Service {

    private final ExpenseReportService expenseReportService;

    @Value("classpath:input/day-01.txt")
    private Resource resource;

    public Day01Service(final ExpenseReportService expenseReportService) {
        this.expenseReportService = expenseReportService;
    }

    @SneakyThrows
    public Long getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Integer> report = stringStream.mapToInt(Integer::valueOf)
                                                     .boxed()
                                                     .collect(Collectors.toList());
            return expenseReportService.calculateExpenseReport(report);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Long getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<Integer> report = stringStream.mapToInt(Integer::valueOf)
                                                     .boxed()
                                                     .collect(Collectors.toList());
            return expenseReportService.calculateExpenseReportForThreeNumbers(report);
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
