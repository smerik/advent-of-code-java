package nl.smerik.adventofcode.aoc2019.service;

import nl.smerik.adventofcode.aoc2019.model.AmplificationCircuit;
import org.apache.commons.collections4.iterators.PermutationIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmplificationCircuitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmplificationCircuitService.class);

    public int determineLargestOutputSignal(final int[] program, final List<Integer> phases) {
        final List<Integer> thrusterSignals = new ArrayList<>();
        final PermutationIterator<Integer> integerPermutationIterator = new PermutationIterator<>(phases);
        integerPermutationIterator.forEachRemaining(phaseSequence -> thrusterSignals.add(amplify(program, phaseSequence)));
        return thrusterSignals.stream().max(Integer::compareTo).orElseThrow();
    }

    private int amplify(final int[] program, final List<Integer> phaseSequence) {
        LOGGER.debug("Run:{}", phaseSequence);
        final AmplificationCircuit circuit = new AmplificationCircuit(program, phaseSequence);
        return circuit.run(0);
    }
}
