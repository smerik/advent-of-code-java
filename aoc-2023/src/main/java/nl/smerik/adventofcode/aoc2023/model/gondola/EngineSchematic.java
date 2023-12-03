package nl.smerik.adventofcode.aoc2023.model.gondola;

import lombok.Getter;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class EngineSchematic {

    private static final Pattern SYMBOL_PATTERN = Pattern.compile("(?<symbol>[^\\d.])");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(?<number>\\d+)");

    private final Map<Point, SchemaSymbol> schemaSymbols;
    private final Set<SchemaNumber> schemaNumbers;

    public EngineSchematic(List<String> lines) {
        schemaSymbols = new HashMap<>();
        schemaNumbers = new HashSet<>();
        parseSchematic(lines);
    }

    public int sumPartNumbers() {
        return schemaNumbers.stream().filter(SchemaNumber::isEnginePart).mapToInt(SchemaNumber::getNumber).sum();
    }

    private void parseSchematic(final List<String> lines) {
        parseSymbols(lines);
        parseNumbers(lines);
    }

    private void parseSymbols(final List<String> lines) {
        int y = 0;
        for (String line : lines) {
            final Matcher matcher = SYMBOL_PATTERN.matcher(line);
            while (matcher.find()) {
                final Point point = new Point(matcher.start(), y);
                char symbol = matcher.group("symbol").charAt(0);
                schemaSymbols.put(point, new SchemaSymbol(symbol, point));
            }
            y++;
        }
    }

    private void parseNumbers(final List<String> lines) {
        int y = 0;
        for (String line : lines) {
            final Matcher matcher = NUMBER_PATTERN.matcher(line);
            while (matcher.find()) {
                final Point point = new Point(matcher.start(), y);
                int length = matcher.end() - matcher.start();
                int number = Integer.parseInt(matcher.group("number"));
                boolean isEnginePart = determineIfNumberIsEnginePart(point, length);
                schemaNumbers.add(new SchemaNumber(number, point, isEnginePart));
            }
            y++;
        }

    }

    private boolean determineIfNumberIsEnginePart(final Point point, final int length) {
        return determineSurroundingPoints(point, length).stream().anyMatch(schemaSymbols::containsKey);
    }

    private Set<Point> determineSurroundingPoints(final Point point, final int length) {
        final Set<Point> result = new HashSet<>();
        for (int y = point.y - 1; y <= point.y + 1; y++) {
            for (int x = point.x - 1; x <= point.x + length; x++) {
                if (y == point.y && x >= point.x && x < point.x + length) {
                    // ignore points which are part of the number itself
                    continue;
                }
                result.add(new Point(x, y));
            }
        }
        return result;
    }

    @Getter
    static class SchemaNumber {

        private final Point point;
        private final int number;
        private final boolean isEnginePart;

        public SchemaNumber(final int number, final Point point, final boolean isEnginePart) {
            this.point = point;
            this.number = number;
            this.isEnginePart = isEnginePart;
        }

        @Override
        public String toString() {
            return "" + number;
        }
    }

    @Getter
    static class SchemaSymbol {

        private final Point point;
        private final char symbol;

        public SchemaSymbol(final char symbol, final Point point) {
            this.point = point;
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return "" + symbol;
        }
    }
}
