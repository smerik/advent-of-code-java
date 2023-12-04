package nl.smerik.adventofcode.aoc2023.model.game.scratchcard;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
public class ScratchCardGame {

    // TODO: rewrite pattern
//    private static final Pattern CARD_PATTERN = Pattern.compile("Card (?<cardNumber>\\d+): (?<winningNumbers>((\\d+\\s))+)\\| (?<numbers>((\\d+\\s?))+)");
    private static final Pattern CARD_PATTERN = Pattern.compile("Card +(?<cardNumber>\\d+)");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private Integer cardNumber;
    private Set<Integer> winningNumbers;
    private Set<Integer> numbers;


    public ScratchCardGame(final String line) {
        parseLine(line);
    }

    private void parseLine(final String line) {
        String[] game = line.split(":");

        final Matcher matcher = CARD_PATTERN.matcher(game[0]);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Unknown card pattern: '" + line + "'");
        }
        cardNumber = Integer.valueOf(matcher.group("cardNumber"));
//        winningNumbers = parseNumbers(matcher.group("winningNumbers"));
//        numbers = parseNumbers(matcher.group("numbers"));
        String[] numberLists = game[1].split("\\|");
        winningNumbers = parseNumbers(numberLists[0]);
        numbers = parseNumbers(numberLists[1]);
    }

    private Set<Integer> parseNumbers(final String numberList) {
        final Set<Integer> result = new HashSet<>();
        final Matcher matcher = NUMBER_PATTERN.matcher(numberList);
        while (matcher.find()) {
            result.add(Integer.valueOf(matcher.group()));
        }
        return result;
    }

    public int determinePoints() {
        // TODO: improve me
        final Set<Integer> foundNumbers = findWinningNumbers();
        if (foundNumbers.isEmpty()) {
            return 0;
        }
        int result = 1;
        for (int i = 1; i < foundNumbers.size(); i++) {
            result *= 2;
        }
        return result;
    }

    public Set<Integer> findWinningNumbers() {
        return numbers.stream().filter(number -> winningNumbers.contains(number)).collect(Collectors.toSet());
    }
}
