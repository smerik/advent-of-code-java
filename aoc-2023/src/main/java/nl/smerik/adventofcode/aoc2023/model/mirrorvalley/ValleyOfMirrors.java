package nl.smerik.adventofcode.aoc2023.model.mirrorvalley;

import java.util.ArrayList;
import java.util.List;

public class ValleyOfMirrors {

    private final List<TerrainTile> tiles;

    public ValleyOfMirrors(final List<String> lines) {
        tiles = parseLines(lines);
    }

    private List<TerrainTile> parseLines(List<String> lines) {
        final List<TerrainTile> result = new ArrayList<>();
        List<String> tileLines = new ArrayList<>();
        for (final String line : lines) {
            if (line.isEmpty()) {
                if (!tileLines.isEmpty()) {
                    result.add(new TerrainTile(tileLines));
                }
                tileLines = new ArrayList<>();
            } else {
                tileLines.add(line);
            }
        }
        return result;
    }

    public int sumAllPatternNotes() {
        return tiles.stream().map(TerrainTile::sumPatternNotes).mapToInt(Integer::intValue).sum();
    }
}
