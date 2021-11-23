package nl.smerik.adventofcode.aoc2019.service;

import nl.smerik.adventofcode.aoc2019.model.RocketModule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService {

    public Integer calculateTotalRequiredFuel(final List<Integer> masses, final boolean includeAddedFuel) {
        return masses.stream()
                     .map(RocketModule::new)
                     .map(module -> module.calculateRequiredFuel(includeAddedFuel))
                     .mapToInt(Integer::valueOf)
                     .sum();
    }
}
