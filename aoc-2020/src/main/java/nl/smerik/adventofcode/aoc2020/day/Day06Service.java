package nl.smerik.adventofcode.aoc2020.day;

import nl.smerik.adventofcode.aoc2020.model.customs.GroupAnswers;
import nl.smerik.adventofcode.aoc2020.service.customs.CustomsService;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Day06Service {

    private final CustomsService customsService;

    @Value("classpath:input/day-06.txt")
    private Resource resource;

    public Day06Service(final CustomsService customsService) {
        this.customsService = customsService;
    }

    public Integer getSolutionPart1() {
        final List<GroupAnswers> answers = initAnswers();
        return customsService.sumCountUnionOfAnswers(answers);
    }

    public Integer getSolutionPart2() {
        final List<GroupAnswers> answers = initAnswers();
        return customsService.sumCountIntersectionOfAnswers(answers);
    }

    private List<GroupAnswers> initAnswers() {
        final List<String> input = PuzzleInputParser.parseToString(resource);
        return customsService.parseAnswers(input);
    }
}
