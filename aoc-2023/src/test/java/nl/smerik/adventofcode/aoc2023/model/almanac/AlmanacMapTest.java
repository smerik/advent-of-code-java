package nl.smerik.adventofcode.aoc2023.model.almanac;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlmanacMapTest {

    private static AlmanacMap ALMANAC_MAP_EXAMPLE_01;

    @BeforeAll
    static void beforeAll() {
        ALMANAC_MAP_EXAMPLE_01 = new AlmanacMap(AlmanacCategory.SEED, AlmanacCategory.SOIL);
        ALMANAC_MAP_EXAMPLE_01.addRange(new AlmanacRange(50, 98, 2));
        ALMANAC_MAP_EXAMPLE_01.addRange(new AlmanacRange(52, 50, 48));
    }

    @ParameterizedTest
    @CsvSource({
            " 0,  0",
            " 1,  1",
            //
            "48, 48",
            "49, 49",
            "50, 52",
            "51, 53",
            //
            "96, 98",
            "97, 99",
            "98, 50",
            "99, 51",
            //
            "79, 81",
            "14, 14",
            "55, 57",
            "13, 13"
    })
    void mapSourceToDestination(final long sourceNumber, final long destinationNumber) {
        assertEquals(destinationNumber, ALMANAC_MAP_EXAMPLE_01.mapSourceToDestination(sourceNumber));
    }

    @ParameterizedTest
    @CsvSource({
            " 0,  0",
            " 1,  1",
            //
            "48, 48",
            "49, 49",
            "50, 52",
            "51, 53",
            //
            "96, 98",
            "97, 99",
            "98, 50",
            "99, 51",
            //
            "79, 81",
            "14, 14",
            "55, 57",
            "13, 13"
    })
    void mapDestinationToSource(final long sourceNumber, final long destinationNumber) {
        assertEquals(sourceNumber, ALMANAC_MAP_EXAMPLE_01.mapDestinationToSource(destinationNumber));
    }
}