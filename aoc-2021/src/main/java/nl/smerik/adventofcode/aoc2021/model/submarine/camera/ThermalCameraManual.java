package nl.smerik.adventofcode.aoc2021.model.submarine.camera;

import lombok.extern.slf4j.Slf4j;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ThermalCameraManual {

    private static final char DOT_CHARACTER = '#';
    private static final Pattern FOLDING_INSTRUCTION_PATTERN = Pattern.compile("fold along (?<foldingAxis>[xy])=(?<foldingLocation>\\d+)");

    private final List<String> foldingInstructions;

    private char[][] transparentPaper;

    public ThermalCameraManual(final List<String> lines) {
        final Iterator<String> lineIterator = lines.iterator();
        transparentPaper = parseDotCoordinatesOnPaper(lineIterator);
        foldingInstructions = parseFoldingInstructions(lineIterator);
    }

    private char[][] parseDotCoordinatesOnPaper(final Iterator<String> lineIterator) {
        final Set<Point> coordinates = new HashSet<>();
        LOG.debug("Start parsing dot coordinate lines...");
        while (lineIterator.hasNext()) {
            final String line = lineIterator.next();
            if (line.isBlank()) {
                LOG.debug("Finished parsing dot coordinate lines.");
                break;
            }
            final int[] coordinate = Arrays.stream(line.split(",")).mapToInt(Integer::valueOf).toArray();
            coordinates.add(new Point(coordinate[0], coordinate[1]));
        }

        final int maxX = coordinates.stream().max(Comparator.comparing(Point::getX)).orElseThrow().x + 1;
        final int maxY = coordinates.stream().max(Comparator.comparing(Point::getY)).orElseThrow().y + 1;

        final char[][] result = new char[maxY][maxX];
        for (final Point point : coordinates) {
            result[point.y][point.x] = '#';
        }
        return result;
    }

    private List<String> parseFoldingInstructions(final Iterator<String> lineIterator) {
        final List<String> result = new ArrayList<>();
        LOG.debug("Start parsing folding instructions...");
        while (lineIterator.hasNext()) {
            result.add(lineIterator.next());
        }
        LOG.debug("Finished parsing folding instructions.");
        return result;
    }

    public void foldByInstructions(final int foldLimit) {
        final int maxFoldCount = foldLimit == 0 ? foldingInstructions.size() : 1;
        for (int i = 0; i < maxFoldCount; i++) {
            fold(foldingInstructions.get(i));
        }
    }

    void fold(final String foldInstruction) {
        final Matcher matcher = FOLDING_INSTRUCTION_PATTERN.matcher(foldInstruction);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Unknown folding instruction '" + foldInstruction + "'");
        }
        final String foldingAxis = matcher.group("foldingAxis");
        final int foldingLocation = Integer.parseInt(matcher.group("foldingLocation"));

        switch (foldingAxis) {
            case "x" -> foldX(foldingLocation);
            case "y" -> foldY(foldingLocation);
            default -> throw new UnsupportedOperationException("Folding " + foldingAxis + "-axis not implemented");
        }
    }
    void foldX(final int foldingColumn) {
        final int height = this.transparentPaper.length;
        final int originalWidth = this.transparentPaper[0].length;

        // first copy the left half of the paper
        final char[][] firstHalf = new char[height][foldingColumn];
        for (int y = 0; y < height; y++) {
            System.arraycopy(this.transparentPaper[y], 0, firstHalf[y], 0, foldingColumn);
        }

        // then flip the right half of the paper
        final char[][] foldedHalf = new char[height][foldingColumn];
        for (int y = 0; y < height; y++) {
            for (int x = 0, originalX = originalWidth - 1; x < foldingColumn; x++, originalX--) {
                foldedHalf[y][x] = this.transparentPaper[y][originalX];
            }
        }

        // now merge the folded half with the first half
        this.transparentPaper = mergePapers(firstHalf, foldedHalf);
    }

    void foldY(final int foldingRow) {
        final int originalHeight = this.transparentPaper.length;
        final int width = this.transparentPaper[0].length;

        // first copy the top half of the paper
        final char[][] firstHalf = new char[foldingRow][width];
        System.arraycopy(this.transparentPaper, 0, firstHalf, 0, foldingRow);

        // then flip the bottom half of the paper
        final char[][] foldedHalf = new char[foldingRow][width];
        for (int y = 0, originalY = originalHeight - 1; y < foldingRow; y++, originalY--) {
            foldedHalf[y] = this.transparentPaper[originalY];
        }

        // now merge the folded half with the first half
        this.transparentPaper = mergePapers(firstHalf, foldedHalf);
    }

    char[][] mergePapers(final char[][] firstHalf, final char[][] foldedHalf) {
        for (int y = 0; y < firstHalf.length; y++) {
            for (int x = 0; x < firstHalf[0].length; x++) {
                if (foldedHalf[y][x] == DOT_CHARACTER) {
                    firstHalf[y][x] = foldedHalf[y][x];
                }
            }
        }
        return firstHalf;
    }

    public long countDots() {
        int result = 0;
        for (final char[] line : transparentPaper) {
            for (final char character : line) {
                if (character == DOT_CHARACTER) {
                    result++;
                }
            }
        }
        return result;
    }
}
