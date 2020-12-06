package nl.smerik.adventofcode.aoc2020.model.customs;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class PersonAnswers {

    @Getter
    private final Set<Character> answers;

    public PersonAnswers(final String line) {
        answers = new HashSet<>();
        for (final char answer : line.toCharArray()) {
            answers.add(answer);
        }
    }
}
