package nl.smerik.adventofcode.aoc2020.model.luggage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LuggageRulesTest {

    private static List<String> rulesPart01Example01;
    private static List<String> rulesPart02Example01;

    @BeforeAll
    public static void initAll() {
        rulesPart01Example01 = List.of(
                "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                "faded blue bags contain no other bags.",
                "dotted black bags contain no other bags."
        );

        rulesPart02Example01 = List.of(
                "shiny gold bags contain 2 dark red bags.",
                "dark red bags contain 2 dark orange bags.",
                "dark orange bags contain 2 dark yellow bags.",
                "dark yellow bags contain 2 dark green bags.",
                "dark green bags contain 2 dark blue bags.",
                "dark blue bags contain 2 dark violet bags.",
                "dark violet bags contain no other bags."
        );
    }

    private static Stream<Arguments> provideSourceForCountTotalNumberOfBagsInside() {
        return Stream.of(
                // @formatter:off
                Arguments.of(rulesPart01Example01, "shiny gold",  32),
                Arguments.of(rulesPart02Example01, "shiny gold", 126)
                // @formatter:on
        );
    }

    @Test
    void parseLuggageRulesPart01Example01() {
        final LuggageRules luggageRules = new LuggageRules(rulesPart01Example01);

        // These rules specify the required contents for 9 bag types.
        assertEquals(9, luggageRules.getBags().size());
        // In this example, every faded blue bag is empty
        assertEquals(0, luggageRules.getBags().get("faded blue").countNumberOfBags());
        // Every vibrant plum bag contains 11 bags (5 faded blue and 6 dotted black)
        assertEquals(11, luggageRules.getBags().get("vibrant plum").countNumberOfBags());
        // and so on
        assertEquals(3, luggageRules.getBags().get("light red").countNumberOfBags());
        assertEquals(7, luggageRules.getBags().get("dark orange").countNumberOfBags());
        assertEquals(1, luggageRules.getBags().get("bright white").countNumberOfBags());
        assertEquals(11, luggageRules.getBags().get("muted yellow").countNumberOfBags());
        assertEquals(3, luggageRules.getBags().get("shiny gold").countNumberOfBags());
        assertEquals(7, luggageRules.getBags().get("dark olive").countNumberOfBags());
        assertEquals(0, luggageRules.getBags().get("dotted black").countNumberOfBags());
    }

    @Test
    void getBagTypesEventuallyContaining() {
        final LuggageRules luggageRules = new LuggageRules(rulesPart01Example01);

        final Set<Bag> result = luggageRules.getBagsEventuallyContaining("shiny gold");

        // So, in this example, the number of bag colors that can eventually contain at least one shiny gold bag is 4
        assertEquals(4, result.size());

        // In the above rules, the following options would be available to you
        // A bright white bag, which can hold your shiny gold bag directly.
        final Set<Bag> expectedResult = createBags("bright white", "muted yellow", "dark orange", "light red");
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("provideSourceForCountTotalNumberOfBagsInside")
    void getTotalNumberOfBags(final List<String> rules, final String bagType, final int expectedResult) {
        final LuggageRules luggageRules = new LuggageRules(rules);

        final int result = luggageRules.countTotalNumberOfBagsInside(bagType);

        // So, in this example, the number of bag colors that can eventually contain at least one shiny gold bag is 4
        assertEquals(expectedResult, result);
    }

    private Set<Bag> createBags(final String... bagTypes) {
        return Stream.of(bagTypes).map(Bag::new).collect(Collectors.toSet());
    }
}