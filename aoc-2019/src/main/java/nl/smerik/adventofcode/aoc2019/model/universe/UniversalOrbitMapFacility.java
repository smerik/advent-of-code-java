package nl.smerik.adventofcode.aoc2019.model.universe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UniversalOrbitMapFacility {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversalOrbitMapFacility.class);

    private final Map<String, SpaceObject> space = new HashMap<>();

    public UniversalOrbitMapFacility() {
    }

    public void addSpaceObject(final String orbitedObjectId, final String objectId) {
        final SpaceObject orbitedObject = space.computeIfAbsent(orbitedObjectId, SpaceObject::new);
        final SpaceObject spaceObject = space.computeIfAbsent(objectId, SpaceObject::new);
        spaceObject.setOrbitedSpaceObject(orbitedObject);
    }

    public int countTotalNumberOfOrbits() {
        int result = 0;
        for (String id: space.keySet()) {
            result += countTotalNumberOfOrbits(id);
        }
        return result;
    }

    public int countTotalNumberOfOrbits(final String objectId) {
        int result = -1;
        SpaceObject currentSpaceObject = space.get(objectId);
        while (currentSpaceObject != null) {
            currentSpaceObject = currentSpaceObject.getOrbitedSpaceObject();
            result++;
        }
        return result;
    }

    public List<String> getOrbitalTransfersToCOM(final String objectId) {
        List<String> result = new ArrayList<>();

        SpaceObject currentObject = space.get(objectId);
        while(currentObject != null) {
            result.add(currentObject.getId());
            currentObject = currentObject.getOrbitedSpaceObject();
        }
        return result;
    }

    public int countMinimumNumberOfTransfersRequired(final String fromObjectId, final String toObjectId) {
        final List<String> toCOM1 = getOrbitalTransfersToCOM(fromObjectId);
        final List<String> toCOM2 = getOrbitalTransfersToCOM(toObjectId);

        LOGGER.debug("toCOM1:{}", toCOM1);
        LOGGER.debug("toCOM2:{}", toCOM2);

        final HashSet<String> toCOMSet1 = new HashSet<>(toCOM1);
        toCOMSet1.removeAll(toCOM2);
        toCOMSet1.remove(fromObjectId);
        final HashSet<String> toCOMSet2 = new HashSet<>(toCOM2);
        toCOMSet2.removeAll(toCOM1);
        toCOMSet2.remove(toObjectId);

        LOGGER.debug("toCOMSet1:{}", toCOMSet1);
        LOGGER.debug("toCOMSet2:{}", toCOMSet2);

        return toCOMSet1.size() + toCOMSet2.size();
    }
}


