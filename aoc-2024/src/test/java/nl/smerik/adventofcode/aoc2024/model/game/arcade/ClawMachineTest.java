package nl.smerik.adventofcode.aoc2024.model.game.arcade;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.geom.Point2D;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ClawMachineTest {

    private static Stream<Arguments> provideSourceForDetermineMinTokensToPossiblePrize() {
        return Stream.of(
                //@formatter:off
                // part 1
                Arguments.of(280, new Button(94, 34, 3), new Button(22, 67, 1), new Point2D.Double( 8400,  5400)),
                Arguments.of(  0, new Button(26, 66, 3), new Button(67, 21, 1), new Point2D.Double(12748, 12176)),
                Arguments.of(200, new Button(17, 86, 3), new Button(84, 37, 1), new Point2D.Double( 7870,  6450)),
                Arguments.of(  0, new Button(69, 23, 3), new Button(27, 71, 1), new Point2D.Double(18641, 10279)),
                // part 2
                Arguments.of(           0L, new Button(94, 34, 3), new Button(22, 67, 1), new Point2D.Double(10000000008400d, 10000000005400d)),
                Arguments.of(459236326669L, new Button(26, 66, 3), new Button(67, 21, 1), new Point2D.Double(10000000012748d, 10000000012176d)),
                Arguments.of(           0L, new Button(17, 86, 3), new Button(84, 37, 1), new Point2D.Double(10000000007870d, 10000000006450d)),
                Arguments.of(416082282239L, new Button(69, 23, 3), new Button(27, 71, 1), new Point2D.Double(10000000018641d, 10000000010279d))
                //@formatter:on
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForDetermineMinTokensToPossiblePrize")
    void testDetermineMinTokensToPossiblePrize(final long expectedResult,
                                               final Button buttonA,
                                               final Button buttonB,
                                               final Point2D.Double prizeLocation) {
        final ClawMachine machine = new ClawMachine(buttonA, buttonB, prizeLocation);
        assertEquals(expectedResult, machine.determineMinTokensToPossiblePrize());
    }
}