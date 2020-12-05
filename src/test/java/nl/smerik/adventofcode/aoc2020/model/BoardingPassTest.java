package nl.smerik.adventofcode.aoc2020.model;

import nl.smerik.adventofcode.aoc2020.model.boarding.BoardingPass;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardingPassTest {

    private static Stream<Arguments> provideSourceForSeatLocation() {
        return Stream.of(
                Arguments.of("FBFBBFFRLR", 44, 5, 357),
                Arguments.of("BFFFBBFRRR", 70, 7, 567),
                Arguments.of("FFFBBBFRRR", 14, 7, 119),
                Arguments.of("BBFFBBFRLL", 102, 4, 820)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForSeatLocation")
    void seatLocation(final String passID, final int row, final int column, final int seatID) {
        final BoardingPass pass = new BoardingPass(passID);
        assertEquals(row, pass.getRow());
        assertEquals(column, pass.getColumn());
        assertEquals(seatID, pass.getSeatID());
    }
}