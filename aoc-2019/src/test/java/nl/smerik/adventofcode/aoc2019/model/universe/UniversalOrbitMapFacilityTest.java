package nl.smerik.adventofcode.aoc2019.model.universe;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniversalOrbitMapFacilityTest {

    @Test
    void countTotalNumberOfOrbits() {
        final UniversalOrbitMapFacility mapFacility = createUniversalOrbitMapFacility();

        assertEquals(42, mapFacility.countTotalNumberOfOrbits());
    }

    @Test
    void countTotalNumberOfOrbitsForSpaceObject() {
        final UniversalOrbitMapFacility mapFacility = createUniversalOrbitMapFacility();

        assertEquals(3, mapFacility.countTotalNumberOfOrbits("D"));
        assertEquals(7, mapFacility.countTotalNumberOfOrbits("L"));
        assertEquals(0, mapFacility.countTotalNumberOfOrbits("COM"));
    }

    private UniversalOrbitMapFacility createUniversalOrbitMapFacility() {
        final UniversalOrbitMapFacility mapFacility = new UniversalOrbitMapFacility();
        mapFacility.addSpaceObject("COM", "B");
        mapFacility.addSpaceObject("B", "C");
        mapFacility.addSpaceObject("C", "D");
        mapFacility.addSpaceObject("D", "E");
        mapFacility.addSpaceObject("E", "F");
        mapFacility.addSpaceObject("B", "G");
        mapFacility.addSpaceObject("G", "H");
        mapFacility.addSpaceObject("D", "I");
        mapFacility.addSpaceObject("E", "J");
        mapFacility.addSpaceObject("J", "K");
        mapFacility.addSpaceObject("K", "L");
        return mapFacility;
    }

    @Test
    void getOrbitalTransfersToCOM() {
        final List<String> expected = Arrays.asList("YOU", "K", "J", "E", "D", "C", "B", "COM");
        final UniversalOrbitMapFacility mapFacility = createUniversalOrbitMapFacilityPart2();

        assertEquals(expected, mapFacility.getOrbitalTransfersToCOM("YOU"));
    }

    @Test
    void testGetMinimumNumberOfOrbitalTransfersRequired() {
        final UniversalOrbitMapFacility mapFacility = createUniversalOrbitMapFacilityPart2();
        assertEquals(4, mapFacility.countMinimumNumberOfTransfersRequired("YOU", "SAN"));
    }

    private UniversalOrbitMapFacility createUniversalOrbitMapFacilityPart2() {
        final UniversalOrbitMapFacility mapFacility = new UniversalOrbitMapFacility();
        mapFacility.addSpaceObject("COM", "B");
        mapFacility.addSpaceObject("B", "C");
        mapFacility.addSpaceObject("C", "D");
        mapFacility.addSpaceObject("D", "E");
        mapFacility.addSpaceObject("E", "F");
        mapFacility.addSpaceObject("B", "G");
        mapFacility.addSpaceObject("G", "H");
        mapFacility.addSpaceObject("D", "I");
        mapFacility.addSpaceObject("E", "J");
        mapFacility.addSpaceObject("J", "K");
        mapFacility.addSpaceObject("K", "L");
        mapFacility.addSpaceObject("K", "YOU");
        mapFacility.addSpaceObject("I", "SAN");
        return mapFacility;
    }
}