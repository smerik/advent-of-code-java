package nl.smerik.adventofcode.aoc2020.service.cpu;

import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.cpu.HandheldGameConsole;
import nl.smerik.adventofcode.aoc2020.model.cpu.Processor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProcessorService {

    public Integer getAccumulatorValueOnInfiniteLoop(final Processor processor) {
        try {
            processor.run();
        } catch (IllegalStateException e) {
            LOG.error("Error during execution.", e);
        }
        return processor.getAccumulator();
    }

    public Integer getAccumulatorWhenInstructionsFixed(final List<String> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            final List<String> fixedInstructions = new ArrayList<>(instructions);
            final String operation = fixedInstructions.get(i);
            if (operation.startsWith("jmp")) {
                fixedInstructions.set(i, operation.replace("jmp", "nop"));
            } else if (operation.startsWith("nop")) {
                fixedInstructions.set(i, operation.replace("nop", "jmp"));
            } else {
                continue;
            }

            try {
                final Processor processor = new HandheldGameConsole(fixedInstructions);
                processor.run();
                return processor.getAccumulator();
            } catch (IllegalStateException e) {
                LOG.debug("Infinite loop on current instruction set: try next change...");
            }
        }
        throw new IllegalStateException("Instruction set cannot be fixed.");
    }
}
