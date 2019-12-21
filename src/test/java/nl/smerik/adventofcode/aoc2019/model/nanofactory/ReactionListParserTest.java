package nl.smerik.adventofcode.aoc2019.model.nanofactory;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReactionListParserTest {

    @Test
    void parseExample01() throws IOException {
        final Path pathExample = Paths.get("src", "test", "resources", "input", "day-14-example-01.txt");

        final NanoFactory result = ReactionListParser.parse(pathExample);

        final Map<String, Reaction> rules = result.getReactionRules();
        assertEquals(6, rules.size());
        assertConsumes(rules.get("A"), 10, "ORE");
        assertProduces(rules.get("A"), 10, "A");

        assertConsumes(rules.get("B"), 1, "ORE");
        assertProduces(rules.get("B"), 1, "B");

        assertConsumes(rules.get("C"), 7, "A");
        assertConsumes(rules.get("C"), 1, "B");
        assertProduces(rules.get("C"), 1, "C");

        assertConsumes(rules.get("D"), 7, "A");
        assertConsumes(rules.get("D"), 1, "C");
        assertProduces(rules.get("D"), 1, "D");

        assertConsumes(rules.get("E"), 7, "A");
        assertConsumes(rules.get("E"), 1, "D");
        assertProduces(rules.get("E"), 1, "E");

        assertConsumes(rules.get("FUEL"), 7, "A");
        assertConsumes(rules.get("FUEL"), 1, "E");
        assertProduces(rules.get("FUEL"), 1, "FUEL");

    }

    private void assertConsumes(final Reaction reaction, final int units, final String chemical) {
        assertTrue(reaction.getConsumesChemicalUnits().contains(new ChemicalUnits(chemical, units)));
    }

    private void assertProduces(final Reaction reaction, final int units, final String chemical) {
        assertEquals(chemical, reaction.getProducesChemicalUnits().getChemical());
        assertEquals(units, reaction.getProducesChemicalUnits().getUnits());
    }
}