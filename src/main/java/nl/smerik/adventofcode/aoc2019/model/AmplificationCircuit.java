package nl.smerik.adventofcode.aoc2019.model;

import java.util.List;
import java.util.stream.Collectors;

public class AmplificationCircuit {

    final List<IntcodeComputer> amplifiers;

    public AmplificationCircuit(final int[] program, final List<Integer> phaseSequence) {
        this.amplifiers = phaseSequence.stream()
                .map(phase -> new IntcodeComputer(program, phase))
                .collect(Collectors.toList());
    }

    public int run(final int input) {
        int signal = input;
        for (final IntcodeComputer computer : amplifiers) {
            signal = computer.run(signal);
        }
        if (amplifiers.get(amplifiers.size() - 1).isPausedExecution()) {
            return run(signal);
        }
        return signal;
    }
}
