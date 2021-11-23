package nl.smerik.adventofcode.aoc2019.model;

import java.util.List;
import java.util.stream.Collectors;

public class AmplificationCircuit {

    final List<IntcodeComputer> amplifiers;

    public AmplificationCircuit(final long[] program, final List<Integer> phaseSequence) {
        this.amplifiers = phaseSequence.stream()
                .map(phase -> new IntcodeComputer(program, phase))
                .collect(Collectors.toList());
    }

    public List<Long> run(final List<Long> input) {
        List<Long> signal = input;
        for (final IntcodeComputer computer : amplifiers) {
            signal = computer.run(signal);
        }
        if (amplifiers.get(amplifiers.size() - 1).isPausedExecution()) {
            return run(signal);
        }
        return signal;
    }
}
