package nl.smerik.adventofcode.aoc2023.model.parabolicreflector;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ParabolicReflector {

    private final TileType[][] platform;

    public ParabolicReflector(final List<String> lines) {
        platform = parseLines(lines);
    }

    private TileType[][] parseLines(final List<String> lines) {
        // @formatter:off
        return lines.stream()
                    .map(line -> line.chars().mapToObj(c -> TileType.valueOfToken((char) c)).toArray(TileType[]::new))
                    .toArray(TileType[][]::new);
        // @formatter:on
    }

    public int sumTotalLoad() {
        int result = 0;
        for (int y = 0; y < platform.length; y++) {
            result += calcLoad(y);
        }
        return result;
    }

    public int calcLoad(final int y) {
        int countRoundedRocks = 0;
        for (int x = 0; x < platform[y].length; x++) {
            if (platform[y][x] == TileType.ROUNDED_ROCK) {
                countRoundedRocks++;
            }
        }
        return countRoundedRocks * (platform[y].length - y);
    }

    public void tiltLever() {
        LOG.info("Pull the lever, Kronk!");
        for (int x = 0; x < platform[0].length; x++) {
            int lastBlockYPos = -1;
            for (int y = 0; y < platform.length; y++) {
                final TileType type = platform[y][x];
                switch (type) {
                    case CUBE_SHAPED_ROCK -> lastBlockYPos = y;
                    case ROUNDED_ROCK -> {
                        lastBlockYPos = lastBlockYPos == -1 ? 0 : lastBlockYPos + 1;
                        platform[y][x] = TileType.EMPTY_SPACE;
                        platform[lastBlockYPos][x] = TileType.ROUNDED_ROCK;

                    }
                    case EMPTY_SPACE -> {
                        // ignore
                    }
                    default -> throw new IllegalArgumentException("Unimplemented type: " + type);
                }
            }
        }
    }

    public String render() {
        final StringBuilder builder = new StringBuilder();
        for (int y = 0; y < platform.length; y++) {
            for (int x = 0; x < platform[0].length; x++) {
                builder.append(platform[y][x]);
            }
            if (y < platform.length - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
