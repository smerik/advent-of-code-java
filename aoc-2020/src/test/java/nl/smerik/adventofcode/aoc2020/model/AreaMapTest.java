package nl.smerik.adventofcode.aoc2020.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

class AreaMapTest {

    @Test
    void getMap() {
        final List<String> input = List.of(
                "..##.......",
                "#...#...#.."
        );
        final AreaMap area = new AreaMap(input);

        final char[][] result = area.getMap();

        assertEquals('.', result[0][0]);
        assertEquals('.', result[0][1]);
        assertEquals('#', result[0][2]);
        assertEquals('#', result[0][3]);
        assertEquals('.', result[0][4]);
        assertEquals('.', result[0][5]);
        assertEquals('.', result[0][6]);
        assertEquals('.', result[0][7]);
        assertEquals('.', result[0][8]);
        assertEquals('.', result[0][9]);
        assertEquals('.', result[0][10]);

        assertEquals('#', result[1][0]);
        assertEquals('.', result[1][1]);
        assertEquals('.', result[1][2]);
        assertEquals('.', result[1][3]);
        assertEquals('#', result[1][4]);
        assertEquals('.', result[1][5]);
        assertEquals('.', result[1][6]);
        assertEquals('.', result[1][7]);
        assertEquals('#', result[1][8]);
        assertEquals('.', result[1][9]);
        assertEquals('.', result[1][10]);
    }
}