package nl.smerik.adventofcode.aoc2021.model.submarine.navigation;

import lombok.Getter;

@Getter
public class NavigationSyntaxErrorException extends Exception {

    private final ChunkPairType unexpectedPairType;

    public NavigationSyntaxErrorException(final ChunkPairType unexpectedPairType) {
        super("Unexpected chunk bracket '" + unexpectedPairType.getBracket() + "'");
        this.unexpectedPairType = unexpectedPairType;
    }
}
