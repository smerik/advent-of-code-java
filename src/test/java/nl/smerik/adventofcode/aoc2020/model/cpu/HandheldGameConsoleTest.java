package nl.smerik.adventofcode.aoc2020.model.cpu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

class HandheldGameConsoleTest {

    @Test
    void run() {
        final List<String> instructions = List.of(
                "nop +0",
                "acc +1",
                "jmp +4",
                "acc +3",
                "jmp -3",
                "acc -99",
                "acc +1",
                "jmp -4",
                "acc +6"
        );
        final HandheldGameConsole console = new HandheldGameConsole(instructions);

        console.run();

        assertEquals(5, console.getAccumulator());
    }
}