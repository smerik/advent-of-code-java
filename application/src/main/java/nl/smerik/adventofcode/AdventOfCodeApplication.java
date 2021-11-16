package nl.smerik.adventofcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AdventOfCodeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        LOG.info("Starting AdventOfCodeApplication...");
        SpringApplication.run(AdventOfCodeApplication.class, args);
        LOG.info("Finished AdventOfCodeApplication.");
    }

    @Override
    public void run(final String... args) {
        LOG.info("Executing CommandLineRunner...");

        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }
}
