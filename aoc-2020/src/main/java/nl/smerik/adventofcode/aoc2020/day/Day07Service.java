package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.luggage.LuggageRules;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day07Service {

    @Value("classpath:input/day-07.txt")
    private Resource resource;

    public Integer getSolutionPart1() {
        return initLuggageRules().getBagsEventuallyContaining("shiny gold").size();
    }

    public Integer getSolutionPart2() {
        return initLuggageRules().countTotalNumberOfBagsInside("shiny gold");
    }

    private LuggageRules initLuggageRules() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return new LuggageRules(input);
    }
}
