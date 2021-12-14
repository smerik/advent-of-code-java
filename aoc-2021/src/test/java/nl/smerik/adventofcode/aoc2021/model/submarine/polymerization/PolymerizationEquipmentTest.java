package nl.smerik.adventofcode.aoc2021.model.submarine.polymerization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PolymerizationEquipmentTest {

    private static final List<String> EXAMPLE_01 = List.of(
            "NNCB",
            "",
            "CH -> B",
            "HH -> N",
            "CB -> H",
            "NH -> C",
            "HB -> C",
            "HC -> B",
            "HN -> C",
            "NN -> C",
            "BH -> H",
            "NC -> B",
            "NB -> B",
            "BN -> B",
            "BB -> N",
            "BC -> B",
            "CC -> N",
            "CN -> C"
    );

    @Test
    void testApplyRules() {
        final PolymerizationEquipment equipment = new PolymerizationEquipment(EXAMPLE_01);
        assertEquals("NCNBCHB", equipment.applyRules());
    }


    private static Stream<Arguments> applyRulesWithSteps() {
        return Stream.of(
                Arguments.of(1, "NCNBCHB"),
                Arguments.of(2, "NBCCNBBBCBHCB"),
                Arguments.of(3, "NBBBCNCCNBBNBNBBCHBHHBCHB"),
                Arguments.of(4, "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB")
        );
    }

    @ParameterizedTest
    @MethodSource("applyRulesWithSteps")
    void testApplyRulesWithSteps(final int steps, final String expectedResult) {
        final PolymerizationEquipment equipment = new PolymerizationEquipment(EXAMPLE_01);
        assertEquals(expectedResult, equipment.applyRules(steps));
    }


    private static Stream<Arguments> applyRulesLengthWithSteps() {
        return Stream.of(
                Arguments.of(5, 97),
                Arguments.of(10, 3073)
        );
    }

    @ParameterizedTest
    @MethodSource("applyRulesLengthWithSteps")
    void testApplyRulesLengthWithSteps(final int steps, final int expectedResult) {
        final PolymerizationEquipment equipment = new PolymerizationEquipment(EXAMPLE_01);
        assertEquals(expectedResult, equipment.applyRules(steps).length());
    }

    @Test
    void testCountElements() {
        final PolymerizationEquipment equipment = new PolymerizationEquipment(EXAMPLE_01);
        equipment.applyRules(10);
        final Map<Character, Long> elementsCount = equipment.countElements();
        assertEquals(1749, elementsCount.get('B'));
        assertEquals( 298, elementsCount.get('C'));
        assertEquals( 161, elementsCount.get('H'));
        assertEquals( 865, elementsCount.get('N'));
    }

    @Test
    void testFindLeastCommonElement() {
        final PolymerizationEquipment equipment = new PolymerizationEquipment(EXAMPLE_01);
        equipment.applyRules(10);
        assertEquals('H', equipment.findLeastCommonElement());
    }

    @Test
    void testFindMostCommonElement() {
        final PolymerizationEquipment equipment = new PolymerizationEquipment(EXAMPLE_01);
        equipment.applyRules(10);
        assertEquals('B', equipment.findMostCommonElement());
    }
}