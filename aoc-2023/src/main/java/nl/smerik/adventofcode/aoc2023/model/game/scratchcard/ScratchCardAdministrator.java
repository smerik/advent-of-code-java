package nl.smerik.adventofcode.aoc2023.model.game.scratchcard;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScratchCardAdministrator {

    private final Map<Integer, ScratchCardGame> gamesByCardNumber;
    private final Map<Integer, Integer> cardCountByCardNumber;

    public ScratchCardAdministrator(final List<String> lines) {
        gamesByCardNumber = parseLines(lines);
        cardCountByCardNumber = mapCardCountByCardNumber(gamesByCardNumber);
        processWinningCards();
    }

    private Map<Integer, ScratchCardGame> parseLines(List<String> lines) {
        return lines.stream().map(ScratchCardGame::new).collect(Collectors.toMap(ScratchCardGame::getCardNumber, Function.identity()));
    }

    private Map<Integer, Integer> mapCardCountByCardNumber(Map<Integer, ScratchCardGame> gamesByCardNumber) {
        return gamesByCardNumber.keySet().stream().collect(Collectors.toMap(key -> key, value -> 1));
    }

    private void processWinningCards() {
        for (final Map.Entry<Integer, ScratchCardGame> gameEntry : gamesByCardNumber.entrySet()) {
            final int cardCount = cardCountByCardNumber.get(gameEntry.getKey());
            final int copyCount = gameEntry.getValue().findWinningNumbers().size();
            for (int i = 1; i <= copyCount; i++) {
                final int cardNumberToCopy = gameEntry.getKey() + i;
                if (!cardCountByCardNumber.containsKey(cardNumberToCopy)) {
                    // Reached end of table, so break out of copy-loop because of rule:
                    // Cards will never make you copy a card past the end of the table.
                    break;
                }
                int newCount = cardCountByCardNumber.get(cardNumberToCopy) + cardCount;
                cardCountByCardNumber.put(cardNumberToCopy, newCount);
            }
        }
    }

    public int sumPoints() {
        return gamesByCardNumber.values().stream().map(ScratchCardGame::determinePoints).mapToInt(Integer::intValue).sum();
    }

    public Integer sumCards() {
        return cardCountByCardNumber.values().stream().mapToInt(Integer::intValue).sum();
    }
}
