package nl.smerik.adventofcode.aoc2020.model.luggage;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @Test
    void equals() {
        final Bag bag01 = new Bag("bag");
        final Bag bag02 = new Bag("bag");
        bag02.addContent(Map.of(new Bag("foo"), 1));

        assertEquals(bag01, bag02);
    }

    @Test
    void getNumberOfBagsWhenEmpty() {
        final Bag bag = new Bag("empty bag");
        assertEquals(0, bag.getNumberOfBags());
    }

    @Test
    void getNumberOfBagsWhenFilledWithEmptyBags() {
        final Bag emptyBag1 = new Bag("empty bag 01");
        final Bag emptyBag2 = new Bag("empty bag 02");

        final Bag bag = new Bag("bag");
        bag.addContent(Map.of(emptyBag1, 1, emptyBag2, 2));

        assertEquals(3, bag.getNumberOfBags());
    }

    @Test
    void getNumberOfBagsWhenFilledWithBagsContainingOtherBags() {
        final Bag emptyBag1 = new Bag("empty bag 01");
        final Bag emptyBag2 = new Bag("empty bag 02");
        final Map<Bag, Integer> emptyBags = Map.of(emptyBag1, 1, emptyBag2, 2);

        final Bag foo = new Bag("foo");
        foo.addContent(emptyBags);

        final Bag bar = new Bag("bar");
        bar.addContent(emptyBags);

        final Bag bag = new Bag("bag");
        bag.addContent(Map.of(foo, 3, bar, 4));

        assertEquals(7, bag.getNumberOfBags());
    }

    @Test
    void countTotalNumberOfBags() {
        final Bag emptyBag1 = new Bag("empty bag 01");
        final Bag emptyBag2 = new Bag("empty bag 02");

        final Bag foo = new Bag("foo");
        foo.addContent(Map.of(emptyBag1, 1, emptyBag2, 2));

        final Bag bar = new Bag("bar");
        bar.addContent(Map.of(emptyBag1, 1));

        final Bag bag = new Bag("bag");
        bag.addContent(Map.of(foo, 3, bar, 4));

        // 1x bag                                          =  1
        // 3x foo + 3x (1x empty bag 01 + 2x empty bag 01) = 12
        // 4x bar + 4x (1x empty bag 01                  ) =  8
        // TOTAL                                           = 21
        assertEquals(21, bag.countTotalNumberOfBags());
    }

    @Test
    void containsBag() {
        final Bag emptyBag1 = new Bag("empty bag 01");
        final Bag emptyBag2 = new Bag("empty bag 02");

        final Bag foo = new Bag("foo");
        foo.addContent(Map.of(emptyBag1, 1, emptyBag2, 2));

        final Bag bar = new Bag("bar");
        bar.addContent(Map.of(emptyBag1, 1));

        final Bag bag = new Bag("bag");
        bag.addContent(Map.of(foo, 3, bar, 4));

        assertFalse(emptyBag1.containsBag(emptyBag2));
        assertFalse(emptyBag1.containsBag(foo));
        assertFalse(emptyBag1.containsBag(bar));
        assertFalse(emptyBag1.containsBag(bag));

        assertTrue(foo.containsBag(emptyBag1));
        assertTrue(foo.containsBag(emptyBag2));

        assertTrue(bar.containsBag(emptyBag1));
        assertFalse(bar.containsBag(emptyBag2));

        assertTrue(bag.containsBag(emptyBag1));
        assertTrue(bag.containsBag(emptyBag2));
        assertTrue(bag.containsBag(foo));
        assertTrue(bag.containsBag(bar));
    }
}