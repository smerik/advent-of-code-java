package nl.smerik.adventofcode.aoc2020.model.cpu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.smerik.adventofcode.aoc2020.service.cpu.ProcessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProcessorServiceTest {

    @Autowired
    private ProcessorService processorService;

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

        assertEquals(5, processorService.getAccumulatorValueOnInfiniteLoop(console));
    }
}