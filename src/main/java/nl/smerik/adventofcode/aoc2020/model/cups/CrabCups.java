package nl.smerik.adventofcode.aoc2020.model.cups;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Getter
public class CrabCups {

    private static final int CUPS_COUNT_TO_PICK_UP = 3;

    private final List<Integer> cups;
    private final int minCupLabel;
    private final int maxCupLabel;

    private int currentCupLabel;
    private int moveCount;

    public CrabCups(final String cupLabeling) {
        this.cups = splitCupLabeling(cupLabeling);
        this.minCupLabel = this.cups.stream().min(Comparator.naturalOrder()).orElseThrow();
        this.maxCupLabel = this.cups.stream().max(Comparator.naturalOrder()).orElseThrow();
        this.currentCupLabel = this.cups.get(0);
        this.moveCount = 0;
    }

    /**
     * Splits up given cup labeling into a list of digits, a.k.a. the cup labels.
     *
     * @param cupLabeling the cup labeling
     * @return the list of cup labels
     */
    private List<Integer> splitCupLabeling(final String cupLabeling) {
        final List<Integer> result = new ArrayList<>();
        for (final char ch : cupLabeling.toCharArray()) {
            result.add(Character.getNumericValue(ch));
        }
        return result;
    }

    /**
     * Simulates a game of Crap Cups for the given amount of moves.
     *
     * @param moveCount the amount of moves to simulate
     */
    public void simulate(final int moveCount) {
        for (int i = 0; i < moveCount; i++) {
            this.simulate();
        }
        LOG.debug("-- final --");
        LOG.debug("cups: {}", this.cups);
    }

    /**
     * Simulates 1 move in a game of Crab Cups.
     */
    private void simulate() {
        moveCount++;
        LOG.debug("-- move {} --", moveCount);
        LOG.debug("cups: {}", this.cups);
        final List<Integer> pickedUpCups = pickUpCups();
        final Integer destinationCup = determineDestinationCupLabel(pickedUpCups);
        this.cups.addAll(this.cups.indexOf(destinationCup) + 1, pickedUpCups);
        selectNewCurrentCupLabel();
    }

    /**
     * The crab picks up the three cups that are immediately clockwise of the current cup.
     * They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
     *
     * @return the picked up cups
     */
    private List<Integer> pickUpCups() {
        final List<Integer> result = new ArrayList<>(CUPS_COUNT_TO_PICK_UP);
        for (int i = 0; i < CUPS_COUNT_TO_PICK_UP; i++) {
            final int indexToRemove = getClockWiseIndex(this.currentCupLabel);
            result.add(this.cups.remove(indexToRemove));
        }
        LOG.debug("pick up: {}", result);
        return result;
    }

    /**
     * The crab selects a destination cup:
     * the cup with a label equal to the current cup's label minus one.
     * If this would select one of the cups that was just picked up,
     * the crab will keep subtracting one until it finds a cup that wasn't just picked up.
     * If at any point in this process the value goes below the lowest value on any cup's label,
     * it wraps around to the highest value on any cup's label instead.
     *
     * @param pickedUpCups the cups that had been picked up before
     * @return the destination cup
     */
    private int determineDestinationCupLabel(final List<Integer> pickedUpCups) {
        int cupLabel = this.currentCupLabel;
        do {
            cupLabel = cupLabel == minCupLabel ? maxCupLabel : cupLabel - 1;
        } while (pickedUpCups.contains(cupLabel));
        LOG.debug("destination: {}", cupLabel); // this logs the destination cup (and so NOT the destination index!)
        return cupLabel;
    }

    /**
     * The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
     */
    private void selectNewCurrentCupLabel() {
        this.currentCupLabel = this.cups.get(getClockWiseIndex(this.currentCupLabel));
    }

    /**
     * Gives the cups' labels starting after the cup labeled <code>1</code>.
     *
     * @return the cups' labels after 1
     */
    public String getCupLabeling() {
        final StringBuilder builder = new StringBuilder();
        int cupLabel = 1; // the initial cup label to start after
        for (int i = 0; i < cups.size() - 1; i++) {
            int cupIndex = getClockWiseIndex(cupLabel);
            cupLabel = this.cups.get(cupIndex);
            builder.append(cupLabel);
        }
        return builder.toString();
    }

    private int getClockWiseIndex(final int cupLabel) {
        int nextCupIndex = cups.indexOf(cupLabel) + 1;
        if (nextCupIndex == this.cups.size()) {
            nextCupIndex = 0;
        }
        return nextCupIndex;
    }
}
