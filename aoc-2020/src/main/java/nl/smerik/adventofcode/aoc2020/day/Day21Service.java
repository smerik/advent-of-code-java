package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import nl.smerik.adventofcode.aoc2020.model.allergen.AllergenAssessment;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day21Service {

    @Value("classpath:input/day-21.txt")
    private Resource resource;

    @SneakyThrows
    public Long getSolutionPart1() {
        return initAllegenAssessment().countIngredientsNotPossiblyContainingAllergens();
    }

    public String getSolutionPart2() {
        return initAllegenAssessment().produceCanonicalDangerousIngredientList();
    }

    private AllergenAssessment initAllegenAssessment() {
        final List<String> foodLines = PuzzleInputParser.parseToString(resource);
        return new AllergenAssessment(foodLines);
    }
}
