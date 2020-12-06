package nl.smerik.adventofcode.aoc2020.model.customs;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupAnswers {

    @Getter
    private final List<PersonAnswers> answers;

    public GroupAnswers() {
        this.answers = new ArrayList<>();
    }

    public void addAnswers(final PersonAnswers answers) {
        this.answers.add(answers);
    }

    public Set<Character> getUnionOfAnswers() {
        return this.answers.stream()
                           .flatMap(pa -> pa.getAnswers().stream())
                           .collect(Collectors.toSet());
    }

    public int countUnionOfAnswers() {
        return getUnionOfAnswers().size();
    }
}
