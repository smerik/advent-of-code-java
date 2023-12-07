package nl.smerik.adventofcode.aoc2023.model.game.camelcard;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CamelCardsGame {

    private final Map<Hand, Integer> bidsByHand;

    public CamelCardsGame(final List<String> lines) {
        bidsByHand = parseLines(lines);
    }

    private Map<Hand, Integer> parseLines(List<String> lines) {
        return lines.stream().map(Hand::new).collect(Collectors.toMap(Function.identity(), Hand::getBid));
    }

    public int determineTotalWinnings() {
        final List<Hand> hands = sortHandsInOrderOfStrength();
        int result = 0;
        int rank = 0;
        for (final Hand hand : hands) {
            rank++;
            result += rank * bidsByHand.get(hand);
        }
        return result;
    }

    public List<Hand> sortHandsInOrderOfStrength() {
        return bidsByHand.keySet().stream().sorted(Comparator.reverseOrder()).toList();
    }
}
