package nl.smerik.adventofcode.aoc2024.model.game.arcade;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arcade {

    private static final Pattern BUTTON_PATTERN = Pattern.compile("^Button [AB]: X\\+(?<dx>\\d+), Y\\+(?<dy>\\d+)$");
    private static final Pattern PRIZE_PATTERN = Pattern.compile("^Prize: X=(?<x>\\d+), Y=(?<y>\\d+)$");

    private final Set<ClawMachine> machines;

    public Arcade(final List<String> lines) {
        this.machines = parseLines(lines);
    }

    private Set<ClawMachine> parseLines(final List<String> lines) {
        final Set<ClawMachine> result = new HashSet<>();
        final Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            final Button buttonA = parseButton(iterator.next(), 3);
            final Button buttonB = parseButton(iterator.next(), 1);
            final Point prizeLocation = parsePrizeLocation(iterator.next());
            result.add(new ClawMachine(buttonA, buttonB, prizeLocation));
            // hack for last line
            if (iterator.hasNext()) {
                iterator.next();
            }
        }
        return result;
    }

    private Button parseButton(final String line, final int tokenCost) {
        final Matcher matcher = BUTTON_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid button line: " + line);
        }
        final int dx = Integer.parseInt(matcher.group("dx"));
        final int dy = Integer.parseInt(matcher.group("dy"));
        return new Button(dx, dy, tokenCost);
    }

    private Point parsePrizeLocation(final String line) {
        final Matcher matcher = PRIZE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid prize line: " + line);
        }
        final int x = Integer.parseInt(matcher.group("x"));
        final int y = Integer.parseInt(matcher.group("y"));
        return new Point(x, y);
    }

    public int determineMinTokensToWinAllPossiblePrizes() {
        return machines.stream().map(ClawMachine::determineMinTokensToPossiblePrize).mapToInt(Integer::intValue).sum();
    }
}
