package nl.smerik.adventofcode.aoc2019.day;

import nl.smerik.adventofcode.aoc2019.model.image.Image;
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
public class Day08Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day08Service.class);

    @Value("classpath:input/day-08.txt")
//    @Value("classpath:input/day-08-test-part-01.txt")
//    @Value("classpath:input/day-08-test-part-02.txt")
    private Resource resource;

    public Day08Service() {
    }

    public int getSolutionPart1() throws IOException {
        final Image image = getImage(25, 6);
//        final Image image = getImage(3, 2);
        return image.getChecksum();
    }

    public String getSolutionPart2() throws IOException {
        final Image image = getImage(25, 6);
//        final Image image = getImage(2, 2);
        return image.printImage();
    }

    private Image getImage(final int width, final int height) throws IOException {
        final Path path = Paths.get(resource.getURI());
        final String imageData = Files.readString(path).replaceAll("\n", "").replaceAll("\r", "");
        return new Image(imageData, width, height);
    }
}
