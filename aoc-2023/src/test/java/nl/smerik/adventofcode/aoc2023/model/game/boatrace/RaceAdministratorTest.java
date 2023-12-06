package nl.smerik.adventofcode.aoc2023.model.game.boatrace;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceAdministratorTest {

    private static final List<String> INPUT_EXAMPLE = List.of(
            "Time:      7  15   30",
            "Distance:  9  40  200"
    );
    private static RaceAdministrator raceAdministrator;

    @BeforeAll
    static void beforeAll() {
        raceAdministrator = new RaceAdministrator(INPUT_EXAMPLE);
    }

    @Test
    void testGetRaces() {
        assertEquals(3, raceAdministrator.getRaces().size());
    }

    @Test
    void testCalcMarginOfError() {
        assertEquals(288, raceAdministrator.calcMarginOfError());
    }
}