package nl.smerik.adventofcode.aoc2020.model.customs;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
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

    public Set<Character> getIntersectionOfAnswers() {
        // Used: https://codereview.stackexchange.com/questions/145594/intersection-of-a-stream-of-collections
        final Iterator<Set<Character>> iterator = this.answers.stream()
                                                              .map(PersonAnswers::getAnswers)
                                                              .sorted(Comparator.comparingInt(Collection::size))
                                                              .iterator();
        if (!iterator.hasNext()) {
            return Collections.emptySet();
        }

        final Set<Character> result = new HashSet<>(iterator.next());
        while (iterator.hasNext()) {
            result.retainAll(new HashSet<>(iterator.next()));
        }
        return result;
    }

    public int countIntersectionOfAnswers() {
        return getIntersectionOfAnswers().size();
    }
}
