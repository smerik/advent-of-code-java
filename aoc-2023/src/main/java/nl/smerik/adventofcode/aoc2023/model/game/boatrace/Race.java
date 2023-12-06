package nl.smerik.adventofcode.aoc2023.model.game.boatrace;

import java.util.HashMap;
import java.util.Map;

public class Race {

    private final int raceDuration;
    private final int recordDistance;

    public Race(final int raceDuration, final int recordDistance) {
        this.raceDuration = raceDuration;
        this.recordDistance = recordDistance;
    }

    public int determineNumberOfWaysToWinRace() {
        return determineWaysToWinRace().size();
    }

    /**
     * Determines the best distances to win the race mapped by the button press duration.
     *
     * @return distances mapped by button press duration
     */
    public Map<Integer, Integer> determineWaysToWinRace() {
        final Map<Integer, Integer> result = new HashMap<>();
        for (int holdButtonDuration = 1; holdButtonDuration < raceDuration; holdButtonDuration++) {
            int timeLeft = raceDuration - holdButtonDuration;

            final int distance = calcDistance(holdButtonDuration, timeLeft);
            if (distance > recordDistance) {
                result.put(holdButtonDuration, distance);
            }
            if (timeLeft == 1) {
                break;
            }
        }
        return result;
    }

    private int calcDistance(final int holdButtonDuration, final int timeLeft) {
        return holdButtonDuration * timeLeft;
    }
}
