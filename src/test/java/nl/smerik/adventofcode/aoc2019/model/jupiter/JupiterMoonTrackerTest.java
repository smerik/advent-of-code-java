package nl.smerik.adventofcode.aoc2019.model.jupiter;

import lombok.ToString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JupiterMoonTrackerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JupiterMoonTrackerTest.class);

    private static JupiterMoonTracker trackerExample01;
    private static JupiterMoonTracker trackerExample02;

    @BeforeAll
    static void initAll() {
        final List<Moon> moonsExample01 = Arrays.asList(
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)
        );
        trackerExample01 = new JupiterMoonTracker(moonsExample01);

        final List<Moon> moonsExample02 = Arrays.asList(
                new Moon(-8, -10, 0),
                new Moon(5, 5, 10),
                new Moon(2, -7, 3),
                new Moon(9, -8, -3)
        );
        trackerExample02 = new JupiterMoonTracker(moonsExample02);
    }

    private static Stream<Arguments> provideSourceForSimulateMotionExample01() {
        return Stream.of(
                // After 1 step
                Arguments.of(Arrays.asList(
                        new PositionVelocity(2, -1, 1, 3, -1, -1),
                        new PositionVelocity(3, -7, -4, 1, 3, 3),
                        new PositionVelocity(1, -7, 5, -3, 1, -3),
                        new PositionVelocity(2, 2, 0, -1, -3, 1)
                )),
                // After 2 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(5, -3, -1, 3, -2, -2),
                        new PositionVelocity(1, -2, 2, -2, 5, 6),
                        new PositionVelocity(1, -4, -1, 0, 3, -6),
                        new PositionVelocity(1, -4, 2, -1, -6, 2)
                )),
                // After 3 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(5, -6, -1, 0, -3, 0),
                        new PositionVelocity(0, 0, 6, -1, 2, 4),
                        new PositionVelocity(2, 1, -5, 1, 5, -4),
                        new PositionVelocity(1, -8, 2, 0, -4, 0)
                )),
                // After 4 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(2, -8, 0, -3, -2, 1),
                        new PositionVelocity(2, 1, 7, 2, 1, 1),
                        new PositionVelocity(2, 3, -6, 0, 2, -1),
                        new PositionVelocity(2, -9, 1, 1, -1, -1)
                )),
                // After 5 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(-1, -9, 2, -3, -1, 2),
                        new PositionVelocity(4, 1, 5, 2, 0, -2),
                        new PositionVelocity(2, 2, -4, 0, -1, 2),
                        new PositionVelocity(3, -7, -1, 1, 2, -2)
                )),
                // After 6 steps
                Arguments.of(
                        Arrays.asList(
                                new PositionVelocity(-1, -7, 3, 0, 2, 1),
                                new PositionVelocity(3, 0, 0, -1, -1, -5),
                                new PositionVelocity(3, -2, 1, 1, -4, 5),
                                new PositionVelocity(3, -4, -2, 0, 3, -1)
                        )),
                // After 7 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(2, -2, 1, 3, 5, -2),
                        new PositionVelocity(1, -4, -4, -2, -4, -4),
                        new PositionVelocity(3, -7, 5, 0, -5, 4),
                        new PositionVelocity(2, 0, 0, -1, 4, 2)
                )),
                // After 8 steps
                Arguments.of(
                        Arrays.asList(
                                new PositionVelocity(5, 2, -2, 3, 4, -3),
                                new PositionVelocity(2, -7, -5, 1, -3, -1),
                                new PositionVelocity(0, -9, 6, -3, -2, 1),
                                new PositionVelocity(1, 1, 3, -1, 1, 3)
                        )),
                // After 9 steps
                Arguments.of(
                        Arrays.asList(
                                new PositionVelocity(5, 3, -4, 0, 1, -2),
                                new PositionVelocity(2, -9, -3, 0, -2, 2),
                                new PositionVelocity(0, -8, 4, 0, 1, -2),
                                new PositionVelocity(1, 1, 5, 0, 0, 2)
                        )),
                // After 10 steps
                Arguments.of(
                        Arrays.asList(
                                new PositionVelocity(2, 1, -3, -3, -2, 1),
                                new PositionVelocity(1, -8, 0, -1, 1, 3),
                                new PositionVelocity(3, -6, 1, 3, 2, -3),
                                new PositionVelocity(2, 0, 4, 1, -1, -1)
                        )
                )
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

    @Disabled
    @ParameterizedTest()
    @MethodSource("provideSourceForSimulateMotionExample01")
    void simulateMotionExample01(final List<PositionVelocity> positionsVelocities) {
        trackerExample01.simulateMotion(1L);

        assertEquals(positionsVelocities.get(0).position, trackerExample01.getMoons().get(0).getPosition());
        assertEquals(positionsVelocities.get(0).velocity, trackerExample01.getMoons().get(0).getVelocity());

        assertEquals(positionsVelocities.get(1).position, trackerExample01.getMoons().get(1).getPosition());
        assertEquals(positionsVelocities.get(1).velocity, trackerExample01.getMoons().get(1).getVelocity());

        assertEquals(positionsVelocities.get(2).position, trackerExample01.getMoons().get(2).getPosition());
        assertEquals(positionsVelocities.get(2).velocity, trackerExample01.getMoons().get(2).getVelocity());

        assertEquals(positionsVelocities.get(3).position, trackerExample01.getMoons().get(3).getPosition());
        assertEquals(positionsVelocities.get(3).velocity, trackerExample01.getMoons().get(3).getVelocity());
    }

    private static Stream<Arguments> provideSourceForSimulateMotionExample02() {
        return Stream.of(
                // After 10 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(-9, -10, 1, -2, -2, -1),
                        new PositionVelocity(4, 10, 9, -3, 7, -2),
                        new PositionVelocity(8, -10, -3, 5, -1, -2),
                        new PositionVelocity(5, -10, 3, 0, -4, 5)

                )),
                // After 20 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(-10, 3, -4, -5, 2, 0),
                        new PositionVelocity(5, -25, 6, 1, 1, -4),
                        new PositionVelocity(13, 1, 1, 5, -2, 2),
                        new PositionVelocity(0, 1, 7, -1, -1, 2)

                )),
                // After 30 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(15, -6, -9, -5, 4, 0),
                        new PositionVelocity(-4, -11, 3, -3, -10, 0),
                        new PositionVelocity(0, -1, 11, 7, 4, 3),
                        new PositionVelocity(-3, -2, 5, 1, 2, -3)

                )),
                // After 40 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(14, -12, -4, 11, 3, 0),
                        new PositionVelocity(-1, 18, 8, -5, 2, 3),
                        new PositionVelocity(-5, -14, 8, 1, -2, 0),
                        new PositionVelocity(0, -12, -2, -7, -3, -3)

                )),
                // After 50 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(-23, 4, 1, -7, -1, 2),
                        new PositionVelocity(20, -31, 13, 5, 3, 4),
                        new PositionVelocity(-4, 6, 1, -1, 1, -3),
                        new PositionVelocity(15, 1, -5, 3, -3, -3)

                )),
                // After 60 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(36, -10, 6, 5, 0, 3),
                        new PositionVelocity(-18, 10, 9, -3, -7, 5),
                        new PositionVelocity(8, -12, -3, -2, 1, -7),
                        new PositionVelocity(-18, -8, -2, 0, 6, -1)

                )),
                // After 70 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(-33, -6, 5, -5, -4, 7),
                        new PositionVelocity(13, -9, 2, -2, 11, 3),
                        new PositionVelocity(11, -8, 2, 8, -6, -7),
                        new PositionVelocity(17, 3, 1, -1, -1, -3)

                )),
                // After 80 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(30, -8, 3, 3, 3, 0),
                        new PositionVelocity(-2, -4, 0, 4, -13, 2),
                        new PositionVelocity(-18, -7, 15, -8, 2, -2),
                        new PositionVelocity(-2, -1, -8, 1, 8, 0)

                )),
                // After 90 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(-25, -1, 4, 1, -3, 4),
                        new PositionVelocity(2, -9, 0, -3, 13, -1),
                        new PositionVelocity(32, -8, 14, 5, -4, 6),
                        new PositionVelocity(-1, -2, -8, -3, -6, -9)

                )),
                // After 100 steps
                Arguments.of(Arrays.asList(
                        new PositionVelocity(8, -12, -9, -7, 3, 0),
                        new PositionVelocity(13, 16, -3, 3, -11, -5),
                        new PositionVelocity(-29, -11, -1, -3, 7, 4),
                        new PositionVelocity(16, -13, 23, 7, 1, 1)
                ))
        );
    }


    @ParameterizedTest
    @MethodSource("provideSourceForSimulateMotionExample02")
    void simulateMotionExample02(final List<PositionVelocity> positionsVelocities) {
        trackerExample02.simulateMotion(10L);

        assertEquals(positionsVelocities.get(0).position, trackerExample02.getMoons().get(0).getPosition());
        assertEquals(positionsVelocities.get(0).velocity, trackerExample02.getMoons().get(0).getVelocity());

        assertEquals(positionsVelocities.get(1).position, trackerExample02.getMoons().get(1).getPosition());
        assertEquals(positionsVelocities.get(1).velocity, trackerExample02.getMoons().get(1).getVelocity());

        assertEquals(positionsVelocities.get(2).position, trackerExample02.getMoons().get(2).getPosition());
        assertEquals(positionsVelocities.get(2).velocity, trackerExample02.getMoons().get(2).getVelocity());

        assertEquals(positionsVelocities.get(3).position, trackerExample02.getMoons().get(3).getPosition());
        assertEquals(positionsVelocities.get(3).velocity, trackerExample02.getMoons().get(3).getVelocity());
    }

    @Test
    void getTotalEnergy() {
        final List<Moon> moonsExample01 = Arrays.asList(
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)
        );
        final JupiterMoonTracker example01 = new JupiterMoonTracker(moonsExample01);
        example01.simulateMotion(10L);
        assertEquals(179, example01.getTotalEnergy());

        final List<Moon> moonsExample02 = Arrays.asList(
                new Moon(-8, -10, 0),
                new Moon(5, 5, 10),
                new Moon(2, -7, 3),
                new Moon(9, -8, -3)
        );
        final JupiterMoonTracker example02 = new JupiterMoonTracker(moonsExample02);
        example02.simulateMotion(100L);
        assertEquals(1940, example02.getTotalEnergy());
    }

    @Test
    void doesHistoryRepeatItself() {
        final List<Moon> moonsExample01 = Arrays.asList(
                new Moon(-1, 0, 2),
                new Moon(2, -10, -7),
                new Moon(4, -8, 8),
                new Moon(3, 5, -1)
        );
        final JupiterMoonTracker example01 = new JupiterMoonTracker(moonsExample01);
//        while (statesDoesNotContainDuplicates(example01)) {
//            example01.simulateMotion(1L);
//        }
        example01.simulateMotion(2772L);
        LOGGER.info("tracker.currentStep():{}", example01.getCurrentStep());


//        final List<Moon> moonsExample02 = Arrays.asList(
//                new Moon(-8, -10, 0),
//                new Moon(5, 5, 10),
//                new Moon(2, -7, 3),
//                new Moon(9, -8, -3)
//        );
//        final JupiterMoonTracker example02 = new JupiterMoonTracker(moonsExample02);
////        while (statesDoesNotContainDuplicates(example02)) {
////            example02.simulateMotion(1);
////        }
//        example02.simulateMotion(4686774924L);
//        LOGGER.info("tracker.currentStep():{}", example02.getCurrentStep());
    }




    @Test
    void getDay12SolutionPart01() {
        final List<Moon> moons = Arrays.asList(
                new Moon(10, 15, 7),
                new Moon(15, 10, 0),
                new Moon(20, 12, 3),
                new Moon(0, -3, 13)
        );
        final JupiterMoonTracker part01 = new JupiterMoonTracker(moons);
        part01.simulateMotion(1000L);

        LOGGER.info("Total energy:{}", part01.getTotalEnergy());
    }

    @Test
    void getDay12SolutionPart02() {
        final List<Moon> moons = Arrays.asList(
                new Moon(10, 15, 7),
                new Moon(15, 10, 0),
                new Moon(20, 12, 3),
                new Moon(0, -3, 13)
        );
        final JupiterMoonTracker tracker = new JupiterMoonTracker(moons);
        while (statesDoesNotContainDuplicates(tracker)) {
            tracker.simulateMotion(1L);
        }
        // Answer should be 478373365921244
        LOGGER.info("tracker.currentStep():{}", tracker.getCurrentStep());
    }

    private boolean statesDoesNotContainDuplicates(final JupiterMoonTracker tracker) {
        for (List<Integer> value : tracker.getStates().values()) {
            if (value.size() > 1) {
                return false;
            }
        }
        return true;
    }
}