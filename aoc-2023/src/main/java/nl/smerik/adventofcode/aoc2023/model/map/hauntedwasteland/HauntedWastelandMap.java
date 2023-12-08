package nl.smerik.adventofcode.aoc2023.model.map.hauntedwasteland;

import lombok.Getter;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static boolean isPossibleEndNode(final String node) {
        return node.endsWith("Z");
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
        while (!"ZZZ".equals(result.get(result.size() - 1))) {
            for (final NodeDirection direction : directions) {
                final List<String> nodes = mapByNode.get(result.get(result.size() - 1));
                result.add(direction == NodeDirection.LEFT ? nodes.get(0) : nodes.get(1));
            }
        }
        return result;
    }

    public final BigInteger determineStepCountToReachEndpointAsAGhost() {
        final Map<String, String> startNodesFor = determineStartNodesForGhost().stream().collect(Collectors.toMap(Function.identity(), Function.identity()));

        final Map<String, BigInteger> endsWithZStepCountByStartNode = new HashMap<>();
        for (final String startNode : startNodesFor.keySet()) {
            int stepCount = 0;
            String currentNode = startNode;
            while (!isPossibleEndNode(currentNode)) {
                for (final NodeDirection direction : directions) {
                    final List<String> nodes = mapByNode.get(currentNode);
                    currentNode = direction == NodeDirection.LEFT ? nodes.get(0) : nodes.get(1);
                    stepCount++;
                }
            }
            endsWithZStepCountByStartNode.put(startNode, BigInteger.valueOf(stepCount));
        }
        return endsWithZStepCountByStartNode.values().stream().reduce(BigInteger.ONE, HauntedWastelandMap::lcm);
    }

    public static BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }

    public Set<String> determineStartNodesForGhost() {
        return mapByNode.keySet().stream().filter(node -> node.endsWith("A")).collect(Collectors.toSet());
    }
}
