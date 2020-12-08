package nl.smerik.adventofcode.aoc2020.service.cpu;

import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.cpu.Processor;
import org.springframework.stereotype.Service;

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
}
