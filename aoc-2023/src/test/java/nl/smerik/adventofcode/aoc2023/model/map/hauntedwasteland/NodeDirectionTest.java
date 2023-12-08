package nl.smerik.adventofcode.aoc2023.model.map.hauntedwasteland;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeDirectionTest {

    @ParameterizedTest
    @CsvSource({
            "LEFT , L",
            "RIGHT, R"
    })
    void testValueByInstruction(final NodeDirection expectedDirection, final char instruction) {
        assertEquals(expectedDirection, NodeDirection.valueByInstruction(instruction));
    }
}