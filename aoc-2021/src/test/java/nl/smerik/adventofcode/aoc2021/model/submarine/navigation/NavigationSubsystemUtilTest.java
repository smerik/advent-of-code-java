package nl.smerik.adventofcode.aoc2021.model.submarine.navigation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static nl.smerik.adventofcode.aoc2021.model.submarine.navigation.ChunkPairType.*;
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

    @Test
    void testDetermineMedianOfCompletionScores() {
        assertEquals(288957, NavigationSubsystemUtil.determineMedianOfCompletionScores(SUBSYSTEM_EXAMPLE_01));
    }

    @Test
    void testCalculateCompletionScores() {
        assertEquals(List.of(288957, 5566, 1480781, 995444, 294), NavigationSubsystemUtil.calculateCompletionScores(SUBSYSTEM_EXAMPLE_01));
    }

    private static Stream<Arguments> calculateCompletionScore() {
        return Stream.of(
                // @formatter:off
                Arguments.of("[({(<(())[]>[[{[]{<()<>>",  288957),
                Arguments.of("[(()[<>])]({[<{<<[]>>("  ,    5566),
                Arguments.of("(((({<>}<{<{<>}{[]{[]{}" , 1480781),
                Arguments.of("{<[[]]>}<{[{[{[]{()[[[]" ,  995444),
                Arguments.of("<{([{{}}[<[[[<>{}]]]>[]]" ,    294)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("calculateCompletionScore")
    void testCalculateCompletionScore(final String line, final int expectedScore) throws Exception {
        assertEquals(expectedScore, NavigationSubsystemUtil.calculateCompletionScore(line));
    }


    private static Stream<Arguments> parseLineWithExceptions() {
        return Stream.of(
                // @formatter:off
                Arguments.of("{([(<{}[<>[]}>{[]{[(<()>", CURLY_CLOSE ),
                Arguments.of("[[<[([]))<([[{}[[()]]]"  , ROUND_CLOSE ),
                Arguments.of("[{[{({}]{}}([{[{{{}}([]" , SQUARE_CLOSE),
                Arguments.of("[<(<(<(<{}))><([]([]()"  , ROUND_CLOSE ),
                Arguments.of("<{([([[(<>()){}]>(<<{{"  , ANGLE_CLOSE )
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("parseLineWithExceptions")
    void testParseLine(final String line, final ChunkPairType expectedExceptionType) {
        final NavigationSyntaxErrorException thrown = Assertions.assertThrows(NavigationSyntaxErrorException.class,
                () -> NavigationSubsystemUtil.parseLine(line), "NumberFormatException error was expected");
        Assertions.assertEquals(expectedExceptionType, thrown.getUnexpectedPairType());
    }


    private static Stream<Arguments> parseLineWithoutExceptions() {
        return Stream.of(
                // @formatter:off
                Arguments.of("[({(<(())[]>[[{[]{<()<>>", List.of(CURLY_OPEN, CURLY_OPEN, SQUARE_OPEN, SQUARE_OPEN, ROUND_OPEN, CURLY_OPEN, ROUND_OPEN, SQUARE_OPEN)),
                Arguments.of("[(()[<>])]({[<{<<[]>>("  , List.of(ROUND_OPEN, CURLY_OPEN, ANGLE_OPEN, SQUARE_OPEN, CURLY_OPEN, ROUND_OPEN)),
                Arguments.of("(((({<>}<{<{<>}{[]{[]{}" , List.of(CURLY_OPEN, CURLY_OPEN, ANGLE_OPEN, CURLY_OPEN, ANGLE_OPEN, ROUND_OPEN, ROUND_OPEN, ROUND_OPEN, ROUND_OPEN)),
                Arguments.of("{<[[]]>}<{[{[{[]{()[[[]" , List.of(SQUARE_OPEN, SQUARE_OPEN, CURLY_OPEN, CURLY_OPEN, SQUARE_OPEN, CURLY_OPEN, SQUARE_OPEN, CURLY_OPEN, ANGLE_OPEN)),
                Arguments.of("<{([{{}}[<[[[<>{}]]]>[]]", List.of(SQUARE_OPEN, ROUND_OPEN, CURLY_OPEN, ANGLE_OPEN))
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("parseLineWithoutExceptions")
    void testParseLine(final String line, final List<ChunkPairType> expectedResult) throws Exception {
        // Since the stack (a.k.a. Dequeue) result is converted into a list the expectedResult is the reverse order of how it would have been popped
        assertEquals(expectedResult, new ArrayList<>(NavigationSubsystemUtil.parseLine(line)));
    }
}