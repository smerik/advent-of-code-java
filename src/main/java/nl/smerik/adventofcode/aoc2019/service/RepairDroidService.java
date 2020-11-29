package nl.smerik.adventofcode.aoc2019.service;

import nl.smerik.adventofcode.aoc2019.model.IntcodeComputer;
import nl.smerik.adventofcode.aoc2019.model.repairdroid.Cell;
import nl.smerik.adventofcode.aoc2019.model.repairdroid.RepairDroid;
import nl.smerik.adventofcode.aoc2019.model.repairdroid.Section;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Service;

import java.awt.Point;

@Service
public class RepairDroidService {

    public int determineFewestNumberOfCommandsToMoveToOxygenSystem(final long[] program) {
        final IntcodeComputer intcodeComputer = new IntcodeComputer(program);
        final RepairDroid repairDroid = new RepairDroid(intcodeComputer);

        final Point startPosition = new Point(repairDroid.getCurrentPosition());
        final Section section = new Section(repairDroid);
        final Cell startCell = section.getArea().get(startPosition);
        final Cell oxygenCell = section.getArea().values().stream().filter(cell -> cell.getType() == Cell.Type.OXYGEN).findAny().orElseThrow();

        final BFSShortestPath<Cell, DefaultEdge> shortestPath = new BFSShortestPath<>(section.getHallway());
        final GraphPath<Cell, DefaultEdge> path = shortestPath.getPath(startCell, oxygenCell);
        return path.getLength();
    }
}
