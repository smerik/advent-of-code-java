package nl.smerik.adventofcode.aoc2024.model.printer;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
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
class SafetyManualTest {

    private final SafetyManual manual;

    public SafetyManualTest(@Value("classpath:input/day-05/example-01.txt") Resource example01Resource) {
        final List<String> lines = PuzzleInputParser.parseToString(example01Resource);
        this.manual = new SafetyManual(lines);
    }

    @Test
    void testSumMiddlePagesOfCorrectlyOrderedUpdates() {
        assertEquals(143, manual.sumMiddlePagesOfCorrectlyOrderedUpdates());
    }

    @Test
    void testFindMiddlePagesOfCorrectlyOrderedUpdates() {
        assertEquals(List.of(61, 53, 29), manual.findMiddlePagesOfCorrectlyOrderedUpdates());
    }

    @Test
    void testFindCorrectlyOrderedUpdates() {
        final List<Integer> firstUpdate = List.of(75, 47, 61, 53, 29);
        final List<Integer> secondUpdate = List.of(97, 61, 53, 29, 13);
        final List<Integer> thirdUpdate = List.of(75, 29, 13);
        final List<List<Integer>> expectedUpdates = List.of(firstUpdate, secondUpdate, thirdUpdate);
        assertEquals(expectedUpdates, manual.findCorrectlyOrderedUpdates());
    }

    private static Stream<Arguments> provideSourceForIsUpdateInTheRightOrder() {
        return Stream.of(
                //@formatter:off
                Arguments.of(true , List.of(75, 47, 61, 53, 29)),
                Arguments.of(true , List.of(97, 61, 53, 29, 13)),
                Arguments.of(true , List.of(75, 29, 13)        ),
                Arguments.of(false, List.of(75, 97, 47, 61, 53)),
                Arguments.of(false, List.of(61, 13, 29)        ),
                Arguments.of(false, List.of(97, 13, 75, 29, 47))
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForIsUpdateInTheRightOrder")
    void testIsUpdateInTheRightOrder(final boolean expectedResult, final List<Integer> pagesToUpdate) {
        assertEquals(expectedResult, manual.isUpdateInTheRightOrder(pagesToUpdate));
    }
}