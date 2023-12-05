package nl.smerik.adventofcode.aoc2023.model.almanac;

import nl.smerik.adventofcode.geom.Range;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.smerik.adventofcode.aoc2023.model.almanac.AlmanacCategory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlmanacTest {

    private static Almanac almanac;

    @BeforeAll
    static void beforeAll() {
        final List<Long> seedsToBePlanten = List.of(79L, 14L, 55L, 13L);

        final Map<AlmanacCategory, AlmanacMap> almanacMapByCategory = new HashMap<>();

        final AlmanacMap seedToSoilMap = new AlmanacMap(SEED, SOIL);
        seedToSoilMap.addRange(new AlmanacRange(50, 98, 2));
        seedToSoilMap.addRange(new AlmanacRange(52, 50, 48));
        almanacMapByCategory.put(SEED, seedToSoilMap);

        final AlmanacMap soilToFertilizerMap = new AlmanacMap(SOIL, FERTILIZER);
        soilToFertilizerMap.addRange(new AlmanacRange(0, 15, 37));
        soilToFertilizerMap.addRange(new AlmanacRange(37, 52, 2));
        soilToFertilizerMap.addRange(new AlmanacRange(39, 0, 15));
        almanacMapByCategory.put(SOIL, soilToFertilizerMap);

        final AlmanacMap fertilizerToWaterMap = new AlmanacMap(FERTILIZER, WATER);
        fertilizerToWaterMap.addRange(new AlmanacRange(49, 53, 8));
        fertilizerToWaterMap.addRange(new AlmanacRange(0, 11, 42));
        fertilizerToWaterMap.addRange(new AlmanacRange(42, 0, 7));
        fertilizerToWaterMap.addRange(new AlmanacRange(57, 7, 4));
        almanacMapByCategory.put(FERTILIZER, fertilizerToWaterMap);

        final AlmanacMap waterToLightMap = new AlmanacMap(WATER, LIGHT);
        waterToLightMap.addRange(new AlmanacRange(88, 18, 7));
        waterToLightMap.addRange(new AlmanacRange(18, 25, 70));
        almanacMapByCategory.put(WATER, waterToLightMap);

        final AlmanacMap lightToTemperatureMap = new AlmanacMap(LIGHT, TEMPERATURE);
        lightToTemperatureMap.addRange(new AlmanacRange(45, 77, 23));
        lightToTemperatureMap.addRange(new AlmanacRange(81, 45, 19));
        lightToTemperatureMap.addRange(new AlmanacRange(68, 64, 13));
        almanacMapByCategory.put(LIGHT, lightToTemperatureMap);

        final AlmanacMap temperatureToHumidityMap = new AlmanacMap(TEMPERATURE, HUMIDITY);
        temperatureToHumidityMap.addRange(new AlmanacRange(0, 69, 1));
        temperatureToHumidityMap.addRange(new AlmanacRange(1, 0, 69));
        almanacMapByCategory.put(TEMPERATURE, temperatureToHumidityMap);

        final AlmanacMap humidityToLocationMap = new AlmanacMap(HUMIDITY, LOCATION);
        humidityToLocationMap.addRange(new AlmanacRange(60, 56, 37));
        humidityToLocationMap.addRange(new AlmanacRange(56, 93, 4));
        almanacMapByCategory.put(HUMIDITY, humidityToLocationMap);

        almanac = new Almanac(seedsToBePlanten, almanacMapByCategory);
    }

    @Test
    void testFindLowestLocationNumber() {
        assertEquals(35, almanac.findLowestLocationNumber());
    }

    @Test
    void testFindLowestLocationNumberForARangeOfSeeds() {
        assertEquals(46, almanac.findLowestLocationNumberForARangeOfSeeds());
    }

    @ParameterizedTest
    @CsvSource({
            "79, 82",
            "14, 43",
            "55, 86",
            "13, 35"
    })
    void testMapSeed(final long seedNumber, final long expectedResult) {
        assertEquals(expectedResult, almanac.mapSeed(seedNumber));
    }

    @ParameterizedTest
    @CsvSource({
            "82, 79",
            "43, 14",
            "86, 55",
            "35, 13"
    })
    void testMapLocation(final long locationNumber, final long expectedResult) {
        assertEquals(expectedResult, almanac.mapLocation(locationNumber));
    }

    @Test
    void testDetermineSeedsToBePlantedRanges() {
        final List<Range<Long>> seedsToBePlantedRanges = almanac.determineSeedsToBePlantedRanges();
        assertTrue(seedsToBePlantedRanges.contains(new Range<>(79L, 92L + 1)));
        assertTrue(seedsToBePlantedRanges.contains(new Range<>(55L, 67L + 1)));
        assertEquals(2, seedsToBePlantedRanges.size());
    }
}