package nl.smerik.adventofcode.aoc2020.model.cups;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Implements Day 23: Crab Cups.
 * <p>
 * The implementation needed to be refactored for Part 2.
 * The following resource was used as a base for refactoring:
 * <a href="https://timvisee.com/blog/solving-aoc-2020-in-under-a-second/#day-23-crab-cups">Solving Advent of Code 2020 in under a second</a>.
 */
@Slf4j
@Getter
public class CrabCups {

    private static final int INITIAL_CUP_LABEL = 1; // The initial cup label to start after for cup labeling
    private static final int CUPS_COUNT_TO_PICK_UP = 3;

    private final Map<Integer, Integer> nextCupByCupLabel;
    private final int minCupLabel;
    private final int maxCupLabel;

    private int currentCupLabel;
    private int moveCount;

    public CrabCups(final String cupLabeling, final int totalCupsToArrange) {
        final List<Integer> initialCups = splitCupLabeling(cupLabeling);
        this.nextCupByCupLabel = arrangeCups(initialCups, totalCupsToArrange);
        this.minCupLabel = Collections.min(this.nextCupByCupLabel.keySet());
        this.maxCupLabel = Collections.max(this.nextCupByCupLabel.keySet());
        this.currentCupLabel = initialCups.get(0);
        this.moveCount = 0;
    }

    private Map<Integer, Integer> arrangeCups(final List<Integer> cupLabeling, final int totalCupsToArrange) {
        final Map<Integer, Integer> result = new HashMap<>();

        final List<Integer> allCups = new ArrayList<>(cupLabeling);
        // Arrange additional cups which is introduced at Part 2
        allCups.addAll(IntStream.rangeClosed(allCups.size() + 1, totalCupsToArrange).boxed().toList());

        int nextCup = allCups.get(0); // the last cup needs to be linked to the first cup so the first cup is next
        Collections.reverse(allCups); // reverse the list first, so the next cup can be easily linked within a for loop
        for (final Integer cup : allCups) {
            result.put(cup, nextCup);
            nextCup = cup;
        }
        return result;
    }

    /**
     * Splits up given cup labeling into a list of digits, a.k.a. the cup labels.
     *
     * @param cupLabeling the cup labeling
     * @return the list of cup labels
     */
    private List<Integer> splitCupLabeling(final String cupLabeling) {
        final List<Integer> result = new LinkedList<>();
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
        LOG.trace("-- final --");
        logCups();
    }

    /**
     * Simulates 1 move in a game of Crab Cups.
     */
    private void simulate() {
        moveCount++;
        LOG.trace("-- move {} --", moveCount);
        logCups();

        final List<Integer> cupsToPickup = findCupsToPickUp();
        // Get the cup immediately clockwise of the last picked up cup
        final int nextCup = this.nextCupByCupLabel.get(cupsToPickup.get(cupsToPickup.size() - 1));
        // Link the cup next to the current cup to maintain the circle
        this.nextCupByCupLabel.put(this.currentCupLabel, nextCup);

        // The crab places the cups it just picked up so that they are immediately clockwise of the destination cup.
        // First determine the destination of the picked up cups
        final int destinationCupLabel = determineDestinationCupLabel(cupsToPickup);
        // Then update the next cup of the last picked up cup to the one next to the destination cup
        this.nextCupByCupLabel.put(cupsToPickup.get(cupsToPickup.size() - 1), this.nextCupByCupLabel.get(destinationCupLabel));
        // Then update the next cup of the destination cup to be the first picked up cup
        this.nextCupByCupLabel.put(destinationCupLabel, cupsToPickup.get(0));

        // The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
        this.currentCupLabel = nextCup;
    }

    /**
     * The crab picks up the three cups that are immediately clockwise of the current cup.
     * They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
     *
     * @return the picked up cups
     */
    private List<Integer> findCupsToPickUp() {
        final List<Integer> result = new ArrayList<>(CUPS_COUNT_TO_PICK_UP);
        int cup = this.currentCupLabel;
        for (int i = 0; i < CUPS_COUNT_TO_PICK_UP; i++) {
            cup = this.nextCupByCupLabel.get(cup);
            result.add(cup);
        }
        LOG.trace("pick up: {}", result);
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
        LOG.trace("destination: {}", cupLabel); // this logs the destination cup (and so NOT the destination index!)
        return cupLabel;
    }

    /**
     * Gives the cups' labels starting after the cup labeled <code>{@value INITIAL_CUP_LABEL}</code>.
     *
     * @return the cups' labels after <code>{@value INITIAL_CUP_LABEL}</code>
     */
    public String getCupLabeling() {
        final StringBuilder builder = new StringBuilder();
        int cupLabel = INITIAL_CUP_LABEL;
        while ((cupLabel = this.getNextCupByCupLabel().get(cupLabel)) != INITIAL_CUP_LABEL) {
            builder.append(cupLabel);
        }
        return builder.toString();
    }

    /**
     * The crab is going to hide your stars - one each -
     * under the two cups that will end up immediately clockwise of cup 1.
     *
     * @return the cup labels where the stars are hidden
     */
    public List<Integer> findCupsWithStars() {
        final int cupWith1stStar = this.nextCupByCupLabel.get(1);
        final int cupWith2ndStar = this.nextCupByCupLabel.get(cupWith1stStar);
        return List.of(cupWith1stStar, cupWith2ndStar);
    }

    /**
     * Multiplies the cups values containing stars.
     *
     * @return the multiply result
     */
    public long multiplyCupsWithStars() {
        return findCupsWithStars().stream().mapToLong(Long::valueOf).reduce(1, (a, b) -> a * b);
    }

    private void logCups() {
        LOG.trace("cups: ({})", this.currentCupLabel);
    }
}
