package nl.smerik.adventofcode.aoc2023.model.mirrorvalley;

import java.util.List;

public class TerrainTile {

    private final char[][] content;

    public TerrainTile(final List<String> lines) {
        this.content = parseLines(lines);
    }

    private char[][] parseLines(final List<String> lines) {
        final char[][] result = new char[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            result[y] = lines.get(y).toCharArray();
        }
        return result;
    }

    public int sumPatternNotes() {
        return findVerticalReflectionIndex() + 100 * findHorizontalReflectionIndex();
    }

    public int findVerticalReflectionIndex() {
        for (int x = 0; x < content[0].length; x++) {
            if (isVerticalReflection(x)) {
                return x;
            }
        }
        return 0;
    }

    private boolean isVerticalReflection(final int x) {
        if (x < 1 || x >= content[0].length) {
            return false;
        }

        int xLeft = x - 1;
        int xRight = x;
        while (xLeft >= 0 && xRight < content[0].length) {
            final String leftLine = getVerticalLine(xLeft);
            final String rightLine = getVerticalLine(xRight);
            if (!leftLine.equals(rightLine)) {
                return false;
            }
            xLeft--;
            xRight++;
        }
        return true;
    }

    public String getVerticalLine(final int x) {
        final StringBuilder result = new StringBuilder(content.length);
        for (char[] chars : content) {
            result.append(chars[x]);
        }
        return result.toString();
    }

    public int findHorizontalReflectionIndex() {
        for (int y = 0; y < content.length; y++) {
            if (isHorizontalReflection(y)) {
                return y;
            }
        }
        return 0;
    }

    private boolean isHorizontalReflection(final int y) {
        if (y < 1 || y >= content.length) {
            return false;
        }

        int yAbove = y - 1;
        int yBelow = y;
        while (yAbove >= 0 && yBelow < content.length) {
            final String lineAbove = getHorizontalLine(yAbove);
            final String lineBelow = getHorizontalLine(yBelow);
            if (!lineAbove.equals(lineBelow)) {
                return false;
            }
            yAbove--;
            yBelow++;
        }
        return true;
    }

    public String getHorizontalLine(final int y) {
        return String.valueOf(content[y]);
    }

    public String render() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final char[] rows : content) {
            stringBuilder.append(rows).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
