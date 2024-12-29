package nl.smerik.adventofcode.aoc2024.model.game.arcade;

import java.awt.geom.Point2D;

public class ClawMachine {

    private final Button buttonA;
    private final Button buttonB;
    private final Point2D.Double prizeLocation;

    public ClawMachine(final Button buttonA, final Button buttonB, final Point2D.Double prizeLocation) {
        this.buttonA = buttonA;
        this.buttonB = buttonB;
        this.prizeLocation = prizeLocation;
    }

    /**
     * Determines the smallest amount of tokens to be spent to win a prize.
     * Returns <code>0</code> when it is not possible to win a prize.
     * <p>
     * This linear algebra implementation is based on/copied from
     * <a href="https://www.youtube.com/watch?v=-5J-DAsWuJc">the solution by HyperNeutrino</a>.
     * <p>
     * Note that there is no button press limitation check,
     * since all puzzle inputs only contains either 1 solution or no solution.
     *
     * @return the amount of tokens to spend to win a prize; Will be <code>0</code> when no prize.
     */
    public long determineMinTokensToPossiblePrize() {
        double pressButtonACount = (prizeLocation.x * buttonB.dy() - prizeLocation.y * buttonB.dx()) / (buttonA.dx() * buttonB.dy() - buttonA.dy() * buttonB.dx());
        double pressButtonBCount = (prizeLocation.x - buttonA.dx() * pressButtonACount) / buttonB.dx();
        // Check if both press counts are equal to an integer
        if (pressButtonACount % 1 == 0 && pressButtonBCount % 1 == 0) {
            return (long) (pressButtonACount * buttonA.tokenCount() + pressButtonBCount * buttonB.tokenCount());
        }
        return 0;
    }
}
