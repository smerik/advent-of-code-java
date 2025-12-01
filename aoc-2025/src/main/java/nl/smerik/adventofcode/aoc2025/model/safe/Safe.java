package nl.smerik.adventofcode.aoc2025.model.safe;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Safe {

    private static final Pattern ROTATION_PATTERN = Pattern.compile("^(?<rotation>[LR])(?<distance>\\d+)$");

    private final List<Rotation> rotations;
    private final Dial dial;
    private int pointsAtZeroCount;

    public Safe(final List<String> lines) {
        this.rotations = lines.stream().map(this::parseLine).toList();
        this.dial = new Dial(50);
        this.pointsAtZeroCount = 0;
    }

    private Rotation parseLine(final String line) {
        final Matcher matcher = ROTATION_PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Unknown rotation line '" + line + "'");
        }
        final Direction direction = Direction.valueOf(matcher.group("rotation"));
        final int distance = Integer.parseInt(matcher.group("distance"));
        return new Rotation(direction, distance);
    }

    public int determinePassword() {
        for (Rotation rotation : rotations) {
            int pointsAt = dial.rotate(rotation);
            if (pointsAt == 0) {
                pointsAtZeroCount++;
            }
        }
        return pointsAtZeroCount;
    }
}
