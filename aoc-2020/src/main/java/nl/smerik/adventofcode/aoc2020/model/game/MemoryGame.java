package nl.smerik.adventofcode.aoc2020.model.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryGame {

    final Map<Integer, List<Integer>> spokenNumbers;
    int lastSpokenNumber;
    int turn;

    public MemoryGame(final int... startingNumbers) {
        spokenNumbers = new HashMap<>();
        turn = 1;
        for (int number : startingNumbers) {
            addSpokenNumber(number);
        }
    }

    public int determineNthSpokenNumber(final int n) {
        while (turn <= n) {
            speakLastSpokenNumber();
        }
        return lastSpokenNumber;
    }

    private void speakLastSpokenNumber() {
        final List<Integer> numberSpokenAtTurns = spokenNumbers.get(lastSpokenNumber);
        if (numberSpokenAtTurns.size() == 1) {
            addSpokenNumber(0);
        } else {
            final int previousTurn = numberSpokenAtTurns.get(numberSpokenAtTurns.size() - 1);
            final int lastSpokenBefore = numberSpokenAtTurns.get(numberSpokenAtTurns.size() - 2);
            addSpokenNumber(previousTurn - lastSpokenBefore);
        }
    }

    private void addSpokenNumber(final int number) {
        spokenNumbers.computeIfAbsent(number, k -> new ArrayList<>()).add(turn);
        lastSpokenNumber = number;
        turn++;
    }
}
