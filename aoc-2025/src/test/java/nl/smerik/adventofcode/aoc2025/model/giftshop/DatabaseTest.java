package nl.smerik.adventofcode.aoc2025.model.giftshop;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DatabaseTest {

    @Value("classpath:input/day-02/example-01.txt")
    private Resource example01Resource;

    private static Stream<Arguments> example() {
        return Stream.of(
                // @formatter:off
                Arguments.of(false, 1227775554L),
                Arguments.of(true , 4174379265L)
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("example")
    void testSumInvalidIds(final boolean shouldHaveMultipleRepeats, final Long expected) {
        // Given
        final String line = PuzzleInputParser.parseToString(example01Resource).get(0);
        final Database database = new Database(line);
        // When & Then
        assertEquals(expected, database.sumInvalidIds(shouldHaveMultipleRepeats));
    }
}