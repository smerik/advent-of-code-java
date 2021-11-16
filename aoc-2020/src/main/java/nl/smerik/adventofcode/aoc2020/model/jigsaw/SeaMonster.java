package nl.smerik.adventofcode.aoc2020.model.jigsaw;

import lombok.Data;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

@Data
public class SeaMonster {

    private char[][] content;

    public SeaMonster() {
        this.content = parseSeaMonster();
    }

    private char[][] parseSeaMonster() {
        final String line1 = "                  # ";
        final String line2 = "#    ##    ##    ###";
        final String line3 = " #  #  #  #  #  #   ";

        final char[][] result = new char[3][line1.length() - 1];
        result[0] = line1.toCharArray();
        result[1] = line2.toCharArray();
        result[2] = line3.toCharArray();
        return result;
    }

    public void rotateCW() {
        int height = content.length;
        int width = content[0].length;
        char[][] result = new char[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[j][height - 1 - i] = content[i][j];
            }
        }
        content = result;
    }

    /**
     * Flips the sea monster horizontally.
     */
    public void flipHorizontally() {
        final int height = this.content.length;
        final int width = this.content[0].length;
        final char[][] flippedContent = new char[height][width];
        int y = height - 1;
        for (final char[] chars : this.content) {
            flippedContent[y] = chars;
            y--;
        }
        System.arraycopy(flippedContent, 0, this.content, 0, height);
    }

    public Set<Point> translate(final int x, final int y) {
        final Set<Point> result = new HashSet<>();
        for (final Point location : getSeaMonsterCoordinates()) {
            final Point point = new Point(location);
            point.translate(x, y);
            result.add(point);
        }
        return result;
    }

    public Set<Point> getSeaMonsterCoordinates() {
        final Set<Point> result = new HashSet<>();
        for (int y = 0; y < content.length; y++) {
            for (int x = 0; x < content[0].length; x++) {
                if (content[y][x] == '#') {
                    result.add(new Point(x, y));
                }
            }
        }
        return result;
    }

    public String buildImage() {
        final StringBuilder builder = new StringBuilder();
        for (int y = 0; y < content.length; y++) {
            builder.append(content[y]);
            if (y < content.length - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
