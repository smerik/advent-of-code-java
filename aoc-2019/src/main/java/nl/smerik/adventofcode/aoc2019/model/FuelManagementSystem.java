package nl.smerik.adventofcode.aoc2019.model;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.*;
import java.util.List;

@Slf4j
public class FuelManagementSystem {

    private List<Wire> wires;

    public FuelManagementSystem() {
        wires = new ArrayList<>();
    }

    public boolean addWire(final Wire wire) {
        return wires.add(wire);
    }

    public Set<Point> getIntersectionsBetweenWires() {
        final Set<Point> wire1 = new HashSet<>(wires.get(0).getCoordinates());
        final Set<Point> wire2 = new HashSet<>(wires.get(1).getCoordinates());

        final Set<Point> result = new HashSet<>(wire1);
        result.retainAll(wire2);
        LOG.debug("Intersections:{}", result);
        return result;
    }

    public int getManhattanDistanceFromCentralPortToClosestIntersection() {
        final Point centralPort = new Point();

        Set<Point> intersections = getIntersectionsBetweenWires();
        intersections.remove(centralPort);

        return intersections.stream()
                .map(intersection -> calculateManhattanDistance(centralPort, intersection))
                .sorted()
                .findFirst()
                .orElse(0);
    }

    private int calculateManhattanDistance(final Point centralPort, final Point intersection) {
        return Math.abs(intersection.x) + Math.abs(intersection.y);
    }

    public int getFewestCombinedStepsToReachAnIntersection() {
        final List<Integer> combinedStepsToReachAnIntersection = new ArrayList<>();

        final Set<Point> intersections = getIntersectionsBetweenWires();
        intersections.remove(new Point());
        for (Point point : intersections) {
            final int stepsWire1 = wires.get(0).getNumberOfStepsToPoint(point);
            final int stepsWire2 = wires.get(1).getNumberOfStepsToPoint(point);
            combinedStepsToReachAnIntersection.add(stepsWire1 + stepsWire2);
        }
        return combinedStepsToReachAnIntersection
                .stream()
                .sorted()
                .findFirst()
                .orElse(0);
    }
}
