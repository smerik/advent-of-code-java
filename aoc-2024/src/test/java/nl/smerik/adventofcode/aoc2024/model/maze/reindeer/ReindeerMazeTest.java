package nl.smerik.adventofcode.aoc2024.model.maze.reindeer;

import nl.smerik.adventofcode.io.PuzzleInputParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReindeerMazeTest {

    @Value("classpath:input/day-16/example-01.txt")
    private Resource example01Resource;

    @Value("classpath:input/day-16/example-02.txt")
    private Resource example02Resource;

    /** Source: <a href="https://www.reddit.com/r/adventofcode/comments/1hfboft/comment/m2ecedv/">Reddit</a> */
    @Value("classpath:input/day-16/example-reddit.txt")
    private Resource exampleRedditResource;

    @Test
    void testFindLowestScore() {
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01Resource);
        final ReindeerMaze mazeExample01 = new ReindeerMaze(linesExample01);
        assertEquals(7036, mazeExample01.findLowestScore());

        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02Resource);
        final ReindeerMaze mazeExample02 = new ReindeerMaze(linesExample02);
        assertEquals(11048, mazeExample02.findLowestScore());
    }

    @Test
    void testCountBestPathTiles() {
        final List<String> linesExample01 = PuzzleInputParser.parseToString(example01Resource);
        final ReindeerMaze mazeExample01 = new ReindeerMaze(linesExample01);
        assertEquals(45, mazeExample01.countBestPathTiles());

        final List<String> linesExample02 = PuzzleInputParser.parseToString(example02Resource);
        final ReindeerMaze mazeExample02 = new ReindeerMaze(linesExample02);
        assertEquals(64, mazeExample02.countBestPathTiles());

        final List<String> linesExampleR = PuzzleInputParser.parseToString(exampleRedditResource);
        final ReindeerMaze mazeExampleR = new ReindeerMaze(linesExampleR);
        assertEquals(12, mazeExampleR.countBestPathTiles());
    }
}