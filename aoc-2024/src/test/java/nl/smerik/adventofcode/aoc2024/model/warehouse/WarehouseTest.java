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

    @Value("classpath:input/day-15/example-02.txt")
    private Resource example02Resource;

    @Value("classpath:input/day-15/example-02-result.txt")
    private Resource example02ResultResource;

    @Value("classpath:input/day-15/example-03.txt")
    private Resource example03Resource;

    @Test
    void testMoveRobot() {
        // Given
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01Resource);
        final Warehouse warehouseExample01 = new Warehouse(linesExample01);
        // When
        warehouseExample01.moveRobot();
        // Then
        final List<String> linesExample01Result = PuzzleInputParser.parseToString(example01ResultResource);
        final Warehouse warehouseExample01Result = new Warehouse(linesExample01Result);
        assertEquals(warehouseExample01Result.render(), warehouseExample01.render());

        // Given
        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02Resource);
        final Warehouse warehouseExample02 = new Warehouse(linesExample02);
        // When
        warehouseExample02.moveRobot();
        // Then
        final List<String> linesExample02Result = PuzzleInputParser.parseToString(example02ResultResource);
        final Warehouse warehouseExample02Result = new Warehouse(linesExample02Result);
        assertEquals(warehouseExample02Result.render(), warehouseExample02.render());
    }

    @Test
    void testSumGPSCoordinates() {
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01ResultResource);
        final Warehouse warehouseExample01 = new Warehouse(linesExample01);
        assertEquals(10092, warehouseExample01.sumGPSCoordinates());

        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02ResultResource);
        final Warehouse warehouseExample02 = new Warehouse(linesExample02);
        assertEquals(2028, warehouseExample02.sumGPSCoordinates());

        final List<String> linesExample03 = PuzzleInputParser.parseToString(example03Resource);
        final Warehouse warehouseExample03 = new Warehouse(linesExample03);
        assertEquals(104, warehouseExample03.sumGPSCoordinates());
    }
}