package nl.smerik.adventofcode.aoc2019.model.spaceship;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;

import java.awt.Point;
import java.util.List;

public class EmergencyHullPaintingRobot {

    private final IntcodeComputer brain;

    private final Hull hull;

    private Point currentLocation;
    private int currentDirectionDegrees;

    private enum FacingDirection {
        RIGHT(0),
        UP(90),
        LEFT(180),
        DOWN(270);

        private final int degrees;

        FacingDirection(final int degrees) {
            this.degrees = degrees;
        }

        public static FacingDirection valueOfDegrees(final int degrees) {
            for (final FacingDirection direction : values()) {
                if (direction.degrees == degrees) {
                    return direction;
                }
            }
            throw new IllegalArgumentException("Cannot return direction for degrees value " + degrees);
        }
    }

    private enum DirectionToTurn {
        LEFT(0, 90),
        RIGHT(1, -90);

        private final int code;
        private final int degrees;

        DirectionToTurn(final int code, final int degrees) {
            this.code = code;
            this.degrees = degrees;
        }

        public static DirectionToTurn valueOfCode(final int code) {
            for (final DirectionToTurn direction : values()) {
                if (direction.code == code) {
                    return direction;
                }
            }
            throw new IllegalArgumentException("Cannot return direction for code value " + code);
        }
    }

    public EmergencyHullPaintingRobot(final Hull hull, final IntcodeComputer brain) {
        this.hull = hull;
        this.brain = brain;
        this.currentDirectionDegrees = FacingDirection.UP.degrees;
        this.currentLocation = new Point(0 ,0);
    }

    public void draw() {
        paintAndMove();
        if (this.brain.isPausedExecution()) {
            draw();
        }
    }

    private void paintAndMove() {
        final int locationColorCode = this.hull.getPanel(this.currentLocation).getCurrentColor().getCode();
        final List<Long> output = this.brain.run(Long.valueOf(locationColorCode));

        final Color colorToPaint = Color.valueOfColorCode(Math.toIntExact(output.get(0)));
        final Panel panel = this.hull.getPanel(currentLocation);
        panel.paint(colorToPaint);

        turn(DirectionToTurn.valueOfCode(Math.toIntExact(output.get(1))));
    }

    private void turn(final DirectionToTurn directionToTurn) {
        currentDirectionDegrees += directionToTurn.degrees;
        if (currentDirectionDegrees < 0) {
            currentDirectionDegrees += 360;
        } else if (currentDirectionDegrees >= 360) {
            currentDirectionDegrees -= 360;
        }

        // After the robot turns, it should always move forward exactly one panel
        move();
    }

    private void move() {
        final int steps = 1;
        final FacingDirection direction = FacingDirection.valueOfDegrees(this.currentDirectionDegrees);
        switch (direction) {
            case RIGHT:
                this.currentLocation = new Point(currentLocation.x + steps, currentLocation.y);
                break;
            case UP:
                this.currentLocation = new Point(currentLocation.x, currentLocation.y + steps);
                break;
            case LEFT:
                this.currentLocation = new Point(currentLocation.x - steps, currentLocation.y);
                break;
            case DOWN:
                this.currentLocation = new Point(currentLocation.x, currentLocation.y - steps);
                break;
            default:
                throw new IllegalStateException("Not implemented move: " + direction);
        }
    }
}
