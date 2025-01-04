package nl.smerik.adventofcode.aoc2024.model.computer.chronospatial;

public enum Instruction {

    /**
     * Performs division.
     * The numerator is the value in the <code>A</code> register.
     * The denominator is found by raising 2 to the power of the instruction's combo operand.
     * (So, an operand of <code>2</code> would divide <code>A</code> by <code>4</code> (<code>2^2</code>);
     * an operand of <code>5</code> would divide <code>A</code> by <code>2^B</code>.)
     * The result of the division operation is truncated to an integer and then written to the <code>A</code> register.
     */
    ADV(0),

    /**
     * Calculates the bitwise XOR of register <code>B</code> and the instruction's literal operand,
     * then stores the result in register <code>B</code>.
     */
    BXL(1),

    /**
     * Calculates the value of its combo operand modulo <code>8</code> (thereby keeping only its lowest 3 bits),
     * then writes that value to the <code>B</code> register.
     */
    BST(2),

    /**
     * Does nothing if the <code>A</code>> register is <code>0</code>.
     * However, if the <code>A</code> register is not zero,
     * it jumps by setting the instruction pointer to the value of its literal operand;
     * if this instruction jumps, the instruction pointer is not increased by <code>2</code> after this instruction.
     */
    JNZ(3),

    /**
     * Calculates the bitwise XOR of register <code>B</code> and register <code>C</code>,
     * then stores the result in register <code>B</code>.
     * (For legacy reasons, this instruction reads an operand but ignores it.)
     */
    BXC(4),

    /**
     * Calculates the value of its combo operand modulo <code>8</code>, then outputs that value.
     * (If a program outputs multiple values, they are separated by commas.)
     */
    OUT(5),

    /**
     * Works exactly like the {@link Instruction#ADV} instruction
     * except that the result is stored in the <code>B</code> register.
     * (The numerator is still read from the <code>A</code> register.)
     */
    BDV(6),

    /**
     * Works exactly like the {@link Instruction#ADV} instruction
     * except that the result is stored in the <code>C</code> register.
     * (The numerator is still read from the <code>A</code> register.)
     */
    CDV(7);

    private final int opcode;

    Instruction(final int opcode) {
        this.opcode = opcode;
    }

    public static Instruction valueOfOpcode(final int opcode) {
        for (final Instruction instruction : values()) {
            if (instruction.opcode == opcode) {
                return instruction;
            }
        }
        throw new IllegalArgumentException("Unknown opcode " + opcode);
    }
}
