package nl.smerik.adventofcode.aoc2019.model.jupiter;

import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JupiterMoonTrackerTest {

    private static Stream<Arguments> provideSourceForSimulateMotionExample01() {
        return Stream.of(
                // After 1 step
                Arguments.of(1, List.of(
                        new PositionVelocity(2, -1, 1, 3, -1, -1),
                        new PositionVelocity(3, -7, -4, 1, 3, 3),
                        new PositionVelocity(1, -7, 5, -3, 1, -3),
                        new PositionVelocity(2, 2, 0, -1, -3, 1)
                )),
                // After 2 steps
                Arguments.of(2, List.of(
                        new PositionVelocity(5, -3, -1, 3, -2, -2),
                        new PositionVelocity(1, -2, 2, -2, 5, 6),
                        new PositionVelocity(1, -4, -1, 0, 3, -6),
                        new PositionVelocity(1, -4, 2, -1, -6, 2)
                )),
                // After 3 steps
                Arguments.of(3, List.of(
                        new PositionVelocity(5, -6, -1, 0, -3, 0),
                        new PositionVelocity(0, 0, 6, -1, 2, 4),
                        new PositionVelocity(2, 1, -5, 1, 5, -4),
                        new PositionVelocity(1, -8, 2, 0, -4, 0)
                )),
                // After 4 steps
                Arguments.of(4, List.of(
                        new PositionVelocity(2, -8, 0, -3, -2, 1),
                        new PositionVelocity(2, 1, 7, 2, 1, 1),
                        new PositionVelocity(2, 3, -6, 0, 2, -1),
                        new PositionVelocity(2, -9, 1, 1, -1, -1)
                )),
                // After 5 steps
                Arguments.of(5, List.of(
                        new PositionVelocity(-1, -9, 2, -3, -1, 2),
                        new PositionVelocity(4, 1, 5, 2, 0, -2),
                        new PositionVelocity(2, 2, -4, 0, -1, 2),
                        new PositionVelocity(3, -7, -1, 1, 2, -2)
                )),
                // After 6 steps
                Arguments.of(6, List.of(
                        new PositionVelocity(-1, -7, 3, 0, 2, 1),
                        new PositionVelocity(3, 0, 0, -1, -1, -5),
                        new PositionVelocity(3, -2, 1, 1, -4, 5),
                        new PositionVelocity(3, -4, -2, 0, 3, -1)
                )),
                // After 7 steps
                Arguments.of(7, List.of(
                        new PositionVelocity(2, -2, 1, 3, 5, -2),
                        new PositionVelocity(1, -4, -4, -2, -4, -4),
                        new PositionVelocity(3, -7, 5, 0, -5, 4),
                        new PositionVelocity(2, 0, 0, -1, 4, 2)
                )),
                // After 8 steps
                Arguments.of(8, List.of(
                        new PositionVelocity(5, 2, -2, 3, 4, -3),
                        new PositionVelocity(2, -7, -5, 1, -3, -1),
                        new PositionVelocity(0, -9, 6, -3, -2, 1),
                        new PositionVelocity(1, 1, 3, -1, 1, 3)
                )),
                // After 9 steps
                Arguments.of(9, List.of(
                        new PositionVelocity(5, 3, -4, 0, 1, -2),
                        new PositionVelocity(2, -9, -3, 0, -2, 2),
                        new PositionVelocity(0, -8, 4, 0, 1, -2),
                        new PositionVelocity(1, 1, 5, 0, 0, 2)
                )),
                // After 10 steps
                Arguments.of(10, List.of(
                        new PositionVelocity(2, 1, -3, -3, -2, 1),
                        new PositionVelocity(1, -8, 0, -1, 1, 3),
                        new PositionVelocity(3, -6, 1, 3, 2, -3),
                        new PositionVelocity(2, 0, 4, 1, -1, -1)
                ))
        );
    }

    @ToString
    static class PositionVelocity {

        public Position position;
        public Velocity velocity;

        public PositionVelocity(final int positionX, final int positionY, final int positionZ,
                                final int velocityX, final int velocityY, final int velocityZ) {
            this.position = new Position(positionX, positionY, positionZ);
            this.velocity = new Velocity(velocityX, velocityY, velocityZ);
        }
    }

    @ParameterizedTest
    @MethodSource("provideSourceForSimulateMotionExample01")
    void testSimulateMotionExample01(final long steps, final List<PositionVelocity> positionsVelocities) {
        final JupiterMoonTracker trackerExample = createTrackerExample01();
        trackerExample.simulateMotion(steps);

        assertEquals(positionsVelocities.get(0).position, trackerExample.getMoons().get(0).getPosition());
        assertEquals(positionsVelocities.get(0).velocity, trackerExample.getMoons().get(0).getVelocity());

        assertEquals(positionsVelocities.get(1).position, trackerExample.getMoons().get(1).getPosition());
        assertEquals(positionsVelocities.get(1).velocity, trackerExample.getMoons().get(1).getVelocity());

        assertEquals(positionsVelocities.get(2).position, trackerExample.getMoons().get(2).getPosition());
        assertEquals(positionsVelocities.get(2).velocity, trackerExample.getMoons().get(2).getVelocity());

        assertEquals(positionsVelocities.get(3).position, trackerExample.getMoons().get(3).getPosition());
        assertEquals(positionsVelocities.get(3).velocity, trackerExample.getMoons().get(3).getVelocity());
    }


    private static Stream<Arguments> provideSourceForSimulateMotionExample02() {
        return Stream.of(
                // After 10 steps
                Arguments.of(10, List.of(
                        new PositionVelocity(-9, -10, 1, -2, -2, -1),
                        new PositionVelocity(4, 10, 9, -3, 7, -2),
                        new PositionVelocity(8, -10, -3, 5, -1, -2),
                        new PositionVelocity(5, -10, 3, 0, -4, 5)

                )),
                // After 20 steps
                Arguments.of(20, List.of(
                        new PositionVelocity(-10, 3, -4, -5, 2, 0),
                        new PositionVelocity(5, -25, 6, 1, 1, -4),
                        new PositionVelocity(13, 1, 1, 5, -2, 2),
                        new PositionVelocity(0, 1, 7, -1, -1, 2)

                )),
                // After 30 steps
                Arguments.of(30, List.of(
                        new PositionVelocity(15, -6, -9, -5, 4, 0),
                        new PositionVelocity(-4, -11, 3, -3, -10, 0),
                        new PositionVelocity(0, -1, 11, 7, 4, 3),
                        new PositionVelocity(-3, -2, 5, 1, 2, -3)

                )),
                // After 40 steps
                Arguments.of(40, List.of(
                        new PositionVelocity(14, -12, -4, 11, 3, 0),
                        new PositionVelocity(-1, 18, 8, -5, 2, 3),
                        new PositionVelocity(-5, -14, 8, 1, -2, 0),
                        new PositionVelocity(0, -12, -2, -7, -3, -3)

                )),
                // After 50 steps
                Arguments.of(50, List.of(
                        new PositionVelocity(-23, 4, 1, -7, -1, 2),
                        new PositionVelocity(20, -31, 13, 5, 3, 4),
                        new PositionVelocity(-4, 6, 1, -1, 1, -3),
                        new PositionVelocity(15, 1, -5, 3, -3, -3)

                )),
                // After 60 steps
                Arguments.of(60, List.of(
                        new PositionVelocity(36, -10, 6, 5, 0, 3),
                        new PositionVelocity(-18, 10, 9, -3, -7, 5),
                        new PositionVelocity(8, -12, -3, -2, 1, -7),
                        new PositionVelocity(-18, -8, -2, 0, 6, -1)

                )),
                // After 70 steps
                Arguments.of(70, List.of(
                        new PositionVelocity(-33, -6, 5, -5, -4, 7),
                        new PositionVelocity(13, -9, 2, -2, 11, 3),
                        new PositionVelocity(11, -8, 2, 8, -6, -7),
                        new PositionVelocity(17, 3, 1, -1, -1, -3)

                )),
                // After 80 steps
                Arguments.of(80, List.of(
                        new PositionVelocity(30, -8, 3, 3, 3, 0),
                        new PositionVelocity(-2, -4, 0, 4, -13, 2),
                        new PositionVelocity(-18, -7, 15, -8, 2, -2),
                        new PositionVelocity(-2, -1, -8, 1, 8, 0)

                )),
                // After 90 steps
                Arguments.of(90, List.of(
                        new PositionVelocity(-25, -1, 4, 1, -3, 4),
                        new PositionVelocity(2, -9, 0, -3, 13, -1),
                        new PositionVelocity(32, -8, 14, 5, -4, 6),
                        new PositionVelocity(-1, -2, -8, -3, -6, -9)

                )),
                // After 100 steps
                Arguments.of(100, List.of(
                        new PositionVelocity(8, -12, -9, -7, 3, 0),
                        new PositionVelocity(13, 16, -3, 3, -11, -5),
                        new PositionVelocity(-29, -11, -1, -3, 7, 4),
                        new PositionVelocity(16, -13, 23, 7, 1, 1)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSourceForSimulateMotionExample02")
    void testSimulateMotionExample02(final long steps, final List<PositionVelocity> positionsVelocities) {
        final JupiterMoonTracker trackerExample = createTrackerExample02();
        trackerExample.simulateMotion(steps);

        assertEquals(positionsVelocities.get(0).position, trackerExample.getMoons().get(0).getPosition());
        assertEquals(positionsVelocities.get(0).velocity, trackerExample.getMoons().get(0).getVelocity());

        assertEquals(positionsVelocities.get(1).position, trackerExample.getMoons().get(1).getPosition());
        assertEquals(positionsVelocities.get(1).velocity, trackerExample.getMoons().get(1).getVelocity());

        assertEquals(positionsVelocities.get(2).position, trackerExample.getMoons().get(2).getPosition());
        assertEquals(positionsVelocities.get(2).velocity, trackerExample.getMoons().get(2).getVelocity());

        assertEquals(positionsVelocities.get(3).position, trackerExample.getMoons().get(3).getPosition());
        assertEquals(positionsVelocities.get(3).velocity, trackerExample.getMoons().get(3).getVelocity());
    }

    @Test
    void testSimulateToSameState() {
        final JupiterMoonTracker example01 = createTrackerExample01();
        assertEquals(2772, example01.simulateStepsToSameState());

        final JupiterMoonTracker example02 = createTrackerExample02();
        assertEquals(4686774924L, example02.simulateStepsToSameState());
    }

    @Test
    void getTotalEnergy() {
        final JupiterMoonTracker example01 = createTrackerExample01();
        example01.simulateMotion(10);
        assertEquals(179, example01.getTotalEnergy());

        final JupiterMoonTracker example02 = createTrackerExample02();
        example02.simulateMotion(100);
        assertEquals(1940, example02.getTotalEnergy());
    }


    private JupiterMoonTracker createTrackerExample01() {
        final List<Moon> moons = List.of(
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)
        );
        return new JupiterMoonTracker(moons);
    }

    private JupiterMoonTracker createTrackerExample02() {
        final List<Moon> moons = List.of(
                new Moon(-8, -10, 0),
                new Moon(5, 5, 10),
                new Moon(2, -7, 3),
                new Moon(9, -8, -3)
        );
        return new JupiterMoonTracker(moons);
    }
}