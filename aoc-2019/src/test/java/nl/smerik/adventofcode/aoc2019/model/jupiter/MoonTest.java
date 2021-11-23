package nl.smerik.adventofcode.aoc2019.model.jupiter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoonTest {

    @Test
    void applyGravity() {
        final Moon ganymede = new Moon(3, 0, 0);
        final Moon callisto = new Moon(5, 0, 0);

        ganymede.applyGravity(callisto);

        assertEquals(1, ganymede.getVelocity().x);
        assertEquals(-1, callisto.getVelocity().x);
    }

    @Test
    void applyVelocity() {
        final Moon europa = new Moon(1, 2, 3);
        final Velocity velocity = europa.getVelocity();
        velocity.x = -2;
        velocity.y = 0;
        velocity.z = 3;

        europa.applyVelocity();

        assertEquals(-1, europa.getPosition().x);
        assertEquals(2, europa.getPosition().y);
        assertEquals(6, europa.getPosition().z);
    }

    private static Stream<Arguments> provideSourceForEnergy() {
        return Stream.of(
                Arguments.of(initMoon(2, 1, -3, -3, -2, 1), 6, 6, 36),
                Arguments.of(initMoon(1, -8, 0, -1, 1, 3), 9, 5, 45),
                Arguments.of(initMoon(3, -6, 1, 3, 2, -3), 10, 8, 80),
                Arguments.of(initMoon(2, 0, 4, 1, -1, -1), 6, 3, 18)
        );
    }

    private static Moon initMoon(final int positionX, final int positionY, final int positionZ,
                                 final int velocityX, final int velocityY, final int velocityZ) {
        final Moon result = new Moon(positionX, positionY, positionZ);
        result.getVelocity().x = velocityX;
        result.getVelocity().y = velocityY;
        result.getVelocity().z = velocityZ;
        return result;
    }

    @ParameterizedTest
    @MethodSource("provideSourceForEnergy")
    void getPotentialEnergy(final Moon moon, final int pot, final int kin, final int totalEnergy) {
        assertEquals(pot, moon.getPotentialEnergy());
    }

    @ParameterizedTest
    @MethodSource("provideSourceForEnergy")
    void getKineticEnergy(final Moon moon, final int pot, final int kin, final int totalEnergy) {
        assertEquals(kin, moon.getKineticEnergy());
    }

    @ParameterizedTest
    @MethodSource("provideSourceForEnergy")
    void getTotalEnergy(final Moon moon, final int pot, final int kin, final int totalEnergy) {
        assertEquals(totalEnergy, moon.getTotalEnergy());
    }
}