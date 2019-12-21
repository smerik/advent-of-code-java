package nl.smerik.adventofcode.aoc2019.model.image;

import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@ToString
public class ImageLayer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageLayer.class);

    private int[][] image;

    public ImageLayer(final String data,
                      final int width,
                      final int height) {

        LOGGER.info("Creating new image layer for '{}'...", data);
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
        LOGGER.info("Created: {}", this);
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
