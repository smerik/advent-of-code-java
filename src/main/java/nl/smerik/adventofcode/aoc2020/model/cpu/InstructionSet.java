package nl.smerik.adventofcode.aoc2020.model.cpu;

public enum InstructionSet {

    /**
     * Increases or decreases a single global value called the accumulator by the value given in the argument.
     **/
    ACCUMULATOR("acc"),

    /**
     * Jumps to a new instruction relative to itself.
     * The next instruction to execute is found using the argument as an offset from the <code>jmp</code> instruction.
     */
    JUMP("jmp"),

    /**
     * No OPeration - it does nothing.
     * The instruction immediately below it is executed next.
     */
    NO_OPERATION("nop");

    private final String operation;

    InstructionSet(final String operation) {
        this.operation = operation;
    }

    public static InstructionSet valueOfOperation(final String operation) {
        for (final InstructionSet instructionSet : values()) {
            if (instructionSet.operation.equals(operation)) {
                return instructionSet;
            }
        }
        throw new IllegalArgumentException("Unknown operation '" + operation + "'");
    }
}
