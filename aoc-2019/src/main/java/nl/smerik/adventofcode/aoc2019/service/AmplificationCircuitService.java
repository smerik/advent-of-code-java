package nl.smerik.adventofcode.aoc2019.service;

import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2019.model.AmplificationCircuit;
import org.apache.commons.collections4.iterators.PermutationIterator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class AmplificationCircuitService {

    public long determineLargestOutputSignal(final long[] program, final List<Integer> phases) {
        final List<Long> thrusterSignals = new ArrayList<>();
        final PermutationIterator<Integer> integerPermutationIterator = new PermutationIterator<>(phases);
        integerPermutationIterator.forEachRemaining(phaseSequence -> thrusterSignals.add(amplify(program, phaseSequence)));
        return thrusterSignals.stream().max(Long::compareTo).orElseThrow();
    }

    private long amplify(final long[] program, final List<Integer> phaseSequence) {
        LOG.debug("Run phase sequence {}...", phaseSequence);
        final AmplificationCircuit circuit = new AmplificationCircuit(program, phaseSequence);
        final List<Long> result = circuit.run(Collections.singletonList(0L));
        LOG.debug("Run result: {}", result.get(0));
        return result.get(0);
    }
}
