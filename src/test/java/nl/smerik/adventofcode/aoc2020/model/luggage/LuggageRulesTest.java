package nl.smerik.adventofcode.aoc2020.model.luggage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LuggageRulesTest {

    private static List<String> rulesPart01Example01;

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
    }

    @Test
    void parseLuggageRulesPart01Example01() {
        final LuggageRules luggageRules = new LuggageRules(rulesPart01Example01);

        // These rules specify the required contents for 9 bag types.
        assertEquals(9, luggageRules.getBags().size());
        // In this example, every faded blue bag is empty
        assertEquals(0, luggageRules.getBags().get("faded blue").getNumberOfBags());
        // Every vibrant plum bag contains 11 bags (5 faded blue and 6 dotted black)
        assertEquals(11, luggageRules.getBags().get("vibrant plum").getNumberOfBags());
        // and so on
        assertEquals(3, luggageRules.getBags().get("light red").getNumberOfBags());
        assertEquals(7, luggageRules.getBags().get("dark orange").getNumberOfBags());
        assertEquals(1, luggageRules.getBags().get("bright white").getNumberOfBags());
        assertEquals(11, luggageRules.getBags().get("muted yellow").getNumberOfBags());
        assertEquals(3, luggageRules.getBags().get("shiny gold").getNumberOfBags());
        assertEquals(7, luggageRules.getBags().get("dark olive").getNumberOfBags());
        assertEquals(0, luggageRules.getBags().get("dotted black").getNumberOfBags());
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

    private Set<Bag> createBags(final String... bagTypes) {
        return Stream.of(bagTypes).map(Bag::new).collect(Collectors.toSet());
    }
}