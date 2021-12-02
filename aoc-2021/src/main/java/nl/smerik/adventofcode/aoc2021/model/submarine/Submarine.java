package nl.smerik.adventofcode.aoc2021.model.submarine;

import lombok.Getter;
import nl.smerik.adventofcode.geom.Point3D;

import java.util.List;

public class Submarine {

    private final Point3D position;

    @Getter
    private int aim;

    public Submarine() {
        this.position = new Point3D();
        this.aim = 0;
    }

    public void move(final List<String> commands) {
        for (final String command : commands) {
            move(command);
        }
    }

    public void move(final String command) {
        final String[] commandParameters = command.split(" ");
        final String direction = commandParameters[0];
        final int movingDistance = Integer.parseInt(commandParameters[1]);

        switch (direction) {
            case "forward" -> position.translate(movingDistance, 0, 0);
            case "down" -> position.translate(0, 0, -movingDistance);
            case "up" -> position.translate(0, 0, movingDistance);
            default -> throw new IllegalArgumentException("Unknown direction '" + direction + "'");
        }
    }

    public void moveByAim(final List<String> commands) {
        for (final String command : commands) {
            moveByAim(command);
        }
    }

    public void moveByAim(final String command) {
        final String[] commandParameters = command.split(" ");
        final String direction = commandParameters[0];
        final int movingDistance = Integer.parseInt(commandParameters[1]);

        switch (direction) {
            case "forward" -> moveForwardByAim(movingDistance);
            case "down" -> aim += movingDistance;
            case "up" -> aim -= movingDistance;
            default -> throw new IllegalArgumentException("Unknown direction '" + direction + "'");
        }
    }

    private void moveForwardByAim(final int movingDistance) {
        this.position.translate(movingDistance, 0, aim * -movingDistance);
    }

    public int getHorizontalPosition() {
        return this.position.getX();
    }

    public int getDepth() {
        return -this.position.getZ();
    }

    public int calculateSolutionDay2() {
        return this.position.getX() * -this.position.getZ();
    }
}
