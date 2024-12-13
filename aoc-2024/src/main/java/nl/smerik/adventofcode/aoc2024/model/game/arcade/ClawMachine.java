package nl.smerik.adventofcode.aoc2024.model.game.arcade;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ClawMachine {

    private final Map<String, Integer> tokenCountByPositionAndButtonsPressCount = new HashMap<>();

    public static final int BUTTON_PRESS_LIMIT = 100;
    private final Button buttonA;
    private final Button buttonB;
    private final Point prizeLocation;

    public ClawMachine(final Button buttonA, final Button buttonB, final Point prizeLocation) {
        this.buttonA = buttonA;
        this.buttonB = buttonB;
        this.prizeLocation = prizeLocation;
    }

    /**
     * Determines the smallest amount of tokens to be spent to win a prize.
     * Returns <code>0</code> when it is not possible to win a prize.
     *
     * @return the amount of tokens to spend to win a prize; Will be <code>0</code> when no prize.
     */
    public int determineMinTokensToPossiblePrize() {
        return determineMinTokensToPossiblePrize(new Point(0, 0), 0, 0, 0);
    }

    private int determineMinTokensToPossiblePrize(final Point currentLocation, int tokenCount, int buttonAPressCount, int buttonBPressCount) {
        if (currentLocation.equals(prizeLocation)) {
            return tokenCount;
        }
        if (buttonAPressCount > BUTTON_PRESS_LIMIT || buttonBPressCount > BUTTON_PRESS_LIMIT) {
            return 0;
        }
        if (currentLocation.x > prizeLocation.x || currentLocation.y > prizeLocation.y) {
            return 0;
        }

        final String key = currentLocation + "," + buttonAPressCount + "," + buttonBPressCount;
        if (tokenCountByPositionAndButtonsPressCount.containsKey(key)) {
            return tokenCountByPositionAndButtonsPressCount.get(key);
        }

        final Point newLocationA = new Point(currentLocation);
        newLocationA.translate(buttonA.dx(), buttonA.dy());
        final int resultA = determineMinTokensToPossiblePrize(newLocationA, tokenCount + buttonA.tokenCount(), buttonAPressCount + 1, buttonBPressCount);

        final Point newLocationB = new Point(currentLocation);
        newLocationB.translate(buttonB.dx(), buttonB.dy());
        final int resultB = determineMinTokensToPossiblePrize(newLocationB, tokenCount + buttonB.tokenCount(), buttonAPressCount, buttonBPressCount + 1);

        // Get the minimum tokenCount spent
        final int result = resultA > 0 && resultB > 0 ? Math.min(resultA, resultB) : Math.max(resultA, resultB);
        tokenCountByPositionAndButtonsPressCount.put(key, result);
        return result;
    }
}
