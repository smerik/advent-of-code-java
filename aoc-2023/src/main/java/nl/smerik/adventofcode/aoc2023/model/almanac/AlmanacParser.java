package nl.smerik.adventofcode.aoc2023.model.almanac;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlmanacParser {

    private static final Pattern MAP_DESCRIPTION_PATTERN = Pattern.compile("(?<source>\\w+)-to-(?<destination>\\w+) map:");

    private enum ParserState {
        SEEDS_TO_BE_PLANTED {
            @Override
            ParserState next() {
                return MAP_DESCRIPTION;
            }
        },
        MAP_DESCRIPTION {
            @Override
            ParserState next() {
                return MAP_CONTENT;
            }
        },
        MAP_CONTENT {
            @Override
            ParserState next() {
                return MAP_DESCRIPTION;
            }
        };

        abstract ParserState next();
    }

    private AlmanacParser() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    public static Almanac parse(final List<String> lines) {
        final List<Long> seedsToBePlanted = new ArrayList<>();
        final Map<AlmanacCategory, AlmanacMap> almanacMapByCategory = new EnumMap<>(AlmanacCategory.class);

        ParserState state = ParserState.SEEDS_TO_BE_PLANTED;
        AlmanacMap currentAlmanacMap = new AlmanacMap(AlmanacCategory.SEED, AlmanacCategory.SOIL);
        for (final String line : lines) {
            if (line.isBlank()) {
                state = state.next();
                continue;
            }
            switch (state) {
                case SEEDS_TO_BE_PLANTED -> seedsToBePlanted.addAll(parseSeedsToBePlanted(line));
                case MAP_DESCRIPTION -> {
                    currentAlmanacMap = parseMapDescription(line);
                    almanacMapByCategory.put(currentAlmanacMap.getSourceCategory(), currentAlmanacMap);
                    state = state.next();
                }
                case MAP_CONTENT -> processMapContent(line, currentAlmanacMap);
                default -> throw new IllegalStateException("Unexpected value: '" + state + "'");
            }
        }
        return new Almanac(seedsToBePlanted, almanacMapByCategory);
    }

    private static List<Long> parseSeedsToBePlanted(final String line) {
        return Arrays.stream(line.substring("seeds: ".length()).split(" ")).map(Long::parseLong).toList();
    }

    private static AlmanacMap parseMapDescription(final String line) {
        final Matcher matcher = MAP_DESCRIPTION_PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Not a map description line '" + line + "'");
        }
        final AlmanacCategory source = AlmanacCategory.valueOfCategory(matcher.group("source"));
        final AlmanacCategory destination = AlmanacCategory.valueOfCategory(matcher.group("destination"));
        return new AlmanacMap(source, destination);
    }

    private static void processMapContent(final String line, final AlmanacMap almanacMap) {
        final long[] split = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
        final AlmanacRange range = new AlmanacRange(split[0], split[1], split[2]);
        almanacMap.addRange(range);
    }
}
