package nl.smerik.adventofcode.aoc2023.model.map.hauntedwasteland;

import lombok.Getter;

import java.util.*;

@Getter
public class HauntedWastelandMap {

    private final List<NodeDirection> directions;
    private final Map<String, List<String>> mapByNode;

    public HauntedWastelandMap(final List<String> lines) {
        final Iterator<String> linesIterator = lines.iterator();
        this.directions = parseDirections(linesIterator.next());
        // line will be blank
        linesIterator.next();
        mapByNode = parseNodes(linesIterator);
    }

    private List<NodeDirection> parseDirections(final String directions) {
        return directions.chars().mapToObj(instruction -> (char) instruction).map(NodeDirection::valueByInstruction).toList();
    }

    private Map<String, List<String>> parseNodes(Iterator<String> linesIterator) {
        final Map<String, List<String>> result = new HashMap<>();
        while (linesIterator.hasNext()) {
            final String line = linesIterator.next();
            final String[] splitLine = line.split(" = ");
            final String key = splitLine[0];
            final List<String> values = Arrays.stream(splitLine[1].replaceAll("[()]", "").split(", ")).toList();
            result.put(key, values);
        }
        return result;
    }

    public final int determineStepCountToReachEndpoint() {
        return followInstructions().size() - 1;
    }

    public final List<String> followInstructions() {
        final List<String> result = new ArrayList<>();
        result.add("AAA");
        int directionIndex = 0;
        while (!"ZZZ".equals(result.get(result.size() - 1))) {
            final NodeDirection direction = directions.get(directionIndex);
            final List<String> nodes = mapByNode.get(result.get(result.size() - 1));
            result.add(direction == NodeDirection.LEFT ? nodes.get(0) : nodes.get(1));
            directionIndex = directionIndex + 1 < directions.size() ? directionIndex + 1 : 0;
        }
        return result;
    }
}
