package nl.smerik.adventofcode.aoc2020.model.conway;

import lombok.Getter;
import nl.smerik.adventofcode.aoc2020.model.geom.Point4D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class Satellite4D {

    private final Map<Point4D, HyperCube> conwayCubes;

    public Satellite4D(final List<String> regionOfCubes) {
        conwayCubes = parseRegionOfCubes(regionOfCubes);
    }

    private Map<Point4D, HyperCube> parseRegionOfCubes(final List<String> regionOfCubes) {
        final Map<Point4D, HyperCube> result = new HashMap<>();
        for (int y = 0; y < regionOfCubes.size(); y++) {
            final String line = regionOfCubes.get(y);
            for (int x = 0; x < line.length(); x++) {
                final Point4D point = new Point4D(x, y, 0, 0);
                final boolean active = line.charAt(x) == '#';
                result.put(point, new HyperCube(point, active));
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
        final Map<Point4D, HyperCube> boundaries = conwayCubes.keySet().stream()
                .flatMap(point -> point.getNeighbours().stream())
                .filter(point -> !conwayCubes.containsKey(point))
                .map(HyperCube::new)
                .collect(Collectors.toMap(HyperCube::getPoint, Function.identity(), (existing, replacement) -> existing));
        conwayCubes.putAll(boundaries);

        final Set<HyperCube> cubesToToggleActiveState = new HashSet<>();
        for (final HyperCube cube : conwayCubes.values()) {
            final long activeNeighbourCount = cube.getPoint().getNeighbours()
                    .stream()
                    .map(point -> conwayCubes.getOrDefault(point, new HyperCube(point)))
                    .filter(HyperCube::isActive)
                    .count();

            if ((cube.isActive() && (activeNeighbourCount < 2 || activeNeighbourCount > 3))
                    || (!cube.isActive() && activeNeighbourCount == 3)) {
                cubesToToggleActiveState.add(cube);
            }
        }
        cubesToToggleActiveState.forEach(HyperCube::toggleActiveState);
    }

    public long countActiveCubes() {
        return conwayCubes.values().stream().filter(HyperCube::isActive).count();
    }
}
