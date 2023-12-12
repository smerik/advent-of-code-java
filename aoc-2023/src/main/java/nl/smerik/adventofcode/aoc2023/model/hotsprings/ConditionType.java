package nl.smerik.adventofcode.aoc2023.model.hotsprings;

public enum ConditionType {

    OPERATIONAL('.'),
    DAMAGED('#'),
    UNKNOWN('?');

    private final char conditionToken;

    ConditionType(final char conditionToken) {
        this.conditionToken = conditionToken;
    }

    public static ConditionType valueOfConditionToken(final char conditionToken) {
        for (final ConditionType type : values()) {
            if (type.conditionToken == conditionToken) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown token '" + conditionToken + "'");
    }
}
