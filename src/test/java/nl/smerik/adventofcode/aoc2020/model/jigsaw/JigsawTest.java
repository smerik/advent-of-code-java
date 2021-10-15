package nl.smerik.adventofcode.aoc2020.model.jigsaw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JigsawTest {

    private final Jigsaw jigsawExample01;

    public JigsawTest(@Value("classpath:input/day-20/example-01.txt") final Resource resource) throws Exception {
        final Path path = Paths.get(resource.getURI());
        try (Stream<String> stringStream = Files.lines(path)) {
            final List<String> input = stringStream.toList();
            jigsawExample01 = new Jigsaw(input);
        }
    }

    @Test
    void testJigsawConstructor() {
        assertEquals(9, jigsawExample01.getTiles().size());
        assertEquals(4, jigsawExample01.getTiles().stream().filter(Tile::isCorner).count());
    }

    @Test
    void testMultiplyCornerTileIDs() {
        assertEquals(20899048083289L, jigsawExample01.multiplyCornerTileIDs());
    }
}