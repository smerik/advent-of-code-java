package nl.smerik.adventofcode.aoc2019.service;

import nl.smerik.adventofcode.aoc2019.model.AmplificationCircuit;
import org.apache.commons.collections4.iterators.PermutationIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AmplificationCircuitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmplificationCircuitService.class);

    public long determineLargestOutputSignal(final long[] program, final List<Integer> phases) {
        final List<Long> thrusterSignals = new ArrayList<>();
        final PermutationIterator<Integer> integerPermutationIterator = new PermutationIterator<>(phases);
        integerPermutationIterator.forEachRemaining(phaseSequence -> thrusterSignals.add(amplify(program, phaseSequence)));
        return thrusterSignals.stream().max(Long::compareTo).orElseThrow();
    }

    private long amplify(final long[] program, final List<Integer> phaseSequence) {
        LOGGER.debug("Run phase sequence {}...", phaseSequence);
        final AmplificationCircuit circuit = new AmplificationCircuit(program, phaseSequence);
        final List<Long> result = circuit.run(Collections.singletonList(0L));
        LOGGER.debug("Run result: {}", result.get(0));
        return result.get(0);
    }
}
