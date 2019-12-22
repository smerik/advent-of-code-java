package nl.smerik.adventofcode.aoc2019.model.nanofactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NanoFactoryTest {

    private static NanoFactory nanoFactoryExample01;

    @BeforeAll
    static void initAll() throws IOException {
        final Path pathExample = Paths.get("src", "test", "resources", "input", "day-14-example-01.txt");
        nanoFactoryExample01 = ReactionListParser.parse(pathExample);
    }

    private static Stream<Arguments> provideSourceForHowMuchChemicalUnitsIsNeededToProduce() {
        return Stream.of(
                Arguments.of(10, "ORE", 10, "A"),
                Arguments.of(20, "ORE", 15, "A"),
                Arguments.of(30, "ORE", 28, "A"),
                Arguments.of(1, "ORE", 1, "B"),
                Arguments.of(2, "ORE", 2, "B"),
                Arguments.of(11, "ORE", 1, "C"),
                Arguments.of(21, "ORE", 1, "D"),
                Arguments.of(31, "ORE", 1, "E"),
                Arguments.of(31, "ORE", 1, "FUEL")
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForHowMuchChemicalUnitsIsNeededToProduce")
    void getHowMuchChemicalUnitsIsNeededToProduce(final int expectedUnits, final String expectedChemical,
                                                  final int produceUnits, final String produceChemical) {

        final ChemicalUnits chemicalUnitsToProduce = new ChemicalUnits(produceChemical, produceUnits);
        final Map<String, Integer> result = nanoFactoryExample01.getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnitsToProduce);
        assertEquals(expectedUnits, result.get(expectedChemical));
    }

    @Test
    void getHowMuchChemicalUnitsIsNeededToProduceExample02() throws IOException {
        final Path pathExample = Paths.get("src", "test", "resources", "input", "day-14-example-02.txt");
        final NanoFactory nanoFactory = ReactionListParser.parse(pathExample);

        final ChemicalUnits chemicalUnitsToProduce = new ChemicalUnits("FUEL", 1);
        final Map<String, Integer> result = nanoFactory.getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnitsToProduce);

        assertEquals(165, result.get("ORE"));
    }

    @Test
    void getHowMuchChemicalUnitsIsNeededToProduceExample03() throws IOException {
        final Path pathExample = Paths.get("src", "test", "resources", "input", "day-14-example-03.txt");
        final NanoFactory nanoFactory = ReactionListParser.parse(pathExample);

        final ChemicalUnits chemicalUnitsToProduce = new ChemicalUnits("FUEL", 1);
        final Map<String, Integer> result = nanoFactory.getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnitsToProduce);

        assertEquals(13312, result.get("ORE"));
    }

    @Test
    void getHowMuchChemicalUnitsIsNeededToProduceExample04() throws IOException {
        final Path pathExample = Paths.get("src", "test", "resources", "input", "day-14-example-04.txt");
        final NanoFactory nanoFactory = ReactionListParser.parse(pathExample);

        final ChemicalUnits chemicalUnitsToProduce = new ChemicalUnits("FUEL", 1);
        final Map<String, Integer> result = nanoFactory.getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnitsToProduce);

        assertEquals(180697, result.get("ORE"));
    }


    @Test
    void getHowMuchChemicalUnitsIsNeededToProduceExample05() throws IOException {
        final Path pathExample = Paths.get("src", "test", "resources", "input", "day-14-example-05.txt");
        final NanoFactory nanoFactory = ReactionListParser.parse(pathExample);

        final ChemicalUnits chemicalUnitsToProduce = new ChemicalUnits("FUEL", 1);
        final Map<String, Integer> result = nanoFactory.getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnitsToProduce);

        assertEquals(2210736, result.get("ORE"));
    }

    @Test
    void getSolutionDay14Part01() throws IOException {
        final Path pathExample = Paths.get("src", "test", "resources", "input", "day-14.txt");
        final NanoFactory nanoFactory = ReactionListParser.parse(pathExample);

        final ChemicalUnits chemicalUnitsToProduce = new ChemicalUnits("FUEL", 1);
        final Map<String, Integer> result = nanoFactory.getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnitsToProduce);

        assertEquals(612880, result.get("ORE"));
    }

    @Test
    void getHowMuchFuelCanBeProduced() {

        82892753
    }
}