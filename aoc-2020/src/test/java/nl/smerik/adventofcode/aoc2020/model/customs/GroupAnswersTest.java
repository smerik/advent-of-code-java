package nl.smerik.adventofcode.aoc2020.model.customs;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupAnswersTest {

    @Test
    void addAnswers() {
        final GroupAnswers groupAnswers = new GroupAnswers();
        assertEquals(0, groupAnswers.getAnswers().size());

        groupAnswers.addAnswers(new PersonAnswers("abc"));
        assertEquals(1, groupAnswers.getAnswers().size());

        groupAnswers.addAnswers(new PersonAnswers("abc"));
        assertEquals(2, groupAnswers.getAnswers().size());
    }

    @Test
    void getUnionOfAnswers() {
        final GroupAnswers groupAnswers = new GroupAnswers();
        groupAnswers.addAnswers(new PersonAnswers("abcx"));
        groupAnswers.addAnswers(new PersonAnswers("abcy"));
        groupAnswers.addAnswers(new PersonAnswers("abcz"));

        assertEquals(Set.of('a', 'b', 'c', 'x', 'y', 'z'), groupAnswers.getUnionOfAnswers());
    }

    @Test
    void countUnionOfAnswers() {
        final GroupAnswers groupAnswers = new GroupAnswers();
        groupAnswers.addAnswers(new PersonAnswers("abcx"));
        groupAnswers.addAnswers(new PersonAnswers("abcy"));
        groupAnswers.addAnswers(new PersonAnswers("abcz"));

        assertEquals(6, groupAnswers.countUnionOfAnswers());
    }

    @Test
    void getIntersectionOfAnswers() {
        final GroupAnswers groupAnswers = new GroupAnswers();
        groupAnswers.addAnswers(new PersonAnswers("abcx"));
        groupAnswers.addAnswers(new PersonAnswers("abcy"));
        groupAnswers.addAnswers(new PersonAnswers("abcz"));

        assertEquals(Set.of('a', 'b', 'c'), groupAnswers.getIntersectionOfAnswers());
    }

    @Test
    void countIntersectionOfAnswers() {
        final GroupAnswers groupAnswers = new GroupAnswers();
        groupAnswers.addAnswers(new PersonAnswers("abcx"));
        groupAnswers.addAnswers(new PersonAnswers("abcy"));
        groupAnswers.addAnswers(new PersonAnswers("abcz"));

        assertEquals(3, groupAnswers.countIntersectionOfAnswers());
    }
}