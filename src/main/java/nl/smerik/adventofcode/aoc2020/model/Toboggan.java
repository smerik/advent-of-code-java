package nl.smerik.adventofcode.aoc2020.model;

public class Toboggan {

    private static final char TREE_TOKEN = '#';

    private final AreaMap map;

    public Toboggan(final AreaMap map) {
        this.map = map;
    }

    public int countTreesUsingSlope(final int x, final int y) {
        final char[][] map = this.map.getMap();
        int treeCounter = 0;
        int i = y;
        int j = x;
        while (i < map.length) {
            if (j > map[y].length - 1) {
                j -= map[y].length;
            }
            if (map[i][j] == TREE_TOKEN) {
                treeCounter++;
            }
            i += y;
            j += x;
        }
        return treeCounter;
    }
}
