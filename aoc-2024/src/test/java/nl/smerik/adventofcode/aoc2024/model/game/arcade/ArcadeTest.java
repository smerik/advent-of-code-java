package nl.smerik.adventofcode.aoc2024.model.game.arcade;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ArcadeTest {

    @Value("classpath:input/day-13/example-01.txt")
    private Resource example01Resource;

    private static Stream<Arguments> provideSourceForDetermineMinTokensToPossiblePrize() {
        return Stream.of(
                //@formatter:off
                Arguments.of(         480L, false),
                Arguments.of(875318608908L, true )
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForDetermineMinTokensToPossiblePrize")
    void testDetermineMinTokensToWinAllPossiblePrizes(final long expected, final boolean fixUnitConversionError) {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        final Arcade arcade = new Arcade(lines, fixUnitConversionError);
        assertEquals(expected, arcade.determineMinTokensToWinAllPossiblePrizes());
    }
}