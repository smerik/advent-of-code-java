package nl.smerik.adventofcode.aoc2021.model.display;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FourDigitDisplayTest {

    private static Stream<Arguments> notesExample02() {
        return Stream.of(
                Arguments.of(2, "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe"),
                Arguments.of(3, "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc"),
                Arguments.of(3, "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg"),
                Arguments.of(1, "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb"),
                Arguments.of(3, "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea"),
                Arguments.of(4, "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb"),
                Arguments.of(3, "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe"),
                Arguments.of(1, "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef"),
                Arguments.of(4, "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb"),
                Arguments.of(2, "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce")
        );
    }

    @ParameterizedTest
    @MethodSource("notesExample02")
    void testCountDigits(final int expectedDigits, final String note) {
        final FourDigitDisplay display = new FourDigitDisplay(note);
        assertEquals(expectedDigits, display.countDigits(Set.of(1, 4, 7, 8)));
    }
}