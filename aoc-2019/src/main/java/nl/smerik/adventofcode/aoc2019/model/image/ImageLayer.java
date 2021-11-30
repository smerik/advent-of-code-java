package nl.smerik.adventofcode.aoc2019.model.image;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class ImageLayer {

    private int[][] image;

    public ImageLayer(final String data,
                      final int width,
                      final int height) {

        LOG.info("Creating new image layer for '{}'...", data);
        image = new int[height][width];
        final int[] pixelValues = data.chars().map(Character::getNumericValue).toArray();

        int x = 0;
        int y = 0;
        for (final int pixelValue : pixelValues) {
            image[y][x] = pixelValue;
            x++;
            if (x >= width) {
                x = 0;
                y++;
            }
        }
        LOG.info("Created: {}", this);
    }

    public int getTotalNumberOfDigitsWithValue(final int digit) {
        int result = 0;
        for (final int[] row : image) {
            for (final int i : row) {
                if (i == digit) {
                    result++;
                }
            }
        }
        return result;
    }
}
