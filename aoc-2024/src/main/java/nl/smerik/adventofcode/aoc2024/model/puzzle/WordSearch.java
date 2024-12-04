package nl.smerik.adventofcode.aoc2024.model.puzzle;

import java.util.List;

public class WordSearch {

    private final char[][] grid;

    public WordSearch(final List<String> lines) {
        this.grid = parseLines(lines);
    }

    private char[][] parseLines(final List<String> lines) {
        final char[][] result = new char[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            result[y] = lines.get(y).toCharArray();
        }
        return result;
    }

    public int countWord(final String word) {
        int result = 0;
        result += countHorizontal(word);
        result += countVertical(word);
        result += countDiagonalTopLeftToBottomRight(word);
        result += countDiagonalTopRightToBottomLeft(word);
        return result;
    }

    private int countHorizontal(final String word) {
        int result = 0;
        for (char[] chars : grid) {
            final String line = new String(chars);
            result += countWordInLine(word, line);
        }
        return result;
    }

    private int countVertical(final String word) {
        int result = 0;
        for (int x = 0; x < grid.length; x++) {
            final StringBuilder builder = new StringBuilder();
            for (char[] chars : grid) {
                builder.append(chars[x]);
            }
            result += countWordInLine(word, builder.toString());
        }
        return result;
    }

    private int countDiagonalTopLeftToBottomRight(final String word) {
        int result = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Get diagonals starting from the first row
        for (int startCol = 0; startCol < cols; startCol++) {
            final StringBuilder builder = new StringBuilder();
            for (int row = 0, col = startCol; row < rows && col < cols; row++, col++) {
                builder.append(grid[row][col]);
            }
            result += countWordInLine(word, builder.toString());
        }

        // Get diagonals starting from the first column
        for (int startRow = 1; startRow < rows; startRow++) {
            final StringBuilder builder = new StringBuilder();
            for (int row = startRow, col = 0; row < rows && col < cols; row++, col++) {
                builder.append(grid[row][col]);
            }
            result += countWordInLine(word, builder.toString());
        }
        return result;
    }

    private int countDiagonalTopRightToBottomLeft(final String word) {
        int result = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Get diagonals starting from the first row
        for (int startCol = cols - 1; startCol >= 0; startCol--) {
            final StringBuilder builder = new StringBuilder();
            for (int row = 0, col = startCol; row < rows && col >= 0; row++, col--) {
                builder.append(grid[row][col]);
            }
            result += countWordInLine(word, builder.toString());
        }

        // Get diagonals starting from the last column
        for (int startRow = 1; startRow < rows; startRow++) {
            final StringBuilder builder = new StringBuilder();
            for (int row = startRow, col = cols - 1; row < rows && col >= 0; row++, col--) {
                builder.append(grid[row][col]);
            }
            result += countWordInLine(word, builder.toString());
        }
        return result;
    }

    private int countWordInLine(final String word, final String line) {
        int result = 0;
        result += countWord(word, line);
        result += countWord(word, reverseLine(line));
        return result;
    }

    private int countWord(final String word, final String line) {
        int result = 0;
        int fromIndex = 0;
        while ((fromIndex = line.indexOf(word, fromIndex)) != -1) {
            result++;
            fromIndex++;
        }
        return result;
    }

    private String reverseLine(final String line) {
        return new StringBuilder(line).reverse().toString();
    }

    public int countXMasShape() {
        final String word = "MAS";
        int result = 0;
        for (int x = 1; x < grid.length - 1; x++) {
            for (int y = 1; y < grid[x].length - 1; y++) {
                if (grid[y][x] != 'A') {
                    continue;
                }

                final char[] wordTopLeftToBottomRight = {grid[y - 1][x - 1], grid[y][x], grid[y + 1][x + 1]};
                if (countWordInLine(word, new String(wordTopLeftToBottomRight)) == 0) {
                    continue;
                }

                final char[] wordTopRightToBottomLeft = {grid[y - 1][x + 1], grid[y][x], grid[y + 1][x - 1]};
                if (countWordInLine(word, new String(wordTopRightToBottomLeft)) == 0) {
                    continue;
                }
                result++;
            }
        }
        return result;
    }
}
