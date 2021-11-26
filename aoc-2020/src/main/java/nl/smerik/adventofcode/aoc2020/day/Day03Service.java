package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.AreaMap;
import nl.smerik.adventofcode.aoc2020.model.Toboggan;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day03Service {

    @Value("classpath:input/day-03.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        return initToboggan().countTreesUsingSlope(3, 1);
    }

    public Long getSolutionPart2() {
        final Toboggan toboggan = initToboggan();
        int result1 = toboggan.countTreesUsingSlope(1, 1);
        int result2 = toboggan.countTreesUsingSlope(3, 1);
        int result3 = toboggan.countTreesUsingSlope(5, 1);
        int result4 = toboggan.countTreesUsingSlope(7, 1);
        int result5 = toboggan.countTreesUsingSlope(1, 2);
        return (long) result1 * result2 * result3 * result4 * result5;
    }

    private Toboggan initToboggan() {
        final List<String> treePattern = PuzzleInputParser.parseToString(resource);
        final AreaMap areaMap = new AreaMap(treePattern);
        return new Toboggan(areaMap);
    }
}
