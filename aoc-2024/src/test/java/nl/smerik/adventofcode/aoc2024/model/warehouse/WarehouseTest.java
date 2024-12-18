package nl.smerik.adventofcode.aoc2024.model.warehouse;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseTest {

    @Value("classpath:input/day-15/example-01.txt")
    private Resource example01Resource;

    @Value("classpath:input/day-15/example-01-result.txt")
    private Resource example01ResultResource;

    @Value("classpath:input/day-15/example-01-scaled-up.txt")
    private Resource example01ScaledUpResource;

    @Value("classpath:input/day-15/example-01-scaled-up-result.txt")
    private Resource example01ScaledUpResultResource;

    @Value("classpath:input/day-15/example-02.txt")
    private Resource example02Resource;

    @Value("classpath:input/day-15/example-02-result.txt")
    private Resource example02ResultResource;

    @Value("classpath:input/day-15/example-03.txt")
    private Resource example03Resource;

    @Value("classpath:input/day-15/example-03-scaled-up.txt")
    private Resource example03ScaledUpResource;

    @Value("classpath:input/day-15/example-04.txt")
    private Resource example04Resource;

    @Value("classpath:input/day-15/example-04-scaled-up.txt")
    private Resource example04ScaledUpResource;

    @Value("classpath:input/day-15/example-04-scaled-up-result.txt")
    private Resource example04ScaledUpResultResource;

    @Test
    void testMoveRobot() {
        // Given
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01Resource);
        final Warehouse warehouseExample01 = new Warehouse(linesExample01, false);
        // When
        warehouseExample01.moveRobot();
        // Then
        final List<String> linesExample01Result = PuzzleInputParser.parseToString(example01ResultResource);
        final Warehouse warehouseExample01Result = new Warehouse(linesExample01Result, false);
        assertEquals(warehouseExample01Result.render(), warehouseExample01.render());

        // Given
        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02Resource);
        final Warehouse warehouseExample02 = new Warehouse(linesExample02, false);
        // When
        warehouseExample02.moveRobot();
        // Then
        final List<String> linesExample02Result = PuzzleInputParser.parseToString(example02ResultResource);
        final Warehouse warehouseExample02Result = new Warehouse(linesExample02Result, false);
        assertEquals(warehouseExample02Result.render(), warehouseExample02.render());

        // Given
        final List<String> linesExample04 = PuzzleInputParser.parseToString(example04Resource);
        final Warehouse warehouseExample04ScaledUp = new Warehouse(linesExample04, true);
        // When
        warehouseExample04ScaledUp.moveRobot();
        // Then
        final List<String> linesExample04ScaledUpResult = PuzzleInputParser.parseToString(example04ScaledUpResultResource);
        final Warehouse warehouseExample04ScaledUpResult = new Warehouse(linesExample04ScaledUpResult, false);
        assertEquals(warehouseExample04ScaledUpResult.render(), warehouseExample04ScaledUp.render());

        // Given
        final Warehouse warehouseExample01ScaledUp = new Warehouse(linesExample01, true);
        // When
        warehouseExample01ScaledUp.moveRobot();
        // Then
        final List<String> linesExample01ScaledUpResult = PuzzleInputParser.parseToString(example01ScaledUpResultResource);
        final Warehouse warehouseExample01ScaledUpResult = new Warehouse(linesExample01ScaledUpResult, false);
        assertEquals(warehouseExample01ScaledUpResult.render(), warehouseExample01ScaledUp.render());
    }

    @Test
    void testSumGPSCoordinates() {
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01ResultResource);
        final Warehouse warehouseExample01 = new Warehouse(linesExample01, false);
        assertEquals(10092, warehouseExample01.sumGPSCoordinates());

        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02ResultResource);
        final Warehouse warehouseExample02 = new Warehouse(linesExample02, false);
        assertEquals(2028, warehouseExample02.sumGPSCoordinates());

        final List<String> linesExample03 = PuzzleInputParser.parseToString(example03Resource);
        final Warehouse warehouseExample03 = new Warehouse(linesExample03, false);
        assertEquals(104, warehouseExample03.sumGPSCoordinates());

        // The example result of 105 on AoC is wrong in case it is scaling-up example 03.
        final Warehouse warehouseScaledUpExample03 = new Warehouse(linesExample03, true);
        assertEquals(108, warehouseScaledUpExample03.sumGPSCoordinates());
    }

    @Test
    void testScaledUpRender() {
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01Resource);
        final Warehouse warehouseExample01 = new Warehouse(linesExample01, true);
        final List<String> scaledUpExample01 = PuzzleInputParser.parseToString(example01ScaledUpResource);
        assertEquals(mapToRender(scaledUpExample01), warehouseExample01.render());

        final List<String> linesExample03 = PuzzleInputParser.parseToString(example03Resource);
        final Warehouse warehouseExample03 = new Warehouse(linesExample03, true);
        final List<String> scaledUpExample03 = PuzzleInputParser.parseToString(example03ScaledUpResource);
        assertEquals(mapToRender(scaledUpExample03), warehouseExample03.render());

        final List<String> linesExample04 = PuzzleInputParser.parseToString(example04Resource);
        final Warehouse warehouseExample04 = new Warehouse(linesExample04, true);
        final List<String> scaledUpExample04 = PuzzleInputParser.parseToString(example04ScaledUpResource);
        assertEquals(mapToRender(scaledUpExample04), warehouseExample04.render());
    }

    private String mapToRender(final List<String> lines) {
        return String.join(System.lineSeparator(), lines) + System.lineSeparator();
    }
}