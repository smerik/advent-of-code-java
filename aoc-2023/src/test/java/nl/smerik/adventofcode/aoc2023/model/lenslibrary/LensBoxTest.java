package nl.smerik.adventofcode.aoc2023.model.lenslibrary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LensBoxTest {

    public static Stream<Arguments> sumFocusingPowerSource() {
        return Stream.of(
                // @formatter:off
                Arguments.of(  5, 0, "rn=1,cm=2"     ),
                Arguments.of(140, 3, "ot=7,ab=5,pc=6")
                // @formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("sumFocusingPowerSource")
    void testSumFocusingPowerBox(final int expectedResult, final int boxNumber, final String lenses) {
        final LensBox box = new LensBox(boxNumber);
        for (final String lens : lenses.split(",")) {
            final String[] split = lens.split("=");
            box.add(split[0], Integer.parseInt(split[1]));
        }
        assertEquals(expectedResult, box.sumFocusingPower());
    }

    @Test
    void testToString() {
        // TODO: test empty box
        final LensBox box = new LensBox(3);
        box.add("ot", 7);
        box.add("ab", 5);
        box.add("pc", 6);
        assertEquals("Box 3: [ot 7][ab 5][pc 6]", box.toString());
    }
}