package nl.smerik.adventofcode.aoc2020.model.geom;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Point3D {

    private int x;
    private int y;
    private int z;

    public Point3D(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Set<Point3D> getNeighbours() {
        final Set<Point3D> result = new HashSet<>();
        for (int nx = x - 1; nx <= x + 1; nx++) {
            for (int ny = y - 1; ny <= y + 1; ny++) {
                for (int nz = z - 1; nz <= z + 1; nz++) {
                    if (nx == x && ny == y && nz == z) {
                        continue;
                    }
                    result.add(new Point3D(nx, ny, nz));
                }
            }
        }
        return result;
    }
}
