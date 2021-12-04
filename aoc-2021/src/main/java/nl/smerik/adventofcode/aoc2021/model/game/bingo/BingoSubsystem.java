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
     * Draws the random numbers until one of the cards is completed.
     * The completed card will be returned.
     *
     * @return the first winning card
     * @throws IllegalStateException when there is no completed card
     */
    public BingoCard playUntilBingo() {
        return play(true).get(0);
    }

    /**
     * Draws the random numbers until all cards are completed.
     *
     * @return the last winning card
     * @throws IllegalStateException when there is no completed card
     */
    public BingoCard findLastCompletedCard() {
        final List<BingoCard> completedCards = play(false);
        return completedCards.get(completedCards.size() - 1);
    }

    private List<BingoCard> play(final boolean stopAtFirstBingo) {
        final List<BingoCard> completedCards = new ArrayList<>();
        final Set<BingoCard> cardsToMark = new HashSet<>(bingoCards);
        while (!randomNumbers.isEmpty() && !cardsToMark.isEmpty()) {
            final int drawnNumber = randomNumbers.poll();
            for (final BingoCard bingoCard : cardsToMark) {
                if (bingoCard.markNumber(drawnNumber)) {
                    if (stopAtFirstBingo) {
                        return Collections.singletonList(bingoCard);
                    }
                    completedCards.add(bingoCard);
                }
            }
            completedCards.forEach(cardsToMark::remove);
        }
        if (completedCards.isEmpty()) {
            throw new IllegalStateException("No completed card!");
        }
        return completedCards;
    }
}
