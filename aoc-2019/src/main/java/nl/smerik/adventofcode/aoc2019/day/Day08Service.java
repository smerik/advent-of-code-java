package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.image.Image;
import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Day08Service {

    @Value("classpath:input/day-08.txt")
//    @Value("classpath:input/day-08-test-part-01.txt")
//    @Value("classpath:input/day-08-test-part-02.txt")
    private Resource resource;

    public int getSolutionPart1() {
        return initImage().getChecksum();
    }

    public String getSolutionPart2() {
        return initImage().printImage();
    }

    private Image initImage() {
        final String imageData = PuzzleInputParser.parseToString(resource).get(0);
        return new Image(imageData, 25, 6);
    }
}
