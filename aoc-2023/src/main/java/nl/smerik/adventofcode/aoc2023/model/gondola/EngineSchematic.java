package nl.smerik.adventofcode.aoc2023.model.gondola;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
public class EngineSchematic {

    private static final Pattern SYMBOL_PATTERN = Pattern.compile("(?<symbol>[^\\d.])");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(?<number>\\d+)");

    private final Map<Point, SchemaSymbol> schemaSymbols;
    private final Map<Point, SchemaNumber> schemaNumbers;

    public EngineSchematic(final List<String> lines) {
        schemaSymbols = new HashMap<>();
        schemaNumbers = new HashMap<>();
        parseSchematic(lines);
    }

    public int sumPartNumbers() {
        return schemaNumbers.values().stream().distinct().filter(SchemaNumber::isEnginePart).mapToInt(SchemaNumber::getNumber).sum();
    }

    public int sumGearRatios() {
        int result = 0;
        final Set<Point> gearPositions = schemaSymbols.values().stream().filter(s -> s.getSymbol() == '*').map(SchemaSymbol::getPoint).collect(Collectors.toSet());
        for (final Point gearPosition : gearPositions) {
            result += calcGearRatio(gearPosition);
        }
        return result;
    }

    private int calcGearRatio(final Point gearPosition) {
        final Set<Point> points = determineSurroundingPoints(gearPosition, 1);
        final Set<SchemaNumber> numbers = schemaNumbers.entrySet().stream().filter(entry -> points.contains(entry.getKey())).map(Map.Entry::getValue).collect(Collectors.toSet());
        if (numbers.size() < 2) {
            return 0;
        }
        return numbers.stream().map(SchemaNumber::getNumber).reduce(1, (a, b) -> a * b);
    }

    private void parseSchematic(final List<String> lines) {
        parseSymbols(lines);
        parseNumbers(lines);
    }

    private void parseSymbols(final List<String> lines) {
        int y = 0;
        for (final String line : lines) {
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
        for (final String line : lines) {
            final Matcher matcher = NUMBER_PATTERN.matcher(line);
            while (matcher.find()) {
                final List<Point> points = determineNumberPoints(y, matcher.start(), matcher.end());
                int number = Integer.parseInt(matcher.group("number"));
                boolean isEnginePart = determineIfNumberIsEnginePart(points);
                final SchemaNumber schemaNumber = new SchemaNumber(number, points, isEnginePart);
                for (final Point point : points) {
                    schemaNumbers.put(point, schemaNumber);
                }
            }
            y++;
        }
    }

    private List<Point> determineNumberPoints(final int y, final int x, final int offset) {
        final List<Point> result = new ArrayList<>();
        for (int i = x; i < offset; i++) {
            result.add(new Point(i, y));
        }
        return result;
    }

    private boolean determineIfNumberIsEnginePart(final List<Point> points) {
        return determineSurroundingPoints(points.get(0), points.size()).stream().anyMatch(schemaSymbols::containsKey);
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

    @EqualsAndHashCode
    @Getter
    static class SchemaNumber {

        private final List<Point> points;
        private final int number;
        @EqualsAndHashCode.Exclude
        private final boolean isEnginePart;

        public SchemaNumber(final int number, final List<Point> points, final boolean isEnginePart) {
            this.points = points;
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
