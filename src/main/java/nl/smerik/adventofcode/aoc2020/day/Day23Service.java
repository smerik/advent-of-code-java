package nl.smerik.adventofcode.aoc2020.day;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import nl.smerik.adventofcode.aoc2020.model.cups.CrabCups;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Day23Service {

    private static final String CUP_LABELING = "123487596";

    @SneakyThrows
    public String getSolutionPart1() {
        final CrabCups crabCups = new CrabCups(CUP_LABELING, CUP_LABELING.length());
        crabCups.simulate(100);
        return crabCups.getCupLabeling();
    }

    @SneakyThrows
    public long getSolutionPart2() {
        final CrabCups crabCups = new CrabCups(CUP_LABELING, 1000000);
        crabCups.simulate(10000000);
        return crabCups.multiplyCupsWithStars();
    }
}
