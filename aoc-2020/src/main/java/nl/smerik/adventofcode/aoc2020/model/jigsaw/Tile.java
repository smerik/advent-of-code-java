package nl.smerik.adventofcode.aoc2020.model.jigsaw;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tile {

    private static final Pattern TILE_ID_PATTERN = Pattern.compile("Tile (?<id>\\d+):");

    // https://math.stackexchange.com/questions/3956235/which-combinations-of-nxn-matrix-rotations-and-horizontal-vertical-flips-resul
    private static final int TOTAL_NUMBER_OF_TILE_SYMMETRIES = 8;

    @EqualsAndHashCode.Include
    private final int id;

    private final char[][] content;
    private final Set<Tile> adjacentTiles = new HashSet<>();
    private Point position;


    private enum MatchingBorder {
        NONE,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    /**
     * @param lines the tile content
     */
    public Tile(final List<String> lines) {
        this.id = parseId(lines.get(0));
        this.content = parseInput(lines);
    }

    private int parseId(final String line) {
        final Matcher matcher = TILE_ID_PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid line for tile id '" + line + "'");
        }
        return Integer.parseInt(matcher.group("id"));
    }

    private char[][] parseInput(final List<String> input) {
        final int firstTileLine = 1;
        final char[][] result = new char[input.size() - 1][input.get(firstTileLine).length() - 1];
        for (int i = firstTileLine; i < input.size(); i++) {
            result[i - 1] = input.get(i).toCharArray();
        }
        return result;
    }

    /**
     * Adds given tile as an adjacent of this tile.
     * If given tile is not linked as an adjacent tile yet, it is also linked the other way around.
     *
     * @param tile the adjacent tile
     */
    public void addAdjacent(final Tile tile) {
        this.adjacentTiles.add(tile);
        if (!tile.adjacentTiles.contains(this)) {
            tile.addAdjacent(this);
        }
    }

    /**
     * Checks if this tile is a corner tile.
     *
     * @return <code>true</code> if this tile is a corner tile; <code>false</code> otherwise
     */
    public boolean isCorner() {
        return adjacentTiles.size() == 2;
    }


    /**
     * Checks if given tile is adjacent to this tile.
     *
     * @param tile the tile to check
     * @return <code>true</code> if given tile is adjacent; <code>false</code> otherwise
     */
    public boolean isAdjacent(final Tile tile) {
        if (this.equals(tile)) {
            return false;
        }
        if (this.adjacentTiles.contains(tile)) {
            return true;
        }
        return findMatchByArrangingTile(tile) != MatchingBorder.NONE;
    }

    /**
     * Checks if given tile has a border that matches one of the border of this tile.
     * In order to do so the process is rotating and flipping given tile.
     * When a match has found the matched border type is returned.
     * When no match was found at all the border type {@link MatchingBorder#NONE} is returned.
     *
     * @param tile the tile to find a match for
     * @return the matched border type
     */
    private MatchingBorder findMatchByArrangingTile(final Tile tile) {
        for (int i = 0; i < TOTAL_NUMBER_OF_TILE_SYMMETRIES; i++) {
            final MatchingBorder matchedBorder = findMatchingBorder(tile);
            if (matchedBorder != MatchingBorder.NONE) {
                return matchedBorder;
            }
            // Try all rotations first before flipping the tile horizontally
            if (i == 3) {
                tile.flipHorizontally();
            } else {
                tile.rotateCCW();
            }
        }
        return MatchingBorder.NONE;
    }

    public MatchingBorder findMatchingBorder(final Tile tile) {
        if (matchesBottom(tile)) {
            return MatchingBorder.BOTTOM;
        }
        if (matchesTop(tile)) {
            return MatchingBorder.TOP;
        }
        if (matchesLeft(tile)) {
            return MatchingBorder.LEFT;
        }
        if (matchesRight(tile)) {
            return MatchingBorder.RIGHT;
        }
        return MatchingBorder.NONE;
    }

    /**
     * Checks if the bottom border of given tile matches the top border of this tile.
     *
     * @param tile the tile to match to
     * @return <code>true</code> if matched; <code>false</code>> otherwise.
     */
    public boolean matchesTop(final Tile tile) {
        return tile.matchesBottom(this);
    }

    /**
     * Checks if the top border of given tile matches the bottom border of this tile.
     *
     * @param tile the tile to match to
     * @return <code>true</code> if matched; <code>false</code>> otherwise.
     */
    public boolean matchesBottom(final Tile tile) {
        final int topBorderIndex = 0;
        final int bottomBorderIndex = this.content.length - 1;
        return Arrays.equals(tile.content[topBorderIndex], this.content[bottomBorderIndex]);
    }

    /**
     * Checks if the right border of given tile matches the left border of this tile.
     *
     * @param tile the tile to match to
     * @return <code>true</code> if matched; <code>false</code>> otherwise.
     */
    public boolean matchesLeft(final Tile tile) {
        return tile.matchesRight(this);
    }

    /**
     * Checks if the left border of given tile matches the right border of this tile.
     *
     * @param tile the tile to match to
     * @return <code>true</code> if matched; <code>false</code>> otherwise.
     */
    public boolean matchesRight(final Tile tile) {
        final int leftBorderIndex = 0;
        final int rightBorderIndex = this.content[0].length - 1;
        for (int i = 0; i < this.content.length; i++) {
            if (tile.content[i][leftBorderIndex] != this.content[i][rightBorderIndex]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Rotates the tile counterclockwise (CCW) 90 degrees.
     */
    public void rotateCCW() {
        final int size = content.length;
        final char[][] rotatedContent = new char[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                rotatedContent[i][j] = content[j][size - i - 1];
            }
        }
        for (int i = 0; i < rotatedContent.length; ++i) {
            System.arraycopy(rotatedContent[i], 0, content[i], 0, content[i].length);
        }
    }

    /**
     * Flips the tile horizontally.
     */
    public void flipHorizontally() {
        final int size = content.length;
        final char[][] flippedContent = new char[size][size];
        int k = size - 1;
        for (final char[] chars : this.content) {
            flippedContent[k] = chars;
            k--;
        }
        System.arraycopy(flippedContent, 0, this.content, 0, size);
    }

    /**
     * Arrange all adjacent tiles to this tile.
     * After that all adjacent tiles of the tile will also be arranged.
     * This will continue until all tiles are arranged.
     *
     * @param arrangedTiles the tiles that are already arranged
     * @throws IllegalStateException When the tile position is unknown
     */
    public void arrangeAdjacentTiles(final Set<Tile> arrangedTiles) {
        if (this.position == null) {
            throw new IllegalStateException("Cannot arrange adjacent tiles when position is unknown.");
        }
        for (final Tile adjacentTile : adjacentTiles) {
            final Point alignedPosition = new Point(this.position);
            final MatchingBorder matchingBorder = adjacentTile.isArranged() ? findMatchingBorder(adjacentTile) : findMatchByArrangingTile(adjacentTile);
            switch (matchingBorder) {
                case TOP -> alignedPosition.translate(0, 1);     // set aligned position above this tile
                case BOTTOM -> alignedPosition.translate(0, -1); // set aligned position below this tile
                case LEFT -> alignedPosition.translate(-1, 0);   // set aligned position left to this tile
                case RIGHT -> alignedPosition.translate(1, 0);   // set aligned position right to this tile
                case NONE -> throw new IllegalArgumentException("Tile cannot be aligned");
                default -> throw new IllegalArgumentException("Unknown border type");
            }
            if (adjacentTile.position != null && !adjacentTile.position.equals(alignedPosition)) {
                throw new IllegalArgumentException("Position " + alignedPosition + " should be equal to already assigned position " + this.position);
            }
            adjacentTile.position = alignedPosition;
        }
        arrangedTiles.add(this);
        for (final Tile adjacentTile : adjacentTiles) {
            if (!arrangedTiles.contains(adjacentTile)) {
                adjacentTile.arrangeAdjacentTiles(arrangedTiles);
            }
        }
    }

    public boolean isArranged() {
        return this.position != null;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Tile ");
        builder.append(this.id).append(':').append(System.lineSeparator());
        for (final char[] chars : this.content) {
            builder.append(chars).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
