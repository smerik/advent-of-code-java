package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.cups.CrabCups;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Day23Service {

    final CrabCups crabCups;

    public Day23Service() {
        this.crabCups = new CrabCups("123487596");
    }

    @SneakyThrows
    public String getSolutionPart1() {
        this.crabCups.simulate(100);
        return this.crabCups.getCupLabeling();
    }
}
