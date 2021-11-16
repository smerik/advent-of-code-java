package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.combat.CombatGame;
import nl.smerik.adventofcode.aoc2020.model.combat.CombatPlayer;
import nl.smerik.adventofcode.aoc2020.model.combat.RecursiveCombatGame;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
@Service
public class Day22Service {

    @Value("classpath:input/day-22.txt")
    private Resource resource;

    @SneakyThrows
    public Integer getSolutionPart1() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final CombatGame game = new CombatGame(stringStream.toList());
            final CombatPlayer winner = game.playUntilGameEnds();
            return winner.calculateScore();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }

    @SneakyThrows
    public Integer getSolutionPart2() {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final RecursiveCombatGame game = new RecursiveCombatGame(stringStream.toList());
            final CombatPlayer winner = game.playUntilGameEnds();
            return winner.calculateScore();
        } catch (IOException e) {
            LOG.error("Houston: {}", e.getMessage(), e);
            return null;
        }
    }
}
