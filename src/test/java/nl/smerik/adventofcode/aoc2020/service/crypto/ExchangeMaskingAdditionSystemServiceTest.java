package nl.smerik.adventofcode.aoc2020.service.crypto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExchangeMaskingAdditionSystemServiceTest {

    @Autowired
    private ExchangeMaskingAdditionSystemService xmasService;

    private final List<Long> preamblePart01Example01
            = LongStream.rangeClosed(1, 25).boxed().collect(Collectors.toList());

    private static Stream<Arguments> provideSourceForValidatePart01Example01() {
        return Stream.of(
                // @formatter:off
                Arguments.of( 26L, true ),
                Arguments.of( 49L, true ),
                Arguments.of(100L, false),
                Arguments.of( 50L, false)
                // @formatter:on
        );
    }

    private final List<Long> example01Part01 = List.of(
            35L,
            20L,
            15L,
            25L,
            47L,
            40L,
            62L,
            55L,
            65L,
            95L,
            102L,
            117L,
            150L,
            182L,
            127L,
            219L,
            299L,
            277L,
            309L,
            576L
    );

    @ParameterizedTest
    @MethodSource("provideSourceForValidatePart01Example01")
    void validatePart01Example01(final long nextNumber, final boolean isValid) {
        final ArrayList<Long> numbers = new ArrayList<>(preamblePart01Example01);
        numbers.add(nextNumber);
        final Long result = xmasService.validate(numbers, 25);
        assertEquals(isValid ? 0 : nextNumber, result);
    }

    @Test
    void validatePart01Example02() {
        assertEquals(127L, xmasService.validate(example01Part01, 5));
    }

    @Test
    void findContiguousRange() {
        final List<Long> expectedResult = List.of(15L, 25L, 47L, 40L);
        assertEquals(expectedResult, xmasService.findContiguousRange(example01Part01, 127L));
    }

    @Test
    void findEncryptionWeakness() {
        final List<Long> contiguousRange = List.of(15L, 25L, 47L, 40L);
        assertEquals(62L, xmasService.findEncryptionWeakness(contiguousRange));
    }
}