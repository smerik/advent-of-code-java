package nl.smerik.adventofcode.aoc2020.model.ferry;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class DockingProgramTest {

    private static Stream<Arguments> provideSourceForWriteToMemory() {
        return Stream.of(
                // @formatter:off
                Arguments.of(8L,  11,  73),
                Arguments.of(7L, 101, 101),
                Arguments.of(8L,   0,  64)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForWriteToMemory")
    void writeToMemory(final long address, final int value, final int expectedValue) {
        final DockingProgram program = new DockingProgram();
        program.updateBitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
        program.writeToMemory(address, value);

        assertEquals(expectedValue, program.getMemory().get(address));
    }

    @Test
    void sumMemory() {
        final DockingProgram program = new DockingProgram();
        program.updateBitmask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
        program.writeToMemory(7, 101);
        program.writeToMemory(8, 0);

        assertEquals(165L, program.sumMemory());
    }

    @Test
    void run() {
        final List<String> initializationProgram = List.of(
                "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
                "mem[8] = 11",
                "mem[7] = 101",
                "mem[8] = 0"
        );

        final DockingProgram program = new DockingProgram();
        program.run(initializationProgram);

        assertEquals(165L, program.sumMemory());
    }
}