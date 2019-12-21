package nl.smerik.adventofcode.aoc2019.model.jupiter;

import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Getter
@ToString
public class Moon {

    private static final Logger LOGGER = LoggerFactory.getLogger(Moon.class);

    private Position position;
    private Velocity velocity;

    public Moon(final int x, final int y, final int z) {
        position = new Position(x, y, z);
        velocity = new Velocity(0, 0, 0);
    }

    public void applyGravity(final Moon moon) {
//        LOGGER.debug("Apply gravity between {} and {}...", this, moon);
        if (position.x > moon.position.x) {
            velocity.x--;
            moon.velocity.x++;
        } else if (position.x < moon.position.x) {
            velocity.x++;
            moon.velocity.x--;
        }

        if (position.y > moon.position.y) {
            velocity.y--;
            moon.velocity.y++;
        } else if (position.y < moon.position.y) {
            velocity.y++;
            moon.velocity.y--;
        }

        if (position.z > moon.position.z) {
            velocity.z--;
            moon.velocity.z++;
        } else if (position.z < moon.position.z) {
            velocity.z++;
            moon.velocity.z--;
        }
//        LOGGER.debug("Gravity applied between {} and {}.", this, moon);
    }

    public void applyVelocity() {
        position.x += velocity.x;
        position.y += velocity.y;
        position.z += velocity.z;
    }

    public int getTotalEnergy() {
        return getPotentialEnergy() * getKineticEnergy();
    }

    /**
     * Gets the potential energy (a.k.a. pot)
     * which is the sum of the absolute values of the x, y, and z position coordinates.
     *
     * @return the potential energy
     */
    public int getPotentialEnergy() {
        return Math.abs(position.x) + Math.abs(position.y) + Math.abs(position.z);
    }

    /**
     * Gets the kinetic energy (a.k.a. kin)
     * which is the sum of the absolute values of the velocity coordinates.
     *
     * @return the kinetic energy
     */
    public int getKineticEnergy() {
        return Math.abs(velocity.x) + Math.abs(velocity.y) + Math.abs(velocity.z);
    }

    public int hashState() {
        return Objects.hash(this, position, velocity);
    }
}
