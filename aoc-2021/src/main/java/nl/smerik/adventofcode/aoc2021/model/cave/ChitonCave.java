package nl.smerik.adventofcode.aoc2021.model.cave;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChitonCave {

    private final DirectedWeightedMultigraph<Point, DefaultWeightedEdge> caveGraph;

    private final Point startPosition;
    private final Point endPosition;

    public ChitonCave(final List<String> tile) {
        this(tile, 1);
    }

    public ChitonCave(final List<String> tileRows, final int tileDimensionMultiplier) {
        this.caveGraph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
        final List<String> riskLevelMap = buildRiskLevelMap(tileRows, tileDimensionMultiplier);
        parseRiskLevelMap(riskLevelMap);
        this.startPosition = new Point(0, 0);
        this.endPosition = new Point(riskLevelMap.get(0).length() - 1, riskLevelMap.size() - 1);
    }

    private List<String> buildRiskLevelMap(final List<String> tileRows, final int tileDimensionMultiplier) {
        final List<String> multipliedXResult = new ArrayList<>();
        // multiply x-axis
        for (final String tileRow : tileRows) {
            String resultTile = tileRow;
            final StringBuilder resultRowBuilder = new StringBuilder(resultTile);
            for (int i = 1; i < tileDimensionMultiplier; i++) {
                resultTile = updateRiskLevels(resultTile);
                resultRowBuilder.append(resultTile);
            }
            multipliedXResult.add(resultRowBuilder.toString());
        }

        // multiply y-axis
        final List<String> result = new ArrayList<>(multipliedXResult);
        List<String> tileRowToMultiply = new ArrayList<>(multipliedXResult);
        for (int i = 1; i < tileDimensionMultiplier; i++) {
            final List<String> multipliedTileRow = new ArrayList<>();
            for (final String tileRow : tileRowToMultiply) {
                multipliedTileRow.add(updateRiskLevels(tileRow));
            }
            result.addAll(multipliedTileRow);
            tileRowToMultiply = multipliedTileRow;
        }
        return result;
    }

    private String updateRiskLevels(final String tileRow) {
        return tileRow.chars().map(Character::getNumericValue).map(this::increaseRiskLevel).mapToObj(Integer::toString).collect(Collectors.joining(""));
    }

    private int increaseRiskLevel(final int riskLevel) {
        return riskLevel == 9 ? 1 : riskLevel + 1;
    }

    private void parseRiskLevelMap(final List<String> riskLevelMap) {
        if (riskLevelMap.isEmpty()) {
            return;
        }

        for (int y = 0; y < riskLevelMap.size(); y++) {
            for (int x = 0; x < riskLevelMap.get(0).length(); x++) {
                final int riskLevel = Character.getNumericValue(riskLevelMap.get(y).charAt(x));
                final Point location = new Point(x, y);
                caveGraph.addVertex(location);
                if (x > 0) {
                    final Point locationLeft = new Point(x - 1, y);
                    final DefaultWeightedEdge edgeRight = caveGraph.addEdge(locationLeft, location);
                    caveGraph.setEdgeWeight(edgeRight, riskLevel);

                    final int riskLevelLeft = Character.getNumericValue(riskLevelMap.get(y).charAt(x - 1));
                    final DefaultWeightedEdge edgeLeft = caveGraph.addEdge(location, locationLeft);
                    caveGraph.setEdgeWeight(edgeLeft, riskLevelLeft);
                }
                if (y > 0) {
                    final Point locationTop = new Point(x, y - 1);
                    final DefaultWeightedEdge edge = caveGraph.addEdge(locationTop, location);
                    caveGraph.setEdgeWeight(edge, riskLevel);

                    final int riskLevelTop = Character.getNumericValue(riskLevelMap.get(y - 1).charAt(x));
                    final DefaultWeightedEdge edgeTop = caveGraph.addEdge(location, locationTop);
                    caveGraph.setEdgeWeight(edgeTop, riskLevelTop);
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
        return new DijkstraShortestPath<>(caveGraph).getPath(startPosition, endPosition);
    }
}
