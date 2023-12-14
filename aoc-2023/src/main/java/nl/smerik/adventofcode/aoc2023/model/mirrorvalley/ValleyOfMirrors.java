package nl.smerik.adventofcode.aoc2023.model.mirrorvalley;

import java.util.ArrayList;
import java.util.List;

public class ValleyOfMirrors {

    private final List<TerrainTile> tiles;

    public ValleyOfMirrors(final List<String> lines, final boolean fixSmudge) {
        tiles = parseLines(lines, fixSmudge);
    }

    private List<TerrainTile> parseLines(List<String> lines, final boolean fixSmudge) {
        final List<TerrainTile> result = new ArrayList<>();
        List<String> tileLines = new ArrayList<>();
        for (final String line : lines) {
            if (line.isEmpty()) {
                if (!tileLines.isEmpty()) {
                    result.add(new TerrainTile(tileLines, fixSmudge));
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
