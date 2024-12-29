package nl.smerik.adventofcode.aoc2024.model.game.arcade;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arcade {

    private static final double CONVERSION_FIX_AMOUNT = 10000000000000d;
    private static final Pattern BUTTON_PATTERN = Pattern.compile("^Button [AB]: X\\+(?<dx>\\d+), Y\\+(?<dy>\\d+)$");
    private static final Pattern PRIZE_PATTERN = Pattern.compile("^Prize: X=(?<x>\\d+), Y=(?<y>\\d+)$");

    private final Set<ClawMachine> machines;

    public Arcade(final List<String> lines, final boolean fixUnitConversionError) {
        this.machines = parseLines(lines, fixUnitConversionError);
    }

    private Set<ClawMachine> parseLines(final List<String> lines, final boolean fixUnitConversionError) {
        final Set<ClawMachine> result = new HashSet<>();
        final Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            final Button buttonA = parseButton(iterator.next(), 3);
            final Button buttonB = parseButton(iterator.next(), 1);
            final Point2D.Double prizeLocation = parsePrizeLocation(iterator.next(), fixUnitConversionError);
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

    private Point2D.Double parsePrizeLocation(final String line, final boolean fixUnitConversionError) {
        final Matcher matcher = PRIZE_PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid prize line: " + line);
        }
        double x = Double.parseDouble(matcher.group("x"));
        double y = Double.parseDouble(matcher.group("y"));
        if (fixUnitConversionError) {
            x += CONVERSION_FIX_AMOUNT;
            y += CONVERSION_FIX_AMOUNT;
        }
        return new Point2D.Double(x, y);
    }

    public long determineMinTokensToWinAllPossiblePrizes() {
        return machines.stream().map(ClawMachine::determineMinTokensToPossiblePrize).mapToLong(Long::longValue).sum();
    }
}
