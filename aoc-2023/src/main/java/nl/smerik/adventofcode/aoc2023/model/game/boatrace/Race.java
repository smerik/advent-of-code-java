package nl.smerik.adventofcode.aoc2023.model.game.boatrace;

import java.util.HashMap;
import java.util.Map;

public class Race {

    private final long raceDuration;
    private final long recordDistance;

    public Race(final long raceDuration, final long recordDistance) {
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
    public Map<Long, Long> determineWaysToWinRace() {
        final Map<Long, Long> result = new HashMap<>();
        for (long holdButtonDuration = 1; holdButtonDuration < raceDuration; holdButtonDuration++) {
            long timeLeft = raceDuration - holdButtonDuration;

            final long distance = calcDistance(holdButtonDuration, timeLeft);
            if (distance > recordDistance) {
                result.put(holdButtonDuration, distance);
            }
            if (timeLeft == 1) {
                break;
            }
        }
        return result;
    }

    private long calcDistance(final long holdButtonDuration, final long timeLeft) {
        return holdButtonDuration * timeLeft;
    }
}
