package nl.smerik.adventofcode.aoc2019.model.asteroid;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class AsteroidBeltMapParser {

    public static final char ASTEROID_TOKEN = '#';


    private AsteroidBeltMapParser() {
    }

    public static AsteroidBelt parse(final Path path) throws IOException {
        LOG.info("Parsing asteroids map '{}'...", path);
        final List<Point> asteroids = new ArrayList<>();

        final LineNumberReader reader = new LineNumberReader(new FileReader(path.toFile()));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            parseLine(currentLine).forEach(integer -> asteroids.add(new Point(integer, reader.getLineNumber() - 1)));
        }

        LOG.info("Finished parsing map containing {} asteroids.", asteroids.size());
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
