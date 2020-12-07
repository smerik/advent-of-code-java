package nl.smerik.adventofcode.aoc2020;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AdventOfCode2020Application implements CommandLineRunner {

    public static void main(String[] args) {
        LOG.info("Starting AdventOfCode2020Application...");
        SpringApplication.run(AdventOfCode2020Application.class, args);
        LOG.info("Finished AdventOfCode2020Application.");
    }

    @Override
    public void run(final String... args) {
        LOG.info("Executing CommandLineRunner...");

        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }
}
