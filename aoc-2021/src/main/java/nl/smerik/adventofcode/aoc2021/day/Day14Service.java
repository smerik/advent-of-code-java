package nl.smerik.adventofcode.aoc2021.day;

import nl.smerik.adventofcode.aoc2021.model.submarine.polymerization.PolymerizationEquipment;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Day14Service {

    @Value("classpath:input/day-14.txt")
    private Resource resource;

    public Long getSolutionPart1() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final PolymerizationEquipment equipment = new PolymerizationEquipment(lines);
        equipment.applyRules(10);
        final Map<Character, Long> elementsCount = equipment.countElements();
        final Long mostCommonElementCount = elementsCount.get(equipment.findMostCommonElement());
        final Long leastCommonElementCount = elementsCount.get(equipment.findLeastCommonElement());
        return mostCommonElementCount - leastCommonElementCount;
    }
}
