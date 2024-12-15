package nl.smerik.adventofcode.aoc2024.model.bathroom;

import lombok.Data;

import java.awt.*;

@Data
public class Robot {

    private final int width;
    private final int height;

    private Point currentPosition;
    private int velocityX;
    private int velocityY;

    public Robot(final int width,
                 final int height,
                 final Point currentPosition,
                 final int velocityX,
                 final int velocityY) {
        this.width = width;
        this.height = height;
        this.currentPosition = currentPosition;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void move(final int seconds) {
        for (int i = 0; i < seconds; i++) {
            move();
        }
    }

    private void move() {
        this.currentPosition.translate(velocityX, velocityY);
        if (this.currentPosition.x < 0) {
            this.currentPosition.x = currentPosition.x + width;
        } else if (this.currentPosition.x > width - 1) {
            this.currentPosition.x = currentPosition.x - width;
        }
        if (this.currentPosition.y < 0) {
            this.currentPosition.y = currentPosition.y + height;
        } else if (this.currentPosition.y > height - 1) {
            this.currentPosition.y = currentPosition.y - height;
        }
    }
}
