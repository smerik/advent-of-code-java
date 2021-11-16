package nl.smerik.adventofcode.aoc2020.model.geom;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Point4D {

    private int x;
    private int y;
    private int z;
    private int w;

    public Point4D(final int x, final int y, final int z, final int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Set<Point4D> getNeighbours() {
        final Set<Point4D> result = new HashSet<>();
        for (int nx = x - 1; nx <= x + 1; nx++) {
            for (int ny = y - 1; ny <= y + 1; ny++) {
                for (int nz = z - 1; nz <= z + 1; nz++) {
                    for (int nw = w - 1; nw <= w + 1; nw++) {
                        if (nx == x && ny == y && nz == z && nw == w) {
                            continue;
                        }
                        result.add(new Point4D(nx, ny, nz, nw));
                    }
                }
            }
        }
        return result;
    }
}
