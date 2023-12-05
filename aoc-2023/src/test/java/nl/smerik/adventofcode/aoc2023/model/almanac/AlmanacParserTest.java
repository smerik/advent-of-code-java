package nl.smerik.adventofcode.aoc2023.model.almanac;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static nl.smerik.adventofcode.aoc2023.model.almanac.AlmanacCategory.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlmanacParserTest {

    @Value("classpath:input/day-05/example-01.txt")
    private Resource resource;

    @Test
    void testParse() {
        final List<String> lines = PuzzleInputParser.parseToString(resource);
        final Almanac almanac = AlmanacParser.parse(lines);
        assertNotNull(almanac);
        assertEquals(Set.of(79L, 14L, 55L, 13L), almanac.getSeedsToBePlanted());

        final Map<AlmanacCategory, AlmanacMap> almanacMapByCategory = almanac.getAlmanacMapByCategory();
        assertEquals(2, almanacMapByCategory.get(SEED).getRanges().size());
        assertEquals(3, almanacMapByCategory.get(SOIL).getRanges().size());
        assertEquals(4, almanacMapByCategory.get(FERTILIZER).getRanges().size());
        assertEquals(2, almanacMapByCategory.get(WATER).getRanges().size());
        assertEquals(3, almanacMapByCategory.get(LIGHT).getRanges().size());
        assertEquals(2, almanacMapByCategory.get(TEMPERATURE).getRanges().size());
        assertEquals(2, almanacMapByCategory.get(HUMIDITY).getRanges().size());
        assertNull(almanacMapByCategory.get(LOCATION));
        assertEquals(7, almanacMapByCategory.size());
    }
}