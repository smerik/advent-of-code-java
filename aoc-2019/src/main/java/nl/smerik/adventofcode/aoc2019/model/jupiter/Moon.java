package nl.smerik.adventofcode.aoc2019.model.jupiter;

import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class Moon {

    private static final Pattern POSITION_PATTERN = Pattern.compile("<x=(?<x>-?\\d+), y=(?<y>-?\\d+), z=(?<z>-?\\d+)>");

    private final Position position;
    private final Velocity velocity;

    public Moon(final int x, final int y, final int z) {
        position = new Position(x, y, z);
        velocity = new Velocity();
    }

    public Moon(final String position) {
        this.position = parsePosition(position);
        this.velocity = new Velocity();
    }

    private Position parsePosition(final String position) {
        final Matcher matcher = POSITION_PATTERN.matcher(position);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Unknown position line '" + position + "'");
        }
        final int x = Integer.parseInt(matcher.group("x"));
        final int y = Integer.parseInt(matcher.group("y"));
        final int z = Integer.parseInt(matcher.group("z"));
        return new Position(x, y, z);
    }

    /**
     * Applies gravity to given moon and this moon.
     * On each axis (x, y, and z), the velocity of each moon changes by exactly +1 or -1 to pull the moons together.
     *
     * @param moon the moon to apply gravity to
     */
    public void applyGravity(final Moon moon) {
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
}
