package nl.smerik.adventofcode.aoc2019.day;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Day12Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day12Service.class);

    @Value("classpath:input/day-12.txt")
    private Resource resource;

    public Day12Service() {
    }

    public int getSolutionPart1() throws IOException {
        return -1;
    }

    public int getSolutionPart2() throws IOException {
        return -1;
    }
}
