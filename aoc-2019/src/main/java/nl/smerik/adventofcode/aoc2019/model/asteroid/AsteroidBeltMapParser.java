package nl.smerik.adventofcode.aoc2019.model.asteroid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class AsteroidBeltMapParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsteroidBeltMapParser.class);
    public static final char ASTEROID_TOKEN = '#';


    private AsteroidBeltMapParser() {
    }

    public static AsteroidBelt parse(final Path path) throws IOException {
        LOGGER.info("Parsing asteroids map '{}'...", path);
        final List<Point> asteroids = new ArrayList<>();

        final LineNumberReader reader = new LineNumberReader(new FileReader(path.toFile()));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            parseLine(currentLine).forEach(integer -> asteroids.add(new Point(integer, reader.getLineNumber() - 1)));
        }

        LOGGER.info("Finished parsing map containing {} asteroids.", asteroids.size());
        return new AsteroidBelt(asteroids);
    }

    private static List<Integer> parseLine(final String line) {
        final List<Integer> result = new ArrayList<>();
        int index = -1;
        while ((index = line.indexOf(ASTEROID_TOKEN, index + 1)) != -1) {
            result.add(index);
        }
        return result;
    }
}
