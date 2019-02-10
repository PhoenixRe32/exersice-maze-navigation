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

    public static Predicate<MazeBlock> isWall = mazeBlock -> mazeBlock == WALL;
    public static Predicate<MazeBlock> isSpace = mazeBlock -> mazeBlock == SPACE;
    public static Predicate<MazeBlock> isEntrance = mazeBlock -> mazeBlock == ENTRANCE;
    public static Predicate<MazeBlock> isExit = mazeBlock -> mazeBlock == EXIT;

//    public static boolean isWall(MazeBlock mazeBlock){
//        return mazeBlock == WALL;
//    }
}
