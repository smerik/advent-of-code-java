package nl.smerik.adventofcode.aoc2021.model.cave;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.awt.Point;
import java.util.List;

public class ChitonCave {

    private final DirectedWeightedMultigraph<Point, DefaultWeightedEdge> cave;

    private final Point startPosition;
    private final Point endPosition;

    public ChitonCave(final List<String> riskLevelMap) {
        this.cave = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
        parseRiskLevelMap(riskLevelMap);
        this.startPosition = new Point(0, 0);
        this.endPosition = new Point(riskLevelMap.get(0).length() - 1, riskLevelMap.size() - 1);
    }

    private void parseRiskLevelMap(final List<String> riskLevelMap) {
        if (riskLevelMap.isEmpty()) {
            return;
        }

        for (int y = 0; y < riskLevelMap.size(); y++) {
            for (int x = 0; x < riskLevelMap.get(0).length(); x++) {
                final int riskLevel = Character.getNumericValue(riskLevelMap.get(y).charAt(x));
                final Point location = new Point(x, y);
                cave.addVertex(location);
                if (x > 0) {
                    final Point locationLeft = new Point(x - 1, y);
                    final DefaultWeightedEdge edgeRight = cave.addEdge(locationLeft, location);
                    cave.setEdgeWeight(edgeRight, riskLevel);

                    final int riskLevelLeft = Character.getNumericValue(riskLevelMap.get(y).charAt(x - 1));
                    final DefaultWeightedEdge edgeLeft = cave.addEdge(location, locationLeft);
                    cave.setEdgeWeight(edgeLeft, riskLevelLeft);
                }
                if (y > 0) {
                    final Point locationTop = new Point(x, y - 1);
                    final DefaultWeightedEdge edge = cave.addEdge(locationTop, location);
                    cave.setEdgeWeight(edge, riskLevel);

                    final int riskLevelTop = Character.getNumericValue(riskLevelMap.get(y - 1).charAt(x));
                    final DefaultWeightedEdge edgeTop = cave.addEdge(location, locationTop);
                    cave.setEdgeWeight(edgeTop, riskLevelTop);
                }
            }
        }
    }

    public List<Point> findPathWithLowestTotalRisk() {
        return getShortestPath().getVertexList();
    }

    public long calculateTotalRisk() {
        return ((Double) getShortestPath().getWeight()).longValue();
    }

    private GraphPath<Point, DefaultWeightedEdge> getShortestPath() {
        return new DijkstraShortestPath<>(cave).getPath(startPosition, endPosition);
    }
}
