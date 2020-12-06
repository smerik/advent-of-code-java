package nl.smerik.adventofcode.aoc2019.model.vacuumrobot;

import lombok.Getter;

@Getter
public enum AsciiType {

    NEW_LINE(10),
    SCAFFOLD('#'),
    OPEN_SPACE('.'),
    VACUUM_ROBOT_FACING_UP('^'),
    VACUUM_ROBOT_FACING_DOWN('v'),
    VACUUM_ROBOT_FACING_LEFT('<'),
    VACUUM_ROBOT_FACING_RIGHT('>'),
    VACUUM_ROBOT_TUMBLING('X');

    private final int code;

    AsciiType(final int code) {
        this.code = code;
    }

    public static AsciiType valueOfLabel(final int code) {
        for (AsciiType ascii : values()) {
            if (ascii.code == code) {
                return ascii;
            }
        }
        throw new IllegalArgumentException("Unknown ASCII code " + code);
    }
}
