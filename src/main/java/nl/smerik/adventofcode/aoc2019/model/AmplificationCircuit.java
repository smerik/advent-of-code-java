package nl.smerik.adventofcode.aoc2019.model;

import java.util.List;

public class AmplificationCircuit {

    private final int[] program;
    private final List<Integer> phaseSequence;

    public AmplificationCircuit(final int[] program, final List<Integer> phaseSequence) {
        this.program = program;
        this.phaseSequence = phaseSequence;
    }

    public int runCircuit() {
        int signal = 0;
        for (final Integer phase : phaseSequence) {
            final IntcodeComputer computer = new IntcodeComputer(program, phase, signal);
            computer.run();
            signal = computer.getOutput();
        }
        return signal;
    }
}
