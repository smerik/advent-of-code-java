package nl.smerik.adventofcode.aoc2019.model.nanofactory;

import org.junit.jupiter.api.BeforeAll;
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
    private static NanoFactory nanoFactoryExample02;
    private static NanoFactory nanoFactoryExample03;
    private static NanoFactory nanoFactoryExample04;
    private static NanoFactory nanoFactoryExample05;

    @BeforeAll
    static void initAll() throws IOException {
        nanoFactoryExample01 = ReactionListParser.parse(
                Paths.get("src", "test", "resources", "input", "day-14-example-01.txt"));
        nanoFactoryExample02 = ReactionListParser.parse(
                Paths.get("src", "test", "resources", "input", "day-14-example-02.txt"));
        nanoFactoryExample03 = ReactionListParser.parse(
                Paths.get("src", "test", "resources", "input", "day-14-example-03.txt"));
        nanoFactoryExample04 = ReactionListParser.parse(
                Paths.get("src", "test", "resources", "input", "day-14-example-04.txt"));
        nanoFactoryExample05 = ReactionListParser.parse(
                Paths.get("src", "test", "resources", "input", "day-14-example-05.txt"));
    }

    private static Stream<Arguments> provideSourceForHowMuchChemicalUnitsIsNeededToProduce() {
        return Stream.of(
                Arguments.of(nanoFactoryExample01, 10, "ORE", 10, "A"),
                Arguments.of(nanoFactoryExample01, 20, "ORE", 15, "A"),
                Arguments.of(nanoFactoryExample01, 30, "ORE", 28, "A"),
                Arguments.of(nanoFactoryExample01, 1, "ORE", 1, "B"),
                Arguments.of(nanoFactoryExample01, 2, "ORE", 2, "B"),
                Arguments.of(nanoFactoryExample01, 11, "ORE", 1, "C"),
                Arguments.of(nanoFactoryExample01, 21, "ORE", 1, "D"),
                Arguments.of(nanoFactoryExample01, 31, "ORE", 1, "E"),
                Arguments.of(nanoFactoryExample01, 31, "ORE", 1, "FUEL"),

                Arguments.of(nanoFactoryExample02, 165, "ORE", 1, "FUEL"),
                Arguments.of(nanoFactoryExample03, 13312, "ORE", 1, "FUEL"),
                Arguments.of(nanoFactoryExample04, 180697, "ORE", 1, "FUEL"),
                Arguments.of(nanoFactoryExample05, 2210736, "ORE", 1, "FUEL")
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForHowMuchChemicalUnitsIsNeededToProduce")
    void getHowMuchChemicalUnitsIsNeededToProduce(final NanoFactory nanoFactory,
                                                  final long expectedUnits, final String expectedChemical,
                                                  final long produceUnits, final String produceChemical) {

        final ChemicalUnits chemicalUnitsToProduce = new ChemicalUnits(produceChemical, produceUnits);
        final Map<String, Long> result = nanoFactory.getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnitsToProduce);
        assertEquals(expectedUnits, result.get(expectedChemical));
    }

    @Test
    void getSolutionDay14Part01() throws IOException {
        final Path pathExample = Paths.get("src", "test", "resources", "input", "day-14.txt");
        final NanoFactory nanoFactory = ReactionListParser.parse(pathExample);

        final ChemicalUnits chemicalUnitsToProduce = new ChemicalUnits("FUEL", 1);
        final Map<String, Long> result = nanoFactory.getHowMuchChemicalUnitsIsNeededToProduce(chemicalUnitsToProduce);

        assertEquals(612880, result.get("ORE"));
    }

    private static Stream<Arguments> provideSourceForHowMuchFuelCanBeProduced() {
        return Stream.of(
                Arguments.of(nanoFactoryExample03, 1_000_000_000_000L, 82892753),
                Arguments.of(nanoFactoryExample04, 1_000_000_000_000L, 5586022),
                Arguments.of(nanoFactoryExample05, 1_000_000_000_000L, 460664)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForHowMuchFuelCanBeProduced")
    void getHowMuchFuelCanBeProduced(final NanoFactory nanoFactory,
                                     final Long oreInCargo, final int expectedAmountOfFuel) {
        int low = 0, mid = -1, high = (int) 1E12;
//        while (low <= high) {
        mid = (low + high) >>> 1;
//        }
        System.out.print("mid:" + mid);
//        assertEquals(expectedAmountOfFuel, nanoFactory.getHowMuchFuelCanBeProduced(oreInCargo));
    }
}