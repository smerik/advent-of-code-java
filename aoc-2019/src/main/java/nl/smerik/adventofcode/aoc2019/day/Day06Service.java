package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.universe.UniversalOrbitMapFacility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class Day06Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day06Service.class);

    private static final int ORBITED_SPACE_OBJECT = 0;
    private static final int SPACE_OBJECT = 1;

    @Value("classpath:input/day-06.txt")
    private Resource resource;

    public Day06Service() {
    }

    public int getSolutionPart1()  {
        return parseMap().countTotalNumberOfOrbits();
    }

    public int getSolutionPart2() {
        return parseMap().countMinimumNumberOfTransfersRequired("YOU", "SAN");
    }

    private UniversalOrbitMapFacility parseMap() {
        final UniversalOrbitMapFacility mapFacility = new UniversalOrbitMapFacility();
        try {
            final Path path = Paths.get(resource.getURI());
            Files.lines(path).forEach(line -> addNode(mapFacility, line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapFacility;
    }

    private void addNode(final UniversalOrbitMapFacility mapFacility,
                         final String line) {
        final String[] spaceObjectIds = line.split("\\)");
        mapFacility.addSpaceObject(spaceObjectIds[ORBITED_SPACE_OBJECT], spaceObjectIds[SPACE_OBJECT]);
    }
}
