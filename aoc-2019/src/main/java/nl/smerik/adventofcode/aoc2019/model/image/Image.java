package nl.smerik.adventofcode.aoc2019.model.image;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class Image {

    private static final int PIXEL_COLOR_BLACK = 0;
    private static final int PIXEL_COLOR_WHITE = 1;
    private static final int PIXEL_COLOR_TRANSPARENT = 2;

    private List<ImageLayer> layers;
    private String[][] pixels;

    public Image(final String imageData,
                 final int width,
                 final int height) {

        layers = new ArrayList<>();
        parseImageData(imageData, width, height);
        decodeImage(width, height);
    }

    private void parseImageData(final String imageData, final int width, final int height) {
        final int pixelsPerLayer = width * height;
        final String[] imageLayers = imageData.split("(?<=\\G.{" + pixelsPerLayer + "})");
        for (final String imageLayer : imageLayers) {
            layers.add(new ImageLayer(imageLayer, width, height));
        }
    }

    private void decodeImage(final int width, final int height) {
        pixels = new String[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (final ImageLayer imageLayer : layers) {
                    int pixelColor = imageLayer.getImage()[y][x];
                    if (pixelColor == PIXEL_COLOR_TRANSPARENT) {
                        continue;
                    }
                    if (pixelColor == PIXEL_COLOR_BLACK) {
                        pixels[y][x] = " ";
                        break;
                    }
                    if (pixelColor == PIXEL_COLOR_WHITE) {
                        pixels[y][x] = "#";
                        break;
                    }
                }
            }
        }
    }

    public int getChecksum() {
        final ImageLayer layer = layers.stream()
                .min(Comparator.comparingInt(value -> value.getTotalNumberOfDigitsWithValue(0)))
                .get();
        LOG.info("Layer with the least number of 0 digits: {}", layer);
        return layer.getTotalNumberOfDigitsWithValue(1) * layer.getTotalNumberOfDigitsWithValue(2);
    }

    public String printImage() {
        final StringBuilder sb = new StringBuilder("Image:\n");
        for (final String[] y : pixels) {
            for (final String x : y) {
                sb.append(x);
            }
            sb.append("\n");
        }
        LOG.info(sb.toString());
        return sb.toString();
    }
}
