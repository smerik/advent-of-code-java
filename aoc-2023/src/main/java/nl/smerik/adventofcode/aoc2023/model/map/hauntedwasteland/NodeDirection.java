package nl.smerik.adventofcode.aoc2023.model.map.hauntedwasteland;

public enum NodeDirection {

    LEFT('L'),
    RIGHT('R');

    private final char instruction;

    NodeDirection(final char instruction) {
        this.instruction = instruction;
    }

    public static NodeDirection valueByInstruction(final char instruction) {
        for (final NodeDirection direction : values()) {
            if (direction.instruction == instruction) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Unknown instruction '" + instruction + "'");
    }
}
