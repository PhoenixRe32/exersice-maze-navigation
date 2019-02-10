package uk.gov.dwp.mazeexplorer.maze;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;

import uk.gov.dwp.mazeexplorer.maze.exceptions.InvalidMazeBlockRepresentation;

public enum MazeBlock {
    WALL('X'),
    SPACE(' '),
    ENTRANCE('S'),
    EXIT('F');

    private final Character representation;

    private static final Map<Character, MazeBlock> mappings = Arrays.stream(MazeBlock.values())
            .collect(toMap(MazeBlock::getRepresentation, mb -> mb));


    public Character getRepresentation() {
        return representation;
    }

    MazeBlock(Character representation) {
        this.representation = representation;
    }

    public static MazeBlock fromCharacter(Character c) throws InvalidMazeBlockRepresentation {
        MazeBlock mazeBlock = mappings.get(c);
        if (mazeBlock == null) {
            throw new InvalidMazeBlockRepresentation(c + " is not a valid maze block.");
        }
        return mazeBlock;
    }

    // This can also be boolean methods. I am just playing around and trying something.
    // For now I don't see much of a difference between having predicates or boolean methods.
    // As far as lambdas are concerned, using predicates or method references seems to be the same.
    // Even from a readability perspective, not much of a difference.
    // I guess with predicates you can combine them more naturally to make rules,
    // e.g. isEntrance.or(isExit) vs isEntrance() || isExit()...
    // Then again when using if, you have to use the test() method
    // e.g isEntrance.test(mb) vs isEntrance(mb)
    // ... anyhoo, this has been fun. Took my time and did this over small segments the weekend
    // every now and then...
    public static Predicate<MazeBlock> isWall = mazeBlock -> mazeBlock == WALL;
    public static Predicate<MazeBlock> isSpace = mazeBlock -> mazeBlock == SPACE;
    public static Predicate<MazeBlock> isEntrance = mazeBlock -> mazeBlock == ENTRANCE;
    public static Predicate<MazeBlock> isExit = mazeBlock -> mazeBlock == EXIT;
}
