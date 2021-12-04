package nl.smerik.adventofcode.aoc2021.model.game.bingo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BingoCard {

    private static final int GRID_SIZE = 5;

    @EqualsAndHashCode.Include
    private final int[][] cardNumbers;
    private final List<Integer> markedNumbers;

    public BingoCard(final List<String> cardNumbers) {
        this.cardNumbers = parseCardNumbers(cardNumbers);
        this.markedNumbers = new ArrayList<>();
    }

    private int[][] parseCardNumbers(final List<String> numbers) {
        final int[][] result = new int[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            final String[] stringNumbers = numbers.get(i).trim().replaceAll("\\s\\s+", " ").split(" ");
            result[i] = Arrays.stream(stringNumbers).mapToInt(Integer::valueOf).toArray();
        }
        return result;
    }

    /**
     * Marks given number on the bingo card when it is present.
     * <p>
     * In case the number will complete the card <code>true</code> will be returned; <code>false</code> otherwise
     *
     * @param number the number to mark
     * @return <code>true</code> if card is completed; <code>false</code> otherwise
     */
    public boolean markNumber(final int number) {
        for (final int[] cardNumber : cardNumbers) {
            for (int i = 0; i < GRID_SIZE; i++) {
                if (cardNumber[i] == number) {
                    markedNumbers.add(number);
                    if (isCompleted()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if this bingo card is completed,
     * meaning that it contains at least one complete row or column of marked numbers.
     *
     * @return <code>true</code> if completed; <code>false</code> otherwise
     */
    public boolean isCompleted() {
        return hasCompletedRow() || hasCompletedColumn();
    }

    private boolean hasCompletedRow() {
        for (final int[] cardNumber : cardNumbers) {
            final Set<Integer> numbers = IntStream.of(cardNumber).boxed().collect(Collectors.toSet());
            if (hasCompletedPattern(numbers)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCompletedColumn() {
        for (int x = 0; x < cardNumbers[0].length; x++) {
            final Set<Integer> column = new HashSet<>();
            for (final int[] cardNumber : cardNumbers) {
                column.add(cardNumber[x]);
            }
            if (hasCompletedPattern(column)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCompletedPattern(final Set<Integer> pattern) {
        return markedNumbers.containsAll(pattern);
    }

    public int calculateScore() {
        final int sumOfUnmarkedNumbers = sumAllUnmarkedNumbers();
        final int lastCalledNumber = markedNumbers.get(markedNumbers.size() - 1);
        return sumOfUnmarkedNumbers * lastCalledNumber;
    }

    private int sumAllUnmarkedNumbers() {
        return Arrays.stream(cardNumbers)
                     .flatMapToInt(Arrays::stream)
                     .filter(number -> !markedNumbers.contains(number))
                     .sum();
    }
}
