package nl.smerik.adventofcode.aoc2021.model.submarine.navigation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NavigationSubsystemUtilTest {

    private static final List<String> SUBSYSTEM_EXAMPLE_01 = List.of(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
    );

    private static Stream<Arguments> syntaxErrorScore() {
        return Stream.of(
                // @formatter:off
                Arguments.of("[({(<(())[]>[[{[]{<()<>>",     0),
                Arguments.of("[(()[<>])]({[<{<<[]>>("  ,     0),
                Arguments.of("{([(<{}[<>[]}>{[]{[(<()>",  1197), // Expected ], but found } instead
                Arguments.of("(((({<>}<{<{<>}{[]{[]{}" ,     0),
                Arguments.of("[[<[([]))<([[{}[[()]]]"  ,     3), // Expected ], but found ) instead
                Arguments.of("[{[{({}]{}}([{[{{{}}([]" ,    57), // Expected ), but found ] instead
                Arguments.of("{<[[]]>}<{[{[{[]{()[[[]" ,     0),
                Arguments.of("[<(<(<(<{}))><([]([]()"  ,     3), // Expected >, but found ) instead
                Arguments.of("<{([([[(<>()){}]>(<<{{"  , 25137), // Expected ], but found > instead
                Arguments.of("<{([{{}}[<[[[<>{}]]]>[]]",     0)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("syntaxErrorScore")
    void testCalculateSyntaxErrorScore(final String line, final int expectedScore) {
        assertEquals(expectedScore, NavigationSubsystemUtil.calculateSyntaxErrorScore(line));
    }

    @Test
    void testCalculateTotalSyntaxErrorScore() {
        assertEquals(26397, NavigationSubsystemUtil.calculateTotalSyntaxErrorScore(SUBSYSTEM_EXAMPLE_01));
    }
}