package nl.smerik.adventofcode.aoc2020.model.conway;

import lombok.Getter;
import nl.smerik.adventofcode.geom.Point3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class Satellite {

    private final Map<Point3D, Cube> conwayCubes;

    public Satellite(final List<String> regionOfCubes) {
        conwayCubes = parseRegionOfCubes(regionOfCubes);
    }

    private Map<Point3D, Cube> parseRegionOfCubes(final List<String> regionOfCubes) {
        final Map<Point3D, Cube> result = new HashMap<>();
        for (int y = 0; y < regionOfCubes.size(); y++) {
            final String line = regionOfCubes.get(y);
            for (int x = 0; x < line.length(); x++) {
                final Point3D point = new Point3D(x, y, 0);
                final boolean active = line.charAt(x) == '#';
                result.put(point, new Cube(point, active));
            }
        }
        return result;
    }

    public void execute(final int numberOfCycles) {
        for (int i = 0; i < numberOfCycles; i++) {
            execute();
        }
    }

    private void execute() {
        // expand dimension first so the cubes around given region will also switch active states
        final Map<Point3D, Cube> boundaries = conwayCubes.keySet().stream()
                .flatMap(point -> point.getNeighbours().stream())
                .filter(point -> !conwayCubes.containsKey(point))
                .map(Cube::new)
                .collect(Collectors.toMap(Cube::getPoint, Function.identity(), (existing, replacement) -> existing));
        conwayCubes.putAll(boundaries);

        final Set<Cube> cubesToToggleActiveState = new HashSet<>();
        for (final Cube cube : conwayCubes.values()) {
            final long activeNeighbourCount = cube.getPoint().getNeighbours()
                    .stream()
                    .map(point -> conwayCubes.getOrDefault(point, new Cube(point)))
                    .filter(Cube::isActive)
                    .count();

            if ((cube.isActive() && (activeNeighbourCount < 2 || activeNeighbourCount > 3))
                    || (!cube.isActive() && activeNeighbourCount == 3)) {
                cubesToToggleActiveState.add(cube);
            }
        }
        cubesToToggleActiveState.forEach(Cube::toggleActiveState);
    }

    public long countActiveCubes() {
        return conwayCubes.values().stream().filter(Cube::isActive).count();
    }
}
