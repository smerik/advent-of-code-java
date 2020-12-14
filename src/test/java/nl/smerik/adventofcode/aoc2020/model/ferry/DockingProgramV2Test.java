package nl.smerik.adventofcode.aoc2020.model.ferry;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DockingProgramV2Test {

    @Test
    void writeToMemory() {
        final DockingProgramV2 program = new DockingProgramV2();

        program.updateBitmask("000000000000000000000000000000X1001X");
        program.writeToMemory(42L, 100);
        assertNull(program.getMemory().get(42L));
        assertEquals(100L, program.getMemory().get(26L));
        assertEquals(100L, program.getMemory().get(27L));
        assertEquals(100L, program.getMemory().get(58L));
        assertEquals(100L, program.getMemory().get(59L));
        assertEquals(4, program.getMemory().size());

        program.updateBitmask("00000000000000000000000000000000X0XX");
        program.writeToMemory(26L, 1);
        assertEquals(1L, program.getMemory().get(16L));
        assertEquals(1L, program.getMemory().get(17L));
        assertEquals(1L, program.getMemory().get(18L));
        assertEquals(1L, program.getMemory().get(19L));
        assertEquals(1L, program.getMemory().get(24L));
        assertEquals(1L, program.getMemory().get(25L));
        assertEquals(1L, program.getMemory().get(26L));
        assertEquals(1L, program.getMemory().get(27L));
        // remainder of "program.writeToMemory(42, 100)"
        assertEquals(100L, program.getMemory().get(58L));
        assertEquals(100L, program.getMemory().get(59L));
        assertEquals(10, program.getMemory().size());
    }

    @Test
    void run() {
        final List<String> initializationProgram = List.of(
                "mask = 000000000000000000000000000000X1001X",
                "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX",
                "mem[26] = 1"
        );

        final DockingProgramV2 program = new DockingProgramV2();
        program.run(initializationProgram);

        assertEquals(208L, program.sumMemory());
    }
}