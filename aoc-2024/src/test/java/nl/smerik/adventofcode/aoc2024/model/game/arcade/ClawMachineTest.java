package nl.smerik.adventofcode.aoc2024.model.game.arcade;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ClawMachineTest {

    public static Stream<Arguments> provideSourceForDetermineMinTokensToPossiblePrize() {
        return Stream.of(
                //@formatter:off
                Arguments.of(280, new Button(94, 34, 3), new Button(22, 67, 1), new Point( 8400,  5400)),
                Arguments.of(  0, new Button(26, 66, 3), new Button(67, 21, 1), new Point(12748, 12176)),
                Arguments.of(200, new Button(17, 86, 3), new Button(84, 37, 1), new Point( 7870,  6450)),
                Arguments.of(  0, new Button(69, 23, 3), new Button(27, 71, 1), new Point(18641, 10279))
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForDetermineMinTokensToPossiblePrize")
    void testDetermineMinTokensToPossiblePrize(final int expectedResult,
                                               final Button buttonA,
                                               final Button buttonB,
                                               final Point prizeLocation) {
        final ClawMachine machine = new ClawMachine(buttonA, buttonB, prizeLocation);
        assertEquals(expectedResult, machine.determineMinTokensToPossiblePrize());
    }
}