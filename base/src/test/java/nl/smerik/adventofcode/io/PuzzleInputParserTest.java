package nl.smerik.adventofcode.io;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the {@link PuzzleInputParser}.
 */
@SpringBootTest
class PuzzleInputParserTest {

    @Value("classpath:input/non-existing.txt")
    private Resource nonExistingResource;

    @Value("classpath:input/strings.txt")
    private Resource stringResource;

    @Value("classpath:input/numbers.txt")
    private Resource numberResource;

    @Test
    void testExpectedUnresolvableURIException() {
        final UnresolvableURIException thrown = assertThrows(UnresolvableURIException.class,
                () -> PuzzleInputParser.parseToInt(nonExistingResource),
                "UnresolvableURIException was expected");
        assertEquals("class path resource [input/non-existing.txt] cannot be resolved to URL because it does not exist",
                thrown.getMessage());
    }

    @Test
    void testParseToString() {
        final List<String> result = PuzzleInputParser.parseToString(stringResource);
        assertEquals(List.of("foo", "bar", "baz", "qaz"), result);
    }

    @Test
    void testParseToInt() {
        final List<Integer> result = PuzzleInputParser.parseToInt(numberResource);
        assertEquals(List.of(200, 201, 101, 100), result);
    }

    @Test
    void testParseToLong() {
        final List<Long> result = PuzzleInputParser.parseToLong(numberResource);
        assertEquals(List.of(200L, 201L, 101L, 100L), result);
    }
}