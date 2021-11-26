package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.universe.UniversalOrbitMapFacility;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day06Service {

    private static final int ORBITED_SPACE_OBJECT = 0;
    private static final int SPACE_OBJECT = 1;

    @Value("classpath:input/day-06.txt")
    private Resource resource;

    public int getSolutionPart1() {
        return initOrbitFacility().countTotalNumberOfOrbits();
    }

    public int getSolutionPart2() {
        return initOrbitFacility().countMinimumNumberOfTransfersRequired("YOU", "SAN");
    }

    private UniversalOrbitMapFacility initOrbitFacility() {
        final UniversalOrbitMapFacility mapFacility = new UniversalOrbitMapFacility();
        final List<String> mapDataLines = PuzzleInputParser.parseToString(resource);
        mapDataLines.forEach(line -> addNode(mapFacility, line));
        return mapFacility;
    }

    private void addNode(final UniversalOrbitMapFacility mapFacility,
                         final String line) {
        final String[] spaceObjectIds = line.split("\\)");
        mapFacility.addSpaceObject(spaceObjectIds[ORBITED_SPACE_OBJECT], spaceObjectIds[SPACE_OBJECT]);
    }
}
