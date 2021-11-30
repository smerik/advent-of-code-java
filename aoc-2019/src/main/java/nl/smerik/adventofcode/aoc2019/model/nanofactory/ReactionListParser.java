package nl.smerik.adventofcode.aoc2019.model.nanofactory;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public final class ReactionListParser {

    private ReactionListParser() {
    }

    public static NanoFactory parse(final Path path) throws IOException {
        LOG.info("Parsing reaction list '{}'...", path);
        final Set<Reaction> reactionRules = new HashSet<>();

        final BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            reactionRules.add(parseLine(currentLine));
        }

        LOG.info("Finished reaction list containing {} rules.", reactionRules.size());
        return new NanoFactory(reactionRules);
    }

    private static Reaction parseLine(final String line) {
        final String[] reaction = line.split("=>");
        final String[] consumedAmountOfChemicals = reaction[0].split(",");

        final ChemicalUnits producesChemicalUnits = parseChemicalUnits(reaction[1]);
        final List<ChemicalUnits> consumesChemicalUnits = Stream.of(consumedAmountOfChemicals)
                .map(ReactionListParser::parseChemicalUnits)
                .collect(Collectors.toList());
        return new Reaction(producesChemicalUnits, consumesChemicalUnits);
    }

    private static ChemicalUnits parseChemicalUnits(final String chemicalUnitsX) {
        final String[] chemicalUnits = chemicalUnitsX.trim().split(" ");
        return new ChemicalUnits(chemicalUnits[1], Integer.parseInt(chemicalUnits[0]));
    }
}
