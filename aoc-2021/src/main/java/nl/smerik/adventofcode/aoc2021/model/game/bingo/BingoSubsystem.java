package nl.smerik.adventofcode.aoc2021.model.game.bingo;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class BingoSubsystem {

    private final Queue<Integer> randomNumbers;
    private final Set<BingoCard> bingoCards;

    public BingoSubsystem(final List<String> input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Bingo input is empty");
        }
        randomNumbers = parseRandomNumbers(input.get(0));
        bingoCards = parseBingoCards(input);
    }

    private Queue<Integer> parseRandomNumbers(final String s) {
        return Arrays.stream(s.split(","))
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private Set<BingoCard> parseBingoCards(final List<String> input) {
        final Set<BingoCard> result = new HashSet<>();
        List<String> cardNumbers = new ArrayList<>();
        for (int i = 1; i < input.size(); i++) {
            final String line = input.get(i);
            if (line.isEmpty()) {
                continue;
            }
            cardNumbers.add(line);

            if (i % 6 == 0) {
                result.add(new BingoCard(cardNumbers));
                cardNumbers = new ArrayList<>();
            }
        }
        return result;
    }

    /**
     * Draws the random numbers until one of the card is completed.
     * The completed card will be returned.
     *
     * @return the card that is completed
     * @throws IllegalStateException when all random numbers have been drawn but no card is completed
     */
    public BingoCard playUntilBingo() {
        while (!randomNumbers.isEmpty()) {
            final Integer drawnNumber = randomNumbers.poll();
            for (final BingoCard bingoCard : bingoCards) {
                if (bingoCard.markNumber(drawnNumber)) {
                    return bingoCard;
                }
            }
        }
        throw new IllegalStateException("No completed card!");
    }
}
