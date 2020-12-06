package nl.smerik.adventofcode.aoc2020.model.customs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonAnswersTest {

    private static Stream<Arguments> provideSourceForPersonAnswers() {
        return Stream.of(
                // @formatter:off
                Arguments.of("abcx", 4, Set.of('a', 'b', 'c', 'x')),
                Arguments.of("abcy", 4, Set.of('a', 'b', 'c', 'y')),
                Arguments.of("klm" , 3, Set.of('k', 'l', 'm'     ))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForPersonAnswers")
    void personAnswers(final String line, final int expectedSize, final Set<Character> expectedResult) {
        final PersonAnswers personAnswers = new PersonAnswers(line);

        final Set<Character> result = personAnswers.getAnswers();

        assertEquals(expectedSize, result.size());
        assertEquals(result, expectedResult);
    }
}